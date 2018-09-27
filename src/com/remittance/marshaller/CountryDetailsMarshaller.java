package com.remittance.marshaller;

import com.remittance.bo.CountryDetailsBo;
import com.remittance.dao.CountryDetails;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CountryDetailsMarshaller {

    public CountryDetailsBo daoTOBo(CountryDetails countryDetails) {
        CountryDetailsBo countryDetailsBo = new CountryDetailsBo();

        countryDetailsBo.setId(countryDetails.getId());
        countryDetailsBo.setCountryCode(countryDetails.getCountryCode());
        countryDetailsBo.setCountryCurrency(countryDetails.getCountryCurrency());
        countryDetailsBo.setCountryName(countryDetails.getCountryName());

        try {
            String str = "C:\\Users\\user\\Desktop\\download.jpg";
            File file = new File(str); // java.io.File
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);
//            countryDetailsBo.setInputStreamResource(inputStreamResource);
            countryDetailsBo.setCounryFlag(IOUtils.toByteArray(fileInputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }

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
