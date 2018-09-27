package com.remittance.dao;

import com.google.gson.Gson;
import com.remittance.bo.RequestRemittanceBo;
import com.remittance.bo.TransferwiseBo;
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
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class TransferwiseCallableDao implements Callable<TransferwiseBo> {

    Logger log = Logger.getLogger(TransferwiseDao.class);
    MyPropertiesReader myPropertiesReader = new MyPropertiesReader();
    RequestRemittanceBo requestRemittanceBo;

    public TransferwiseCallableDao(RequestRemittanceBo requestRemittanceBo) {
        this.requestRemittanceBo = requestRemittanceBo;
    }

    @Override
    public TransferwiseBo call() {

        TransferwiseBo transferwiseBo = null;
        BufferedReader rd = null;
        try {
            long startTime = System.nanoTime();
            HttpClient client = HttpClientBuilder.create().build();
            String url = myPropertiesReader.getPropertyValue("transferWiseURL");
            url = MessageFormat.format(url, requestRemittanceBo.getSendingAmount(),requestRemittanceBo.getSourceCountryCurrency(),requestRemittanceBo.getTargetCountryCurrency());
            HttpGet post = new HttpGet(url);
            post.setHeader("x-authorization-key",myPropertiesReader.getPropertyValue("xAuthorizationKey"));

            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = rd.readLine();
                line = "{'dataMap':"+line+"}";

                Gson g = new Gson();
                transferwiseBo = g.fromJson(line, TransferwiseBo.class);
                String jsonInString = g.toJson(transferwiseBo);
                log.info("transferwise data: "+jsonInString);

                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                log.info("Transferwise: "+Thread.currentThread().getName()+ " Completed in "+(totalTime/1000000000.0));
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

        return transferwiseBo;
    }

}
