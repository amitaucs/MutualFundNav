package com.amisoft.mutualfund.controller;


import com.amisoft.entities.MutualFund;
import com.amisoft.mutualfund.dao.MutualFundDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mutualfund")
public class MutualFundController {


    @Autowired
    MutualFundDao mutualFundDao;

    @GetMapping("/getAllMutualFund")
    public ResponseEntity<Optional<List<MutualFund>>> getAllMutualFund() {


        List<MutualFund> mutualFundList = (List<MutualFund>) mutualFundDao.findAll();
        if (mutualFundList.size() > 0)
            return ResponseEntity.status(HttpStatus.OK).body(Optional.of(mutualFundList));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.empty());
    }

}
