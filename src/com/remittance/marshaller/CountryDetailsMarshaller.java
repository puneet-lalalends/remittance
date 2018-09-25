package com.remittance.marshaller;

import com.remittance.bo.CountryDetailsBo;
import com.remittance.dao.CountryDetails;

public class CountryDetailsMarshaller {

    public CountryDetailsBo daoTOBo(CountryDetails countryDetails) {
        //TODO: Convert data
        CountryDetailsBo countryDetailsBo = new CountryDetailsBo();
        return countryDetailsBo;
    }

    public CountryDetails boToDao(CountryDetailsBo countryDetailsBo){
        return boToDao(countryDetailsBo,null);
    }

    public CountryDetails boToDao(CountryDetailsBo countryDetailsBo,CountryDetails countryDetails){

        if(countryDetails == null){
            //TODO: Convert Data
            countryDetails = new CountryDetails();
        }else{
            //TODO: set countrydetails
        }
        return countryDetails;
    }

}
