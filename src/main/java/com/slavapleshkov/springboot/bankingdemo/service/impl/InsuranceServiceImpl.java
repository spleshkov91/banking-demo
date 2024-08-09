package com.slavapleshkov.springboot.bankingdemo.service.impl;

import com.slavapleshkov.springboot.bankingdemo.dto.InsuranceDto;
import com.slavapleshkov.springboot.bankingdemo.entity.Account;
import com.slavapleshkov.springboot.bankingdemo.entity.Insurance;
import com.slavapleshkov.springboot.bankingdemo.entity.InsuranceType;
import com.slavapleshkov.springboot.bankingdemo.mapper.InsuranceMapper;
import com.slavapleshkov.springboot.bankingdemo.repository.AccountRepository;
import com.slavapleshkov.springboot.bankingdemo.repository.InsuranceRepository;
import com.slavapleshkov.springboot.bankingdemo.service.InsuranceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, AccountRepository accountRepository) {
        this.insuranceRepository = insuranceRepository;
        this.accountRepository = accountRepository;
    }

    public boolean existsByInsuranceTypeAndAccountId(InsuranceType insuranceType, Long accountId) {
        return insuranceRepository.existsByInsuranceTypeAndAccountId(insuranceType, accountId);
    }

    @Override
    @Transactional
    public InsuranceDto createInsurance(InsuranceDto insuranceDto) {
        Account account = accountRepository.findById(insuranceDto.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        Insurance insurance = InsuranceMapper.mapToInsurance(insuranceDto, account);
        insuranceRepository.save(insurance);

        return InsuranceMapper.mapToInsuranceDto(insurance);
    }

    @Override
    public InsuranceDto getInsuranceById(Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Insurance not found with id: " + id));

        return InsuranceMapper.mapToInsuranceDto(insurance);
    }

    @Override
    public InsuranceDto increaseLimit(Long id, double insuranceLimit) {
        if (insuranceLimit <= 0) {
            throw new IllegalArgumentException("Insurance limit must be positive.");
        }

        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Insurance not found with id: " + id));
        double totalInsuranceLimit = insurance.getInsuranceLimit() + insuranceLimit;
        insurance.setInsuranceLimit(totalInsuranceLimit);

        Insurance savedInsurance = insuranceRepository.save(insurance);

        return InsuranceMapper.mapToInsuranceDto(savedInsurance);
    }

    @Override
    public InsuranceDto reduceLimit(Long id, double insuranceLimit) {
        if (insuranceLimit <= 0) {
            throw new IllegalArgumentException("Insurance limit must be positive.");
        }

        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Insurance not found with id: " + id));
        double totalInsuranceLimit = insurance.getInsuranceLimit() - insuranceLimit;
        insurance.setInsuranceLimit(totalInsuranceLimit);

        Insurance savedInsurance = insuranceRepository.save(insurance);

        return InsuranceMapper.mapToInsuranceDto(savedInsurance);
    }
}
