package com.slavapleshkov.springboot.bankingdemo.dto;

import com.slavapleshkov.springboot.bankingdemo.entity.InsuranceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDto {

    private Long id;
    private InsuranceType insuranceType;
    private double insuranceLimit;
    private Long accountId;

}
