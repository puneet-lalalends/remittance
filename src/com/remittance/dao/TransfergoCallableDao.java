package com.remittance.dao;

import com.google.gson.Gson;
import com.remittance.bo.RequestRemittanceBo;
import com.remittance.bo.TransfergoBo;
import com.remittance.bo.TransferwiseBo;
import com.remittance.utilities.MyPropertiesReader;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.concurrent.Callable;

public class TransfergoCallableDao implements Callable<TransfergoBo> {

    Logger log = Logger.getLogger(TransferwiseDao.class);
    MyPropertiesReader myPropertiesReader = new MyPropertiesReader();
    RequestRemittanceBo requestRemittanceBo;

    public TransfergoCallableDao(RequestRemittanceBo requestRemittanceBo) {
        this.requestRemittanceBo = requestRemittanceBo;
    }

    @Override
    public TransfergoBo call() {

        TransfergoBo transfergoBo = null;
        try {
            long startTime = System.nanoTime();
            HttpClient client = HttpClientBuilder.create().build();
            String url = myPropertiesReader.getPropertyValue("transfergoURL");
            url = MessageFormat.format(url, requestRemittanceBo.getSendingAmount(),requestRemittanceBo.getSourceCountryCode(),requestRemittanceBo.getTargetCountryCode(),requestRemittanceBo.getSourceCountryCurrency(),requestRemittanceBo.getTargetCountryCurrency());
            HttpGet post = new HttpGet(url);

            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = rd.readLine();
                line = "{'dataMap':"+line+"}";
/*
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                log.info(Thread.currentThread().getName()+ " Completed in "+(totalTime/1000000000.0));
*/
                Gson g = new Gson();
                transfergoBo = g.fromJson(line, TransfergoBo.class);
                String jsonInString = g.toJson(transfergoBo);
                log.info("transfergoBo data: "+jsonInString);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        return transfergoBo;
    }




}
