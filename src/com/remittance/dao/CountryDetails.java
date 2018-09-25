package com.remittance.dao;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "country_details", catalog = "remittance_db",schema = "remittance_db")
public class CountryDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "country_name", nullable = true,length = 100)
    private String countryName;
    @Column(name = "country_code", nullable = true)
    private String countryCode;
    @Column(name = "country_currency", nullable = true,length = 100)
    private String countryCurrency;

    public CountryDetails() {
    }

    public CountryDetails(String countryName, String countryCode, String countryCurrency) {
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
