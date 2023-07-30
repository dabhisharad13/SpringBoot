package com.tutorial.service;

import com.tutorial.model.InterestRate;
import com.tutorial.model.Loan;
import com.tutorial.repository.LoanRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {
    private static final String SERVICE_NAME = "loan-service";
    private static final String RATE_SERVICE_URL = "http://localhost:9000/api/rates/";
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "getDefaultLoans")
    public List<Loan> getAllLoansByType(String type) {
        InterestRate rate = restTemplate.getForObject(RATE_SERVICE_URL + type, InterestRate.class);
        List<Loan> loanList = new ArrayList<>();
        if (rate != null) {
            loanList = loanRepository.findByType(type);
            for (Loan loan : loanList) {
                loan.setInterest(loan.getAmount() * (rate.getRateValue() / 100));
            }
        }
        return loanList;
    }

    public List<Loan> getDefaultLoans(Exception e) {
        return new ArrayList<>();
    }
}
