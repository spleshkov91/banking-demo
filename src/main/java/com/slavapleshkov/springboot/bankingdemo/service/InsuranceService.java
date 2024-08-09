package com.slavapleshkov.springboot.bankingdemo.service;

import com.slavapleshkov.springboot.bankingdemo.dto.InsuranceDto;
import com.slavapleshkov.springboot.bankingdemo.entity.InsuranceType;

public interface InsuranceService {

    InsuranceDto createInsurance(InsuranceDto insuranceDto);

    InsuranceDto getInsuranceById(Long id);

    InsuranceDto increaseLimit(Long id, double insuranceLimit);

    InsuranceDto reduceLimit(Long id, double insuranceLimit);

    boolean existsByInsuranceTypeAndAccountId(InsuranceType insuranceType, Long accountId);


}
