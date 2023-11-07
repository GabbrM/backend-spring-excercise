package com.upc.ejercicio.pc.book.api.respone;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanResponse {
    private Long id;
    private Long bookId;
    private String codeStudent;
    private LocalDate loanDate;
    private LocalDate devolutionDate;
}