package com.remittance.bo;

public class RequestCurrencyConversionBo {

    private String sourceCountryCurrency;
    private String targetCountryCurrency;

    public String getSourceCountryCurrency() {
        return sourceCountryCurrency;
    }

    public void setSourceCountryCurrency(String sourceCountryCurrency) {
        this.sourceCountryCurrency = sourceCountryCurrency;
    }

    public String getTargetCountryCurrency() {
        return targetCountryCurrency;
    }

    public void setTargetCountryCurrency(String targetCountryCurrency) {
        this.targetCountryCurrency = targetCountryCurrency;
    }
}
