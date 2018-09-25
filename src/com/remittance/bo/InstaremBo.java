package com.remittance.bo;

import java.util.HashMap;
import java.util.Map;

public class InstaremBo {

    private Map dataMap = new HashMap();
    private Map paymentOptionsMap = new HashMap();

    public Map getPaymentOptionsMap() {
        return paymentOptionsMap;
    }

    public void setPaymentOptionsMap(Map paymentOptionsMap) {
        this.paymentOptionsMap = paymentOptionsMap;
    }

    public Map getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map dataMap) {
        this.dataMap = dataMap;
    }
}
