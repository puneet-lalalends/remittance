package com.remittance.controller;


import com.remittance.bo.CountryDetailsBo;
import com.remittance.dao.CountryDetails;
import com.remittance.dao.CountryDetailsOperation;
import com.remittance.marshaller.CountryDetailsMarshaller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @RequestMapping(value="/retrieveAll",method = RequestMethod.POST)
    @ResponseBody
    public List<CountryDetailsBo> retrieveAll() {

        List boList = new ArrayList();
        List countryDetailsList = countryDetailsOperation.reteriveAll();
        Iterator itr = countryDetailsList.iterator();
        while (itr.hasNext()){
            boList.add(countryDetailsMarshaller.daoTOBo((CountryDetails) itr.next()));
        }
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
        return boList;
    }

}
