package com.remittance.controller;


import com.remittance.bo.PartnerInfoBo;
import com.remittance.bo.RequestRemittanceBo;
import com.remittance.bo.ResponseRemittanceBo;
import com.remittance.dao.PartnerInfo;
import com.remittance.dao.PartnerInfoOperation;
import com.remittance.marshaller.PartnerInfoMarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/partnerInfo")
public class PartnerInfoController {

    PartnerInfoMarshaller partnerInfoMarshaller = new PartnerInfoMarshaller();
    PartnerInfoOperation partnerInfoOperation = new PartnerInfoOperation();

    @RequestMapping(value="/save",method = RequestMethod.POST)
    @ResponseBody
    public PartnerInfoBo save(@RequestBody PartnerInfoBo partnerInfoBo) {

        PartnerInfo partnerInfo = partnerInfoMarshaller.boToDao(partnerInfoBo);
        partnerInfoOperation.save(partnerInfo);
        partnerInfoBo = partnerInfoMarshaller.daoTOBo(partnerInfo);

        return partnerInfoBo;
    }

}
