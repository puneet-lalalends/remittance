package com.remittance.dao;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "partner_info", catalog = "remittance_db",schema = "remittance_db")
public class PartnerInfo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "partner", nullable = true,length = 100)
    private String partner;
    @Column(name = "licensed_by", nullable = true,length = 100)
    private String licensedBy;
    @Column(name = "based_in", nullable = true,length = 100)
    private String basedIn;
    @Column(name = "available_in", nullable = true,length = 100)
    private String availableIn;
    @Column(name = "brief_about", nullable = true,columnDefinition="TEXT")
    private String briefAbout;
    @Column(name = "deal_in", nullable = true,length = 100)
    private String dealIn;

    public PartnerInfo() {
    }

    public PartnerInfo(String partner, String licensedBy, String basedIn, String availableIn, String briefAbout, String dealIn) {
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
