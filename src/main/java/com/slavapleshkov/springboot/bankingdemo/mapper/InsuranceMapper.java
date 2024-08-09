package com.slavapleshkov.springboot.bankingdemo.mapper;

import com.slavapleshkov.springboot.bankingdemo.dto.InsuranceDto;
import com.slavapleshkov.springboot.bankingdemo.entity.Account;
import com.slavapleshkov.springboot.bankingdemo.entity.Insurance;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InsuranceMapper {

    public static InsuranceDto mapToInsuranceDto(Insurance insurance) {
        if (insurance == null) {
            return null;
        }
        return new InsuranceDto(
                insurance.getId(),
                insurance.getInsuranceType(),
                insurance.getInsuranceLimit(),
                insurance.getAccount() != null ? insurance.getAccount().getId() : null
        );
    }

    public static Insurance mapToInsurance(InsuranceDto insuranceDto, Account account) {
        if (insuranceDto == null) {
            return null;
        }
        Insurance insurance = new Insurance();
        insurance.setId(insuranceDto.getId());
        insurance.setInsuranceType(insuranceDto.getInsuranceType());
        insurance.setInsuranceLimit(insuranceDto.getInsuranceLimit());
        insurance.setAccount(account);
        return insurance;
    }
}
