package com.upc.ejercicio.pc.book.domain.repository;

import com.upc.ejercicio.pc.book.domain.model.entity.Book;
import com.upc.ejercicio.pc.book.domain.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    boolean existsByCodeStudent(String codeStudent);
    boolean existsByCodeStudentAndBookAndBookLoan(String codeStudent, Book book, boolean bookLoan) ;
    List<Loan> findByCodeStudent(String codeStudent);
}
