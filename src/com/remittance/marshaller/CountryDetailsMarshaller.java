package com.remittance.marshaller;

import com.remittance.bo.CountryDetailsBo;
import com.remittance.dao.CountryDetails;

public class CountryDetailsMarshaller {

    public CountryDetailsBo daoTOBo(CountryDetails countryDetails) {
        //TODO: Convert data
        CountryDetailsBo countryDetailsBo = new CountryDetailsBo();

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
            //TODO: Convert Data
            countryDetails = new CountryDetails();

            countryDetails.setCountryCode(countryDetailsBo.getCountryCode());
            countryDetails.setCountryCurrency(countryDetailsBo.getCountryCurrency());
            countryDetails.setCountryName(countryDetailsBo.getCountryName());
        }else{
            //TODO: set countrydetails
            countryDetails.setId(countryDetailsBo.getId());
            countryDetails.setCountryName(countryDetailsBo.getCountryName());
            countryDetails.setCountryCurrency(countryDetailsBo.getCountryCurrency());
            countryDetails.setCountryCode(countryDetailsBo.getCountryCode());
        }
        return countryDetails;
    }

}
