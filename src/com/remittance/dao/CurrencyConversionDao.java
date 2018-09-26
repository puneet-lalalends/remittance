package com.remittance.dao;

import com.google.gson.Gson;
import com.remittance.bo.InstaremBo;
import com.remittance.bo.RequestCurrencyConversionBo;
import com.remittance.bo.ResponseCurrencyConversionBo;
import com.remittance.utilities.MyPropertiesReader;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.MessageFormat;

public class CurrencyConversionDao {

    Logger log = Logger.getLogger(TransferwiseDao.class);
    MyPropertiesReader myPropertiesReader = new MyPropertiesReader();
    RequestCurrencyConversionBo requestCurrencyConversionBo;

    public CurrencyConversionDao(RequestCurrencyConversionBo requestCurrencyConversionBo) {
        this.requestCurrencyConversionBo = requestCurrencyConversionBo;
    }

    //TODO: Refactor this method this is temp solution
    public ResponseCurrencyConversionBo converCurrency() {

        ResponseCurrencyConversionBo responseCurrencyConversionBo = new ResponseCurrencyConversionBo();

        try {
            long startTime = System.nanoTime();
            HttpClient client = HttpClientBuilder.create().build();
            String url = myPropertiesReader.getPropertyValue("unitconvertersUrl");
            url = MessageFormat.format(url, requestCurrencyConversionBo.getSourceCountryCurrency(),requestCurrencyConversionBo.getTargetCountryCurrency());
            HttpGet post = new HttpGet(url);

            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String finalResult = "";
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                finalResult = result.toString();
                log.info(finalResult);
                String currencyRate = finalResult.substring(finalResult.indexOf("<p class=\"bigtext\">"),finalResult.lastIndexOf("<p class=\"bigtext\">"));
                log.info(currencyRate);
                currencyRate = currencyRate.replace("<p class=\"bigtext\">","").replace("</p>","");
                log.info(currencyRate);
                String[] currencyRateSplitByBR =  currencyRate.split("<br>");
                log.info(currencyRateSplitByBR[0]);
                String finalCurrencyRate = currencyRateSplitByBR[0].split("=")[1].replaceAll("[a-zA-Z]", "").trim();
                log.info(finalCurrencyRate);
                responseCurrencyConversionBo.setCurrencyRate(finalCurrencyRate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        return responseCurrencyConversionBo;
    }
}
