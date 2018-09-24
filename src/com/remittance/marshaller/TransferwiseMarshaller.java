package com.remittance.marshaller;

import com.remittance.bo.PaymentModeBo;
import com.remittance.bo.ResponseRemittanceBo;
import com.remittance.bo.TransferwiseBo;
import com.remittance.dao.TransferwiseDao;
import com.remittance.utilities.MyPropertiesReader;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransferwiseMarshaller {

    Logger log = Logger.getLogger(TransferwiseMarshaller.class);
    MyPropertiesReader myPropertiesReader = new MyPropertiesReader();

    public ResponseRemittanceBo daoToBo(TransferwiseBo transferwiseBo, ResponseRemittanceBo responseRemittanceBo) {

        Map dataMap = transferwiseBo.getDataMap();

        responseRemittanceBo.setSendingAmount(String.valueOf(dataMap.get(myPropertiesReader.getPropertyValue("transferWise.sendingAmount"))));
        responseRemittanceBo.setExchangeRate(String.valueOf(dataMap.get(myPropertiesReader.getPropertyValue("transferWise.exchangeRate"))));

        List paymentModeList = (List)dataMap.get("paymentOptions");

        boolean isInternational = false;
        for(int i = 0 ; i < paymentModeList.size() ; i++){
            Map paymentMode = (Map)paymentModeList.get(i);
            if(paymentMode.get("type").equals(myPropertiesReader.getPropertyValue("transferWise.paymentModeCheck"))){
                isInternational = true;
                break;
            }
        }

        List<PaymentModeBo> paymentModeBoList = new ArrayList<PaymentModeBo>();

        if(isInternational){
            for(int i = 0 ; i < paymentModeList.size() ; i++){
                Map paymentMode = (Map)paymentModeList.get(i);
                PaymentModeBo paymentModeBo = null;
                if(paymentMode.get("type").equals(myPropertiesReader.getPropertyValue("transferWise.paymentModesInternational"))){
                    paymentModeBo = new PaymentModeBo();
                    paymentModeBo.setPaymentType(myPropertiesReader.getPropertyValue("transferWise.paymentModesInternationalDisplay"));
                    paymentModeBo.setEstimatedDelivery((String)paymentMode.get("estimatedDelivery"));
                    responseRemittanceBo.setAmountReceived((String)paymentMode.get("targetAmount"));
                    responseRemittanceBo.setTargetCurrency((String)paymentMode.get("targetCurrency"));
                    responseRemittanceBo.setSourceCurrency((String)paymentMode.get("sourceCurrency"));
                    responseRemittanceBo.setTransferFees((String)((Map)paymentMode.get("fee")).get("total"));
                    break;
                }

                if(paymentModeBo != null){
                    paymentModeBoList.add(paymentModeBo);
                }
            }
        }else{
            for(int i = 0 ; i < paymentModeList.size() ; i++){
                Map paymentMode = (Map)paymentModeList.get(i);
                PaymentModeBo paymentModeBo = null;
                if(paymentMode.get("type").equals(myPropertiesReader.getPropertyValue("transferWise.paymentModesDebit"))){
                    paymentModeBo = new PaymentModeBo();
                    paymentModeBo.setPaymentType(myPropertiesReader.getPropertyValue("transferWise.paymentModesDebitDisplay"));
                    paymentModeBo.setEstimatedDelivery((String)paymentMode.get("estimatedDelivery"));
                }else if(paymentMode.get("type").equals(myPropertiesReader.getPropertyValue("transferWise.paymentModesCredit"))){
                    paymentModeBo = new PaymentModeBo();
                    paymentModeBo.setPaymentType(myPropertiesReader.getPropertyValue("transferWise.paymentModesCreditDisplay"));
                    paymentModeBo.setEstimatedDelivery((String)paymentMode.get("estimatedDelivery"));
                }else if(paymentMode.get("type").equals(myPropertiesReader.getPropertyValue("transferWise.paymentModesSwift"))){
                    paymentModeBo = new PaymentModeBo();
                    paymentModeBo.setPaymentType(myPropertiesReader.getPropertyValue("transferWise.paymentModesSwiftDisplay"));
                    paymentModeBo.setEstimatedDelivery((String)paymentMode.get("estimatedDelivery"));

                    responseRemittanceBo.setAmountReceived(String.valueOf(paymentMode.get("targetAmount")));
                    responseRemittanceBo.setTransferFees(String.valueOf(((Map)paymentMode.get("fee")).get("total")));
                    responseRemittanceBo.setTargetCurrency((String)paymentMode.get("targetCurrency"));
                    responseRemittanceBo.setSourceCurrency((String)paymentMode.get("sourceCurrency"));
                }

                if(paymentModeBo != null){
                    paymentModeBoList.add(paymentModeBo);
                }
            }
        }

        responseRemittanceBo.setPaymentMode(paymentModeBoList);

        return responseRemittanceBo;
    }
}
