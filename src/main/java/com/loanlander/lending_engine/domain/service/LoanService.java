package com.loanlander.lending_engine.domain.service;

import com.loanlander.lending_engine.domain.expection.LoanApplicationNotFoundException;
import com.loanlander.lending_engine.domain.expection.UserNotFoundExeption;
import com.loanlander.lending_engine.domain.model.Loan;
import com.loanlander.lending_engine.domain.model.LoanApplication;
import com.loanlander.lending_engine.domain.model.User;
import com.loanlander.lending_engine.domain.repository.LoanApplicationRepository;
import com.loanlander.lending_engine.domain.repository.LoanRipository;
import com.loanlander.lending_engine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanApplicationRepository loanApplicationRepository ;
    private final LoanRipository loanRipository ;
    private final UserRepository userRepository ;

    @Autowired
    public LoanService(LoanApplicationRepository loanApplicationRepository, LoanRipository loanRipository, UserRepository userRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.loanRipository = loanRipository;
        this.userRepository = userRepository;
    }
    public void acceptLoan (String lender_id , Long loanApplication_id){
        User lender = userRepository.findById(lender_id).orElseThrow(()-> new UserNotFoundExeption(lender_id));
        LoanApplication loanApplication = loanApplicationRepository.findById(loanApplication_id).orElseThrow(()-> new LoanApplicationNotFoundException(loanApplication_id));
        loanRipository.save(new Loan(lender,loanApplication));
    }
    public List<Loan> findLoans(){
        return loanRipository.findAll();
    }
}
