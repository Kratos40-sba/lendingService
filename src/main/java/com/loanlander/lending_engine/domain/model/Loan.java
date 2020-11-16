package com.loanlander.lending_engine.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue
    private long id ;
    @ManyToOne
    private User borrower ;
    @ManyToOne
    private User lender ;
    private int amount ;
    private double interestRate ;
    private LocalDate dateLent ;
    private LocalDate dateDue ;
    public Loan(User lender , LoanApplication loanApplication){
        this.lender = lender ;
        this.borrower = loanApplication.getBorrower();
        this.interestRate = loanApplication.getInterestRate();
        this.amount = loanApplication.getAmount();
        this.dateLent = LocalDate.now();
        this.dateDue = LocalDate.now().plusDays(loanApplication.getRepaymentTerm());
    }
}
