package com.loanlander.lending_engine.application;

import com.loanlander.lending_engine.application.dto.LoanRequest;
import com.loanlander.lending_engine.domain.model.Loan;
import com.loanlander.lending_engine.domain.model.LoanApplication;
import com.loanlander.lending_engine.domain.model.User;
import com.loanlander.lending_engine.domain.repository.LoanApplicationRepository;
import com.loanlander.lending_engine.domain.repository.UserRepository;
import com.loanlander.lending_engine.domain.service.LoanAdapter;
import com.loanlander.lending_engine.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {
    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;
    private final LoanAdapter loanAdapter;
    private final LoanService loanService;
    @Autowired
    public LoanController(final LoanApplicationRepository loanApplicationRepository, UserRepository userRepository, LoanAdapter loanAdapter, LoanService loanService){
        this.loanApplicationRepository = loanApplicationRepository;
        this.userRepository = userRepository;
        this.loanAdapter = loanAdapter;
        this.loanService = loanService;
    }
    @PostMapping("/loan/req")
    public void reqLoan (@RequestBody final LoanRequest loanRequest){
        loanApplicationRepository.save(loanAdapter.transform(loanRequest));
    }
    @GetMapping("/users")
    public List<User> findUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/loan/reqs")
    public List<LoanApplication> findLoans(){
        return loanApplicationRepository.findAll();
    }
    @PostMapping("/loan/accept/{lender_id}/{loanApplication_id}")
    public void acceptLoan (@PathVariable String lender_id , @PathVariable String loanApplication_id){
        loanService.acceptLoan(Long.parseLong(lender_id) , Long.parseLong(loanApplication_id));
    }
    @GetMapping("/loans")
    public List<Loan> allLoans (){
        return loanService.findLoans();
    }
}

