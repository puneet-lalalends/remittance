package com.remittance.marshaller;

import com.remittance.bo.PaymentModeBo;
import com.remittance.bo.ResponseRemittanceBo;
import com.remittance.bo.TransfergoBo;
import com.remittance.bo.TransferwiseBo;
import com.remittance.utilities.MyPropertiesReader;
import org.apache.log4j.Logger;

import java.util.*;

public class TransfegoMarshaller {

    Logger log = Logger.getLogger(TransferwiseMarshaller.class);
    MyPropertiesReader myPropertiesReader = new MyPropertiesReader();

    public ResponseRemittanceBo daoToBo(TransfergoBo transfergoBo, ResponseRemittanceBo responseRemittanceBo) {

        Map dataMap = transfergoBo.getDataMap();

        responseRemittanceBo.setSendingAmount(String.valueOf(dataMap.get(myPropertiesReader.getPropertyValue("transfergo.sendingAmount"))));
        responseRemittanceBo.setExchangeRate(String.valueOf(dataMap.get(myPropertiesReader.getPropertyValue("transfergo.exchangeRate"))));
        responseRemittanceBo.setTransferFees(String.valueOf(dataMap.get(myPropertiesReader.getPropertyValue("transfergo.transferFees"))));
        responseRemittanceBo.setAmountReceived(String.valueOf(dataMap.get(myPropertiesReader.getPropertyValue("transfergo.targetAmount"))));

        Map deliveryOptionsMap = (Map)dataMap.get("deliveryOptions");
        List<PaymentModeBo> paymentModeBoList = new ArrayList<PaymentModeBo>();

        Set deliveryOptionsSet = deliveryOptionsMap.entrySet();
        Iterator deliveryOptionsIterator = deliveryOptionsSet.iterator();

        while (deliveryOptionsIterator.hasNext()){
            Map.Entry deliveryOption = (Map.Entry)deliveryOptionsIterator.next();

            if(deliveryOption.getKey().equals(myPropertiesReader.getPropertyValue("transfergo.deliveryOptionsStandard"))){
                String estimatedDelivery = (String)((Map)(((Map)(deliveryOption.getValue())).get("quote"))).get("deliveryEstimate");

                PaymentModeBo debitPaymentModeBo = new PaymentModeBo();
                debitPaymentModeBo.setEstimatedDelivery(estimatedDelivery);
                debitPaymentModeBo.setPaymentType(myPropertiesReader.getPropertyValue("transfergo.deliveryOptionsDebit"));
                PaymentModeBo creditPaymentModeBo = new PaymentModeBo();
                creditPaymentModeBo.setEstimatedDelivery(estimatedDelivery);
                creditPaymentModeBo.setPaymentType(myPropertiesReader.getPropertyValue("transfergo.deliveryOptionsCredit"));
                PaymentModeBo bankPaymentModeBo = new PaymentModeBo();
                bankPaymentModeBo.setEstimatedDelivery(estimatedDelivery);
                bankPaymentModeBo.setPaymentType(myPropertiesReader.getPropertyValue("transfergo.deliveryOptionsBank"));

                paymentModeBoList.add(debitPaymentModeBo);
                paymentModeBoList.add(creditPaymentModeBo);
                paymentModeBoList.add(bankPaymentModeBo);
            }
        }

        responseRemittanceBo.setPaymentMode(paymentModeBoList);

        return responseRemittanceBo;
    }

}
