package com.remittance.bo;

import org.springframework.core.io.InputStreamResource;

public class CountryDetailsBo {

    private Integer id;
    private String countryName;
    private String countryCode;
    private String countryCurrency;
    private byte[] counryFlag;
//    private InputStreamResource inputStreamResource;

    public CountryDetailsBo() {
    }

    public CountryDetailsBo(Integer id, String countryName, String countryCode, String countryCurrency) {
        this.id = id;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.countryCurrency = countryCurrency;
    }

    public byte[] getCounryFlag() {
        return counryFlag;
    }

    public void setCounryFlag(byte[] counryFlag) {
        this.counryFlag = counryFlag;
    }

    /*public InputStreamResource getInputStreamResource() {
        return inputStreamResource;
    }

    public void setInputStreamResource(InputStreamResource inputStreamResource) {
        this.inputStreamResource = inputStreamResource;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCurrency() {
        return countryCurrency;
    }

    public void setCountryCurrency(String countryCurrency) {
        this.countryCurrency = countryCurrency;
    }


}
