package com.remittance.bo;

import java.util.ArrayList;
import java.util.List;

public class ResponseRemittanceBo {

    private String partnerURL;
    private String partnerName;
    private String sourceCurrency;
    private String targetCurrency;
    private String sendingAmount;
    private String transferFees;
    private String exchangeRate;
    private String amountReceived;
    private PartnerInfoBo partnerInfoBo;
    private List<PaymentModeBo> paymentMode = new ArrayList<PaymentModeBo>();

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public PartnerInfoBo getPartnerInfoBo() {
        return partnerInfoBo;
    }

    public void setPartnerInfoBo(PartnerInfoBo partnerInfoBo) {
        this.partnerInfoBo = partnerInfoBo;
    }

    public String getPartnerURL() {
        return partnerURL;
    }

    public void setPartnerURL(String partnerURL) {
        this.partnerURL = partnerURL;
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
