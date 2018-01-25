package com.amisoft.mutualfund.controller;


import com.amisoft.entities.MutualFund;
import com.amisoft.mutualfund.dao.MutualFundDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mutualfund")
public class MutualFundController {


    @Autowired
    MutualFundDao mutualFundDao;

    @GetMapping("/getAllMutualFund")
    public ResponseEntity<List<MutualFund>> getAllMutualFund() {


        List<MutualFund> mutualFundList = (List<MutualFund>) mutualFundDao.findAll();
        if (mutualFundList.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body((mutualFundList));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/byFundName")
    public ResponseEntity<MutualFund> getFundByName(@RequestParam(value="name") String fundName) {


        MutualFund mutualFund = mutualFundDao.findMutualFundBySchemaName(fundName);
        if (null != mutualFund)
            return ResponseEntity.status(HttpStatus.OK).body((mutualFund));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @GetMapping("/byFundManager")
    public ResponseEntity<List<MutualFund>> getFundByFundManager(@RequestParam(value="name") String fundManagerName) {


        List<MutualFund> mutualFundList = mutualFundDao.findAllByFundManager(fundManagerName);
        if (mutualFundList.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body((mutualFundList));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @GetMapping("/bySchemaCode")
    public ResponseEntity<List<MutualFund>> getFundBySchemaCode(@RequestParam(value="name") String schemaCode) {


        List<MutualFund> mutualFundList = mutualFundDao.findAllBySchemaCode(schemaCode);
        if (mutualFundList.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body((mutualFundList));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


}
