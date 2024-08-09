package com.slavapleshkov.springboot.bankingdemo.repository;

import com.slavapleshkov.springboot.bankingdemo.entity.Insurance;
import com.slavapleshkov.springboot.bankingdemo.entity.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    boolean existsByInsuranceTypeAndAccountId(InsuranceType insuranceType, Long accountId);
}
