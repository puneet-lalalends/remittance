package com.remittance.controller;

import com.remittance.bo.RequestRemittanceBo;
import com.remittance.bo.ResponseRemittanceBo;
import com.remittance.service.RemittanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/calculateRemittance")
public class FetchRemittanceController {

    RemittanceService remittanceService = new RemittanceService();

    @RequestMapping(value="/fetch",method = RequestMethod.POST)
    @ResponseBody
    public List<ResponseRemittanceBo> save(@RequestBody RequestRemittanceBo requestRemittanceBo) {

        List<ResponseRemittanceBo> responseRemittanceBoList = remittanceService.fetchAndConvert(requestRemittanceBo);

        return responseRemittanceBoList;
    }
}
