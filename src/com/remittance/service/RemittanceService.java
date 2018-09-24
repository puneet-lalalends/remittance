package com.remittance.service;

import com.remittance.bo.RequestRemittanceBo;
import com.remittance.bo.ResponseRemittanceBo;
import com.remittance.bo.TransfergoBo;
import com.remittance.bo.TransferwiseBo;
import com.remittance.dao.TransferwiseCallableDao;
import com.remittance.marshaller.TransferwiseMarshaller;
import com.remittance.utilities.MyPropertiesReader;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class RemittanceService {

    TransferwiseMarshaller transferwiseMarshaller = new TransferwiseMarshaller();
    MyPropertiesReader myPropertiesReader = new MyPropertiesReader();

    public List<ResponseRemittanceBo> fetchAndConvert(RequestRemittanceBo requestRemittanceBo) {

        List<ResponseRemittanceBo> responseRemittanceBoList = new ArrayList();
        Object boObject = null;
        ExecutorService service = Executors.newFixedThreadPool(10);

        Set callables = new HashSet();
        callables.add(new TransferwiseCallableDao(requestRemittanceBo));

        try {
            List<Future> futures = service.invokeAll(callables);
            for(Future future : futures){
                boObject = future.get();

                if(boObject != null){
                    if(boObject instanceof TransferwiseBo){
                        ResponseRemittanceBo responseRemittanceBo = new ResponseRemittanceBo();
                        responseRemittanceBo.setPartner(myPropertiesReader.getPropertyValue("transferWisePartnerName"));
                        responseRemittanceBo = transferwiseMarshaller.daoToBo((TransferwiseBo)boObject,responseRemittanceBo);
                        responseRemittanceBoList.add(responseRemittanceBo);
                    }else if(boObject instanceof TransfergoBo){


                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();

        return responseRemittanceBoList;
    }

}
