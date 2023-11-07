package com.upc.ejercicio.pc.book.api.response;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String editorial;
}