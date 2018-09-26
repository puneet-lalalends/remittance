package com.remittance.dao;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "country", catalog = "remittance_db",schema = "remittance_db")
public class CountryDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name", nullable = true,length = 150)
    private String countryName;
    @Column(name = "iso_code", nullable = true,length = 3)
    private char isoCode;
    @Column(name = "country_code", nullable = true,length = 3)
    private String countryCode;
    @Column(name = "dial_code", nullable = true)
    private Integer dialCode;
    @Column(name = "nationality", nullable = true,length = 50)
    private String nationality;
    @Column(name = "currency_name", nullable = true,length = 30)
    private String currencyName;
    @Column(name = "currency_symbol", nullable = true,length = 20)
    private String currencySymbol;
    @Column(name = "currency_code", nullable = true,length = 20)
    private String countryCurrency;
    @Column(name = "status", nullable = true,length = 1)
    private byte status;
    @Column(name = "mob_length", nullable = true,length = 3)
    private byte mobLength;

    public CountryDetails() {
    }

    public CountryDetails(String countryName, char isoCode, String countryCode, Integer dialCode, String nationality, String currencyName, String currencySymbol, String countryCurrency, byte status, byte mobLength) {
        this.countryName = countryName;
        this.isoCode = isoCode;
        this.countryCode = countryCode;
        this.dialCode = dialCode;
        this.nationality = nationality;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.countryCurrency = countryCurrency;
        this.status = status;
        this.mobLength = mobLength;
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

    public char getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(char isoCode) {
        this.isoCode = isoCode;
    }

    public Integer getDialCode() {
        return dialCode;
    }

    public void setDialCode(Integer dialCode) {
        this.dialCode = dialCode;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getMobLength() {
        return mobLength;
    }

    public void setMobLength(byte mobLength) {
        this.mobLength = mobLength;
    }
}
