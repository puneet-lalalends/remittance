package com.remittance.bo;

public class CountryDetailsBo {

    private Integer id;
    private String countryName;
    private String countryCode;
    private String countryCurrency;
    private byte[] counryFlag;

    public CountryDetailsBo() {
    }

    public CountryDetailsBo(Integer id, String countryName, String countryCode, String countryCurrency) {
        this.id = id;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.countryCurrency = countryCurrency;
    }

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
