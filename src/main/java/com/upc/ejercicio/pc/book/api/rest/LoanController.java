package com.upc.ejercicio.pc.book.api.rest;


import com.upc.ejercicio.pc.book.api.dto.BookDTO;
import com.upc.ejercicio.pc.book.api.dto.LoanDTO;
import com.upc.ejercicio.pc.book.api.request.CreateLoanRequest;
import com.upc.ejercicio.pc.book.api.response.LoanResponse;
import com.upc.ejercicio.pc.book.domain.model.entity.Book;
import com.upc.ejercicio.pc.book.domain.model.entity.Loan;
import com.upc.ejercicio.pc.book.domain.service.BookService;
import com.upc.ejercicio.pc.book.domain.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/library/v1")
public class LoanController {
    private final LoanService loanService;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    @Autowired
    public LoanController(LoanService loanService, BookService bookService, ModelMapper modelMapper) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        List<LoanResponse> loanResponses = loans.stream()
                .map(loan -> modelMapper.map(loan, LoanResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(loanResponses);
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanResponse> getLoanById(@PathVariable("id") Long id) {
        Loan loan = loanService.getLoanById(id);
        LoanResponse loanResponse = modelMapper.map(loan, LoanResponse.class);
        return ResponseEntity.ok(loanResponse);
    }

    @PostMapping("/loans")
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody CreateLoanRequest loanRequest) {
        BookDTO bookDTO = bookService.getBookDTOById(loanRequest.getRequestedBookId());
        Book book = modelMapper.map(bookDTO, Book.class);
        LoanDTO loan = LoanDTO.builder()
                .codeStudent(loanRequest.getCodeStudent())
                .loanDate(loanRequest.getStartDate())
                .devolutionDate(loanRequest.getReturnDate())
                .bookLoan(loanRequest.isBookLoan())
                .bookId(loanRequest.getRequestedBookId())
                .build();
        Loan createdLoan = loanService.createLoan(loan);
        LoanResponse loanResponse = modelMapper.map(createdLoan, LoanResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanResponse);
    }

    @PutMapping("/loans/{id}")
    public ResponseEntity<LoanResponse> updateLoan(@PathVariable("id") Long id, @Valid @RequestBody CreateLoanRequest loanRequest) {
        LoanDTO loanDTO = modelMapper.map(loanRequest, LoanDTO.class);
        loanDTO.setBookId(loanRequest.getRequestedBookId());
        Loan updatedLoan = loanService.updateLoan(id, loanDTO);
        LoanResponse loanResponse = modelMapper.map(updatedLoan, LoanResponse.class);
        return ResponseEntity.ok(loanResponse);
    }
    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable("id") Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}