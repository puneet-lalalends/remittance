package com.remittance.service;

import com.remittance.bo.*;
import com.remittance.dao.*;
import com.remittance.marshaller.InstaremMarshaller;
import com.remittance.marshaller.PartnerInfoMarshaller;
import com.remittance.marshaller.TransfegoMarshaller;
import com.remittance.marshaller.TransferwiseMarshaller;
import com.remittance.utilities.MyPropertiesReader;

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
    PartnerInfoMarshaller partnerInfoMarshaller = new PartnerInfoMarshaller();
    PartnerInfoOperation partnerInfoOperation = new PartnerInfoOperation();

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
            for (Future future : futures) {
                boObject = future.get();

                if (boObject != null) {
                    if (boObject instanceof TransferwiseBo) {
                        ResponseRemittanceBo responseRemittanceBo = new ResponseRemittanceBo();
                        responseRemittanceBo.setPartnerURL(myPropertiesReader.getPropertyValue("transferWisePartnerURL"));
                        responseRemittanceBo.setPartnerName(myPropertiesReader.getPropertyValue("transferWisePartnerName"));
                        setPartnerInfo(responseRemittanceBo);

                        responseRemittanceBo = transferwiseMarshaller.daoToBo((TransferwiseBo) boObject, responseRemittanceBo);
                        responseRemittanceBoList.add(responseRemittanceBo);
                    } else if (boObject instanceof TransfergoBo) {
                        ResponseRemittanceBo responseRemittanceBo = new ResponseRemittanceBo();
                        responseRemittanceBo.setPartnerURL(myPropertiesReader.getPropertyValue("transfergoPartnerURL"));
                        responseRemittanceBo.setPartnerName(myPropertiesReader.getPropertyValue("transfergoPartnerName"));
                        responseRemittanceBo.setSourceCurrency(requestRemittanceBo.getSourceCountryCurrency());
                        responseRemittanceBo.setTargetCurrency(requestRemittanceBo.getTargetCountryCurrency());
                        setPartnerInfo(responseRemittanceBo);

                        responseRemittanceBo = transfergoMarshaller.daoToBo((TransfergoBo) boObject, responseRemittanceBo);
                        responseRemittanceBoList.add(responseRemittanceBo);
                    } else if (boObject instanceof InstaremBo) {
                        ResponseRemittanceBo responseRemittanceBo = new ResponseRemittanceBo();
                        responseRemittanceBo.setPartnerURL(myPropertiesReader.getPropertyValue("instaremPartnerURL"));
                        responseRemittanceBo.setPartnerName(myPropertiesReader.getPropertyValue("instaremPartnerName"));
                        responseRemittanceBo.setSourceCurrency(requestRemittanceBo.getSourceCountryCurrency());
                        responseRemittanceBo.setTargetCurrency(requestRemittanceBo.getTargetCountryCurrency());
                        setPartnerInfo(responseRemittanceBo);

                        responseRemittanceBo = instaremMarshaller.daoToBo((InstaremBo) boObject, responseRemittanceBo);
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

    public void setPartnerInfo(ResponseRemittanceBo responseRemittanceBo) {
        PartnerInfo partnerInfo = new PartnerInfo();
        partnerInfo.setPartner(responseRemittanceBo.getPartnerURL());
        partnerInfo = (PartnerInfo) (partnerInfoOperation.reteriveSpecific(partnerInfo).get(0));
        responseRemittanceBo.setPartnerInfoBo(partnerInfoMarshaller.daoTOBo(partnerInfo));
    }

}
