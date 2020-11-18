package com.loanlander.lending_engine.domain.service;

import com.loanlander.lending_engine.application.dto.LoanRequest;
import com.loanlander.lending_engine.domain.expection.UserNotFoundExeption;
import com.loanlander.lending_engine.domain.model.LoanApplication;
import com.loanlander.lending_engine.domain.model.User;
import com.loanlander.lending_engine.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanAdapter {
    private final UserRepository userRepository;
    public LoanAdapter(final UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public LoanApplication transform(LoanRequest request , User borrower){
        Optional<User> optionalUser = userRepository.findById(borrower.getUsername());
        if(optionalUser.isPresent()){
            return new LoanApplication(request.getAmount(),optionalUser.get(),request.getDaysTorepay(),request.getInterestRate());
        }else {
            throw new UserNotFoundExeption(borrower.getUsername());

        }

    }
}
