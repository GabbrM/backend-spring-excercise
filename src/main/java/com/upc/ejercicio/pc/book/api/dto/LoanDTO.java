package com.upc.ejercicio.pc.book.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
    private Long loanId;
    private Long bookId;
    private String codeStudent;
    private LocalDate loanDate;
    private LocalDate devolutionDate;
    private boolean bookLoan;
    private BookDTO book;
}