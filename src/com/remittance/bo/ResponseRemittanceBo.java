package com.remittance.bo;

import java.util.ArrayList;
import java.util.List;

public class ResponseRemittanceBo {

    private String partner;
    private String sourceCurrency;
    private String targetCurrency;
    private String sendingAmount;
    private String transferFees;
    private String exchangeRate;
    private String amountReceived;
    private PartnerInfoBo partnerInfoBo;
    private List<PaymentModeBo> paymentMode = new ArrayList<PaymentModeBo>();

    public PartnerInfoBo getPartnerInfoBo() {
        return partnerInfoBo;
    }

    public void setPartnerInfoBo(PartnerInfoBo partnerInfoBo) {
        this.partnerInfoBo = partnerInfoBo;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public List<PaymentModeBo> getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(List<PaymentModeBo> paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getSendingAmount() {
        return sendingAmount;
    }

    public void setSendingAmount(String sendingAmount) {
        this.sendingAmount = sendingAmount;
    }

    public String getTransferFees() {
        return transferFees;
    }

    public void setTransferFees(String transferFees) {
        this.transferFees = transferFees;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(String amountReceived) {
        this.amountReceived = amountReceived;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
