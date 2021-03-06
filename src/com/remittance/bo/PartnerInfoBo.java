package com.remittance.bo;

public class PartnerInfoBo {

    private Integer id;
    private String partner;
    private String licensedBy;
    private String basedIn;
    private String availableIn;
    private String briefAbout;
    private String dealIn;

    public PartnerInfoBo() {
    }

    public PartnerInfoBo(Integer id, String partner, String licensedBy, String basedIn, String availableIn, String briefAbout, String dealIn) {
        this.id = id;
        this.partner = partner;
        this.licensedBy = licensedBy;
        this.basedIn = basedIn;
        this.availableIn = availableIn;
        this.briefAbout = briefAbout;
        this.dealIn = dealIn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getLicensedBy() {
        return licensedBy;
    }

    public void setLicensedBy(String licensedBy) {
        this.licensedBy = licensedBy;
    }

    public String getBasedIn() {
        return basedIn;
    }

    public void setBasedIn(String basedIn) {
        this.basedIn = basedIn;
    }

    public String getAvailableIn() {
        return availableIn;
    }

    public void setAvailableIn(String availableIn) {
        this.availableIn = availableIn;
    }

    public String getBriefAbout() {
        return briefAbout;
    }

    public void setBriefAbout(String briefAbout) {
        this.briefAbout = briefAbout;
    }

    public String getDealIn() {
        return dealIn;
    }

    public void setDealIn(String dealIn) {
        this.dealIn = dealIn;
    }
}
