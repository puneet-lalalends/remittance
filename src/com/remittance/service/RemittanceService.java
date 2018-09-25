package com.remittance.service;

import com.remittance.bo.*;
import com.remittance.dao.InstaremCallableDao;
import com.remittance.dao.TransfergoCallableDao;
import com.remittance.dao.TransferwiseCallableDao;
import com.remittance.marshaller.InstaremMarshaller;
import com.remittance.marshaller.TransfegoMarshaller;
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
    TransfegoMarshaller transfergoMarshaller = new TransfegoMarshaller();
    InstaremMarshaller instaremMarshaller = new InstaremMarshaller();
    MyPropertiesReader myPropertiesReader = new MyPropertiesReader();

    public List<ResponseRemittanceBo> fetchAndConvert(RequestRemittanceBo requestRemittanceBo) {

        List<ResponseRemittanceBo> responseRemittanceBoList = new ArrayList();
        Object boObject = null;
        ExecutorService service = Executors.newFixedThreadPool(10);

        Set callables = new HashSet();
        callables.add(new TransferwiseCallableDao(requestRemittanceBo));
        callables.add(new TransfergoCallableDao(requestRemittanceBo));
        callables.add(new InstaremCallableDao(requestRemittanceBo));

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
                        ResponseRemittanceBo responseRemittanceBo = new ResponseRemittanceBo();
                        responseRemittanceBo.setPartner(myPropertiesReader.getPropertyValue("transfergoPartnerName"));
                        responseRemittanceBo = transfergoMarshaller.daoToBo((TransfergoBo)boObject,responseRemittanceBo);
                        responseRemittanceBoList.add(responseRemittanceBo);
                    }else if(boObject instanceof InstaremBo){
                        ResponseRemittanceBo responseRemittanceBo = new ResponseRemittanceBo();
                        responseRemittanceBo.setPartner(myPropertiesReader.getPropertyValue("instaremPartnerName"));
                        responseRemittanceBo = instaremMarshaller.daoToBo((InstaremBo)boObject,responseRemittanceBo);
                        responseRemittanceBoList.add(responseRemittanceBo);
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
