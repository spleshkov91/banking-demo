package com.slavapleshkov.springboot.bankingdemo.repository;

import com.slavapleshkov.springboot.bankingdemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
