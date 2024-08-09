package com.slavapleshkov.springboot.bankingdemo.mapper;

import com.slavapleshkov.springboot.bankingdemo.dto.AccountDto;
import com.slavapleshkov.springboot.bankingdemo.entity.Account;
import com.slavapleshkov.springboot.bankingdemo.entity.Insurance;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        return new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                accountDto.getInsuranceList().stream()
                        .map(insuranceType -> new Insurance(insuranceType)) // Преобразование типа в объект
                        .collect(Collectors.toList())
        );
    }

    public static AccountDto mapToAccountDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getAccountholderName(),
                account.getBalance(),
                account.getInsuranceList().stream()
                        .map(Insurance::getInsuranceType)
                        .collect(Collectors.toList())
        );
    }
}
