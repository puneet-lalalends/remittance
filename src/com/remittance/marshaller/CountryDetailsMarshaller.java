package com.remittance.marshaller;

import com.remittance.bo.CountryDetailsBo;
import com.remittance.dao.CountryDetails;
import sun.misc.IOUtils;

public class CountryDetailsMarshaller {

    public CountryDetailsBo daoTOBo(CountryDetails countryDetails) {
        CountryDetailsBo countryDetailsBo = new CountryDetailsBo();

        countryDetailsBo.setId(countryDetails.getId());
        countryDetailsBo.setCountryCode(countryDetails.getCountryCode());
        countryDetailsBo.setCountryCurrency(countryDetails.getCountryCurrency());
        countryDetailsBo.setCountryName(countryDetails.getCountryName());

        return countryDetailsBo;
    }

    public CountryDetails boToDao(CountryDetailsBo countryDetailsBo){
        return boToDao(countryDetailsBo,null);
    }

    public CountryDetails boToDao(CountryDetailsBo countryDetailsBo,CountryDetails countryDetails){

        if(countryDetails == null){
            countryDetails = new CountryDetails();

            countryDetails.setCountryCode(countryDetailsBo.getCountryCode());
            countryDetails.setCountryCurrency(countryDetailsBo.getCountryCurrency());
            countryDetails.setCountryName(countryDetailsBo.getCountryName());
        }else{
            countryDetails.setId(countryDetailsBo.getId());
            countryDetails.setCountryName(countryDetailsBo.getCountryName());
            countryDetails.setCountryCurrency(countryDetailsBo.getCountryCurrency());
            countryDetails.setCountryCode(countryDetailsBo.getCountryCode());
        }
        return countryDetails;
    }

}
