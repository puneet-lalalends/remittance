package com.remittance.dao;

import com.google.gson.Gson;
import com.remittance.bo.TransferwiseBo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.CyclicBarrier;

public class TransferwiseDao extends Thread {

    Logger log = Logger.getLogger(TransferwiseDao.class);

    private final CyclicBarrier cyclicBarrier;
    public TransferwiseDao(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        try {
            cyclicBarrier.await();
            long startTime = System.nanoTime();
            HttpClient client = HttpClientBuilder.create().build();
            String url = "https://transferwise.com/api/v1/payment/legacyQuote?amount=100&amountCurrency=source&getNoticeMessages=true&hasDiscount=false&isFixedRate=false&isGuaranteedFixedTarget=false&payInMethod=REGULAR&sourceCurrency=GBP&targetCurrency=INR";
            HttpGet post = new HttpGet(url);
            post.setHeader("x-authorization-key","dad99d7d8e52c2c8aaf9fda788d8acdc");

            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = rd.readLine();
            line = "{'dataMap':"+line+"}";

            System.out.println(line);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
//            System.out.println(Thread.currentThread().getName()+ " Completed in "+(totalTime/1000000000.0));
            log.info(Thread.currentThread().getName()+ " Completed in "+(totalTime/1000000000.0));

            Gson g = new Gson();

            TransferwiseBo transferwiseBo = g.fromJson(line, TransferwiseBo.class);
            String jsonInString = g.toJson(transferwiseBo);
            System.out.println(jsonInString);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

}
