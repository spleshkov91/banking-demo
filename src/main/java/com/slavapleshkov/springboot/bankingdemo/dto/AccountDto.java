package com.slavapleshkov.springboot.bankingdemo.dto;

import com.slavapleshkov.springboot.bankingdemo.entity.InsuranceType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String accountHolderName;
    private double balance;
    private List<InsuranceType> insuranceList;
}
