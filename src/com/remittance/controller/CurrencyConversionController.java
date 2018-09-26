package com.remittance.controller;

import com.remittance.bo.CountryDetailsBo;
import com.remittance.bo.RequestCurrencyConversionBo;
import com.remittance.bo.ResponseCurrencyConversionBo;
import com.remittance.dao.CountryDetails;
import com.remittance.dao.CurrencyConversionDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(value = "/currencyConversion")
public class CurrencyConversionController {

    @RequestMapping(value="/fetch",method = RequestMethod.POST)
    @ResponseBody
    public ResponseCurrencyConversionBo fetch(@RequestBody RequestCurrencyConversionBo requestCurrencyConversionBo) {

        CurrencyConversionDao currencyConversionDao = new CurrencyConversionDao(requestCurrencyConversionBo);

        ResponseCurrencyConversionBo responseCurrencyConversionBo = currencyConversionDao.converCurrency();

        return responseCurrencyConversionBo;
    }

}
