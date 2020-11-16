package com.loanlander.lending_engine.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public final class LoanApplication {
    @Id
    @GeneratedValue
    private long id  ;
    private  int amount ;
    @ManyToOne
    private  User borrower ;
    private  int repaymentTerm ;
    private  double interestRate ;


    public LoanApplication(int amount, User borrower, int repaymentTerm, double interestRate) {
        this.amount = amount ;
        this.borrower = borrower ;
        this.repaymentTerm = repaymentTerm ;
        this.interestRate = interestRate ;

    }
}
