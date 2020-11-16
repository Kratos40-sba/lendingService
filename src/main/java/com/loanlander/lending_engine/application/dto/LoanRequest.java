package com.loanlander.lending_engine.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class LoanRequest {
    private  int amount ;
    private long borrower_id ;
    private int daysTorepay ;
    private  double interestRate ;
}
