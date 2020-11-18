package com.loanlander.lending_engine.application;

import com.loanlander.lending_engine.application.dto.LoanRequest;
import com.loanlander.lending_engine.application.service.TokenValidationService;
import com.loanlander.lending_engine.domain.model.Loan;
import com.loanlander.lending_engine.domain.model.LoanApplication;
import com.loanlander.lending_engine.domain.model.User;
import com.loanlander.lending_engine.domain.repository.LoanApplicationRepository;
import com.loanlander.lending_engine.domain.repository.UserRepository;
import com.loanlander.lending_engine.domain.service.LoanAdapter;
import com.loanlander.lending_engine.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoanController {
    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;
    private final LoanAdapter loanAdapter;
    private final LoanService loanService;
    private final TokenValidationService tokenValidationService ;
    @Autowired
    public LoanController(final LoanApplicationRepository loanApplicationRepository, UserRepository userRepository, LoanAdapter loanAdapter, LoanService loanService, TokenValidationService tokenValidationService){
        this.loanApplicationRepository = loanApplicationRepository;
        this.userRepository = userRepository;
        this.loanAdapter = loanAdapter;
        this.loanService = loanService;
        this.tokenValidationService = tokenValidationService;
    }
    @PostMapping("/loan/req")
    public void reqLoan (@RequestBody final LoanRequest loanRequest , HttpServletRequest request){
        User borrower = tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanApplicationRepository.save(loanAdapter.transform(loanRequest , borrower));
    }
    @GetMapping("/users")
    public List<User> findUsers(HttpServletRequest request){
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return userRepository.findAll();
    }
    @GetMapping("/loan/reqs")
    public List<LoanApplication> findLoans(HttpServletRequest request){
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanApplicationRepository.findAll();
    }
    @PostMapping("/loan/accept/{lender_id}/{loanApplication_id}")
    public void acceptLoan (@PathVariable String lender_id , @PathVariable String loanApplication_id , HttpServletRequest request){
       User lander = tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanService.acceptLoan(lander.getUsername() , Long.parseLong(loanApplication_id));
        // id is a string
    }
    @GetMapping("/loans")
    public List<Loan> allLoans (HttpServletRequest request){
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanService.findLoans();
    }
}

