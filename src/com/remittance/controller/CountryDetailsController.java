package com.remittance.controller;


import com.remittance.bo.CountryDetailsBo;
import com.remittance.dao.CountryDetails;
import com.remittance.dao.CountryDetailsOperation;
import com.remittance.marshaller.CountryDetailsMarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(value = "/countryDetails")
public class CountryDetailsController {

    CountryDetailsMarshaller countryDetailsMarshaller = new CountryDetailsMarshaller();
    CountryDetailsOperation countryDetailsOperation = new CountryDetailsOperation();

    @RequestMapping(value="/save",method = RequestMethod.POST)
    @ResponseBody
    public CountryDetailsBo save(@RequestBody CountryDetailsBo countryDetailsBo) {

        CountryDetails countryDetails = countryDetailsMarshaller.boToDao(countryDetailsBo);
        countryDetailsOperation.save(countryDetails);
        countryDetailsBo = countryDetailsMarshaller.daoTOBo(countryDetails);

        return countryDetailsBo;
    }

}
