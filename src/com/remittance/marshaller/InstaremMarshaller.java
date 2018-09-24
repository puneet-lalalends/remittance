package com.remittance.marshaller;

import com.remittance.bo.InstaremBo;
import com.remittance.bo.PaymentModeBo;
import com.remittance.bo.ResponseRemittanceBo;
import com.remittance.bo.TransfergoBo;
import com.remittance.utilities.MyPropertiesReader;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class InstaremMarshaller {

    Logger log = Logger.getLogger(TransferwiseMarshaller.class);
    MyPropertiesReader myPropertiesReader = new MyPropertiesReader();

    public ResponseRemittanceBo daoToBo(InstaremBo instaremBo, ResponseRemittanceBo responseRemittanceBo) {

        Map dataMap = instaremBo.getDataMap();

        Map data = (Map)dataMap.get(myPropertiesReader.getPropertyValue("instarem.data"));
        Map transactionConfig = (Map)data.get(myPropertiesReader.getPropertyValue("instarem.transactionConfig"));
        String sendingAmount = String.valueOf(transactionConfig.get(myPropertiesReader.getPropertyValue("instarem.sendingAmount")));
        String targetAmount = String.valueOf(data.get(myPropertiesReader.getPropertyValue("instarem.targetAmount")));
        String transferFees = String.valueOf(data.get(myPropertiesReader.getPropertyValue("instarem.transferFees")));
        String exchangeRate = String.valueOf(data.get(myPropertiesReader.getPropertyValue("instarem.exchangeRate")));

        responseRemittanceBo.setSendingAmount(sendingAmount);
        responseRemittanceBo.setAmountReceived(targetAmount);
        responseRemittanceBo.setTransferFees(transferFees);
        responseRemittanceBo.setExchangeRate(exchangeRate);

        List deliveryOptionsList = (List)((Map)instaremBo.getPaymentOptionsMap()).get("data");
        List<PaymentModeBo> paymentModeBoList = new ArrayList<PaymentModeBo>();

        Iterator deliveryOptionsIterator = deliveryOptionsList.iterator();

        while (deliveryOptionsIterator.hasNext()){
            Map deliveryOption = (Map)deliveryOptionsIterator.next();

            Calendar c = Calendar.getInstance();
            c.setTime(new Date()); // Now use today date.
            c.add(Calendar.DATE, 2);

            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

            if(deliveryOption.get("text").equals(myPropertiesReader.getPropertyValue("instarem.BankTransfer"))){

                PaymentModeBo bankTransferPaymentModeBo = new PaymentModeBo();
                bankTransferPaymentModeBo.setEstimatedDelivery(sdf.format(c.getTime()));
                bankTransferPaymentModeBo.setPaymentType(myPropertiesReader.getPropertyValue("instarem.BankTransfer"));

                paymentModeBoList.add(bankTransferPaymentModeBo);
            }else if(deliveryOption.get("text").equals(myPropertiesReader.getPropertyValue("instarem.DebitCard"))){

                PaymentModeBo debitPaymentModeBo = new PaymentModeBo();
                debitPaymentModeBo.setEstimatedDelivery(sdf.format(c.getTime()));
                debitPaymentModeBo.setPaymentType(myPropertiesReader.getPropertyValue("instarem.DebitCard"));

                paymentModeBoList.add(debitPaymentModeBo);
            }
        }

        responseRemittanceBo.setPaymentMode(paymentModeBoList);

        return responseRemittanceBo;
    }

}
