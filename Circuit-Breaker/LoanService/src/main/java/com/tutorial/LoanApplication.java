package com.tutorial;

import com.tutorial.model.Loan;
import com.tutorial.repository.LoanRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class LoanApplication {
    @Autowired
    private LoanRepository loanRepository;

    public static void main(String[] args) {
        SpringApplication.run(LoanApplication.class, args);
    }

    @PostConstruct
    public void setupData() {
        loanRepository.saveAll(Arrays.asList(
                Loan.builder().id(1).type("PERSONAL").amount(200000.0).interest(0.0).build(),
                Loan.builder().id(2).type("HOUSING").amount(6000000.0).interest(0.0).build(),
                Loan.builder().id(3).type("PERSONAL").amount(100000.0).interest(0.0).build()
        ));
    }
}