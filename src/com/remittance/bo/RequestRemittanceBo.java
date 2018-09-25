package com.remittance.bo;

public class RequestRemittanceBo {

    private Integer sendingAmount;
    private String sourceCountryCode;
    private String sourceCountryName;
    private String sourceCountryCurrency;
    private String targetCountryCode;
    private String targetCountryName;
    private String targetCountryCurrency;

    public Integer getSendingAmount() {
        return sendingAmount;
    }

    public void setSendingAmount(Integer sendingAmount) {
        this.sendingAmount = sendingAmount;
    }

    public String getSourceCountryCode() {
        return sourceCountryCode;
    }

    public void setSourceCountryCode(String sourceCountryCode) {
        this.sourceCountryCode = sourceCountryCode;
    }

    public String getSourceCountryName() {
        return sourceCountryName;
    }

    public void setSourceCountryName(String sourceCountryName) {
        this.sourceCountryName = sourceCountryName;
    }

    public String getSourceCountryCurrency() {
        return sourceCountryCurrency;
    }

    public void setSourceCountryCurrency(String sourceCountryCurrency) {
        this.sourceCountryCurrency = sourceCountryCurrency;
    }

    public String getTargetCountryCode() {
        return targetCountryCode;
    }

    public void setTargetCountryCode(String targetCountryCode) {
        this.targetCountryCode = targetCountryCode;
    }

    public String getTargetCountryName() {
        return targetCountryName;
    }

    public void setTargetCountryName(String targetCountryName) {
        this.targetCountryName = targetCountryName;
    }

    public String getTargetCountryCurrency() {
        return targetCountryCurrency;
    }

    public void setTargetCountryCurrency(String targetCountryCurrency) {
        this.targetCountryCurrency = targetCountryCurrency;
    }
}
