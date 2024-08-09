package com.slavapleshkov.springboot.bankingdemo.mapper;

import com.slavapleshkov.springboot.bankingdemo.dto.AccountDto;
import com.slavapleshkov.springboot.bankingdemo.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        return new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
    }

    public static AccountDto mapToAccountDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getAccountholderName(),
                account.getBalance()
        );
    }
}
