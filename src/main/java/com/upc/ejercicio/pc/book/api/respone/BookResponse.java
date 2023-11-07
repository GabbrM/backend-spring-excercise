package com.upc.ejercicio.pc.book.api.respone;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String editorial;
}