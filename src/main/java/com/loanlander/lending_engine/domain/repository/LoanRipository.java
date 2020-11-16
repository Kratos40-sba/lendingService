package com.loanlander.lending_engine.domain.repository;

import com.loanlander.lending_engine.domain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRipository extends JpaRepository<Loan , Long> {
}
