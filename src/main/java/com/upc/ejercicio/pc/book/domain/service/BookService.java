package com.upc.ejercicio.pc.book.domain.service;

import com.upc.ejercicio.pc.book.api.dto.BookDTO;
import com.upc.ejercicio.pc.book.domain.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(BookDTO bookDTO);
    Book updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);
    BookDTO convertToDTO(Book book);
    Book convertToEntity(BookDTO bookDTO);

    BookDTO getBookDTOById(Long requestedBookId);
}