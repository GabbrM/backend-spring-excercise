package com.upc.ejercicio.pc.book.api.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateLoanRequest {
    private Long requestedBookId;
    private String codeStudent;
    private Long userId;
    private LocalDate startDate;
    private LocalDate returnDate;
    private boolean bookLoan;
}