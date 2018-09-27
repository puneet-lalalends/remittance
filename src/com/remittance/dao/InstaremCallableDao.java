package com.remittance.dao;

import com.google.gson.Gson;
import com.remittance.bo.InstaremBo;
import com.remittance.bo.RequestRemittanceBo;
import com.remittance.bo.TransfergoBo;
import com.remittance.utilities.MyPropertiesReader;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.Callable;

public class InstaremCallableDao implements Callable<InstaremBo> {

    Logger log = Logger.getLogger(TransferwiseDao.class);
    MyPropertiesReader myPropertiesReader = new MyPropertiesReader();
    RequestRemittanceBo requestRemittanceBo;

    public InstaremCallableDao(RequestRemittanceBo requestRemittanceBo) {
        this.requestRemittanceBo = requestRemittanceBo;
    }

    @Override
    public InstaremBo call() {

        InstaremBo instaremBo = null;
        BufferedReader rd = null;
        try {
            long startTime = System.nanoTime();
            HttpClient client = HttpClientBuilder.create().build();
            String url = myPropertiesReader.getPropertyValue("instaremURL1");
            url = MessageFormat.format(url, requestRemittanceBo.getSourceCountryCurrency(),requestRemittanceBo.getTargetCountryCurrency(),requestRemittanceBo.getSendingAmount());
            HttpGet post = new HttpGet(url);

            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = rd.readLine();
                line = "{'dataMap':"+line+"}";

                Gson g = new Gson();
                instaremBo = g.fromJson(line, InstaremBo.class);
                instaremBo = instaremURL2(instaremBo);

                String jsonInString = g.toJson(instaremBo);
                log.info("instaremBo data: "+jsonInString);

                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                log.info("InstaremBo: "+Thread.currentThread().getName()+ " Completed in "+(totalTime/1000000000.0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }finally {
            try {
                rd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return instaremBo;
    }

    public InstaremBo instaremURL2(InstaremBo instaremBo){

        BufferedReader rd = null;
        try {
            Map dataMap = instaremBo.getDataMap();
            HttpClient client = HttpClientBuilder.create().build();
            String url = myPropertiesReader.getPropertyValue("instaremURL2");
            url = MessageFormat.format(url, requestRemittanceBo.getSourceCountryCurrency(),requestRemittanceBo.getSendingAmount(),requestRemittanceBo.getTargetCountryCurrency());
            HttpGet post = new HttpGet(url);

            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = rd.readLine();
                line = "{'paymentOptionsMap':"+line+"}";

                Gson g = new Gson();
                instaremBo = g.fromJson(line, InstaremBo.class);
                instaremBo.setDataMap(dataMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }finally {
            try {
                rd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return instaremBo;
    }
}
