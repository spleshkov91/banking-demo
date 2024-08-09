package com.slavapleshkov.springboot.bankingdemo.service.impl;

import com.slavapleshkov.springboot.bankingdemo.dto.AccountDto;
import com.slavapleshkov.springboot.bankingdemo.entity.Account;
import com.slavapleshkov.springboot.bankingdemo.mapper.AccountMapper;
import com.slavapleshkov.springboot.bankingdemo.repository.AccountRepository;
import com.slavapleshkov.springboot.bankingdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    public static final String ACCOUNT_NOT_EXIST = "Account does not exist";

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.
                findById(id).orElseThrow(() ->
                        new RuntimeException(ACCOUNT_NOT_EXIST));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.
                findById(id).orElseThrow(() ->
                        new RuntimeException(ACCOUNT_NOT_EXIST));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.
                findById(id).orElseThrow(() ->
                        new RuntimeException(ACCOUNT_NOT_EXIST));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insifficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.
                findById(id).orElseThrow(() ->
                        new RuntimeException(ACCOUNT_NOT_EXIST));
        accountRepository.delete(account);

    }
}
