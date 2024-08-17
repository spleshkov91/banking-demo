package com.slavapleshkov.springboot.bankingdemo.service.impl;

import com.slavapleshkov.springboot.bankingdemo.dto.AccountDto;
import com.slavapleshkov.springboot.bankingdemo.entity.Account;
import com.slavapleshkov.springboot.bankingdemo.entity.Insurance;
import com.slavapleshkov.springboot.bankingdemo.entity.InsuranceType;
import com.slavapleshkov.springboot.bankingdemo.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test void testCreateAccount_Success() {
        // Задаем входные данные
        List<InsuranceType> insuranceList = Arrays.asList(InsuranceType.MEDICAL, InsuranceType.PENSION);
        List<Insurance> insurances = Arrays.asList(new Insurance(InsuranceType.MEDICAL)
        ,new Insurance(InsuranceType.PENSION));
        AccountDto accountDto = new AccountDto(1L, "John Doe", 1000.0, insuranceList);

        Account account = new Account();
        account.setId(1L);
        account.setAccountholderName("John Doe");
        account.setBalance(1000.0);
        account.setInsuranceList(insurances);

        // Задаем поведение мока
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // Вызываем тестируемый метод
        AccountDto result = accountService.createAccount(accountDto);

        // Проверяем результат
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getAccountHolderName());
        assertEquals(1000.0, result.getBalance(), 0.01);
        assertEquals(insuranceList, result.getInsuranceList());

        // Проверяем, что метод save был вызван один раз
        verify(accountRepository, times(1)).save(any(Account.class));
    }



    @Test
    void createAccount() {
    }

    @Test
    void getAccountById() {
    }

    @Test
    void deposit() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void getAllAccounts() {
    }

    @Test
    void deleteAccount() {
    }
}