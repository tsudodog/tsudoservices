package com.tsudo.fraud;

import com.tsudo.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraudcheck")
@AllArgsConstructor
@Slf4j
public class FraudCheckController {
    private FraudCheckService fraudCheckService;

    @GetMapping(path="{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId){
       boolean isFraudulentCustomer =  fraudCheckService.isFraudulentCustomer(customerId);

       log.info("fraud check request for customer {}", customerId);
       return new FraudCheckResponse(isFraudulentCustomer);
    }
}
