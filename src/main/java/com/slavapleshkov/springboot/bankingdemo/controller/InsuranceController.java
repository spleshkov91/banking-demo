package com.slavapleshkov.springboot.bankingdemo.controller;

import com.slavapleshkov.springboot.bankingdemo.dto.InsuranceDto;
import com.slavapleshkov.springboot.bankingdemo.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping
    public ResponseEntity<?> addInsurance(@RequestBody InsuranceDto insuranceDto) {

        boolean exists = insuranceService.existsByInsuranceTypeAndAccountId(
                insuranceDto.getInsuranceType(),
                insuranceDto.getAccountId()
        );
        if (exists) {
            String errorMessage = "Insurance type already exists for this account.";
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }
        InsuranceDto createdInsurance = insuranceService.createInsurance(insuranceDto);
        return new ResponseEntity<>(createdInsurance, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceDto> getInsurenceById(@PathVariable Long id) {
        InsuranceDto insuranceDto = insuranceService.getInsuranceById(id);
        return ResponseEntity.ok(insuranceDto);
    }

    @PutMapping("/{id}/increase-limit")
    public ResponseEntity<InsuranceDto> increaseInsuranceLimit(
            @PathVariable Long id,
            @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        InsuranceDto updatedInsurance = insuranceService.increaseLimit(id, amount);
        return ResponseEntity.ok(updatedInsurance);
    }

    @PutMapping("/{id}/reduce-limit")
    public ResponseEntity<InsuranceDto> reduceInsuranceLimit(
            @PathVariable Long id,
            @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        InsuranceDto updatedInsurance = insuranceService.reduceLimit(id, amount);
        return ResponseEntity.ok(updatedInsurance);
    }
}
