package com.loanlander.lending_engine.domain.expection;

public class LoanApplicationNotFoundException  extends RuntimeException{
    public LoanApplicationNotFoundException(Long loanApplication_id){
        super("LoanApplication with Id: "+loanApplication_id+"Not Found");
    }
}
