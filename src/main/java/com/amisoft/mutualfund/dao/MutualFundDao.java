package com.amisoft.mutualfund.dao;

import com.amisoft.entities.MutualFund;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface MutualFundDao extends CrudRepository<MutualFund,Long> {


     MutualFund findMutualFundBySchemaName(String fundName);
     List<MutualFund> findAllByFundManager(String fundManager);
     List<MutualFund> findAllBySchemaCode(String fundManager);


}
