package com.remittance.marshaller;

import com.remittance.bo.CountryDetailsBo;
import com.remittance.bo.PartnerInfoBo;
import com.remittance.dao.CountryDetails;
import com.remittance.dao.PartnerInfo;

public class PartnerInfoMarshaller {


    public PartnerInfoBo daoTOBo(PartnerInfo partnerInfo) {
        PartnerInfoBo partnerInfoBo = new PartnerInfoBo(partnerInfo.getId(),partnerInfo.getPartner(),partnerInfo.getLicensedBy(),partnerInfo.getBasedIn(),partnerInfo.getAvailableIn(),partnerInfo.getBriefAbout(),partnerInfo.getDealIn());

        return partnerInfoBo;
    }

    public PartnerInfo boToDao(PartnerInfoBo partnerInfoBo){
        return boToDao(partnerInfoBo,null);
    }

    public PartnerInfo boToDao(PartnerInfoBo partnerInfoBo,PartnerInfo partnerInfo){

        if(partnerInfo == null){
            partnerInfo = new PartnerInfo(partnerInfoBo.getPartner(),partnerInfoBo.getLicensedBy(),partnerInfoBo.getBasedIn(),partnerInfoBo.getAvailableIn(),partnerInfoBo.getBriefAbout(),partnerInfoBo.getDealIn());
        }else{
            partnerInfo.setId(partnerInfoBo.getId());
            partnerInfo.setLicensedBy(partnerInfoBo.getLicensedBy());
            partnerInfo.setBasedIn(partnerInfoBo.getBasedIn());
            partnerInfo.setAvailableIn(partnerInfoBo.getAvailableIn());
            partnerInfo.setBriefAbout(partnerInfoBo.getBriefAbout());
            partnerInfo.setDealIn(partnerInfoBo.getDealIn());
        }
        return partnerInfo;
    }
}
