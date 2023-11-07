package com.upc.ejercicio.pc.book.domain.service;

import com.upc.ejercicio.pc.book.api.dto.LoanDTO;
import com.upc.ejercicio.pc.book.domain.model.entity.Loan;

import java.util.List;

public interface LoanService {
    LoanDTO convertToDTO(Loan loan);
    Loan convertToEntity(LoanDTO loanDTO);
    Loan createLoan(LoanDTO loanDTO);
    Loan getLoanById(Long id);
    List<Loan> getAllLoans();
    Loan updateLoan(Long id, LoanDTO loanDTO);
    void deleteLoan(Long id);
}