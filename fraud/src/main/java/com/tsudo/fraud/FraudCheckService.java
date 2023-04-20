package com.tsudo.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public record FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {


    public boolean isFraudulentCustomer(Integer customerId){
        log.info("fraud check took place");
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build());

        return false;
    }
}
