package com.upc.ejercicio.pc.book.api.rest;

import com.upc.ejercicio.pc.book.api.dto.BookDTO;
import com.upc.ejercicio.pc.book.api.request.BookRequest;
import com.upc.ejercicio.pc.book.api.respone.BookResponse;
import com.upc.ejercicio.pc.book.domain.model.entity.Book;
import com.upc.ejercicio.pc.book.domain.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookResponse> bookResponses = books.stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        return ResponseEntity.ok(bookResponse);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {
        BookDTO bookDTO = modelMapper.map(bookRequest, BookDTO.class);
        Book createdBook = bookService.createBook(bookDTO);
        BookResponse bookResponse = modelMapper.map(createdBook, BookResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookRequest bookRequest) {
        BookDTO bookDTO = modelMapper.map(bookRequest, BookDTO.class);
        Book updatedBook = bookService.updateBook(id, bookDTO);
        BookResponse bookResponse = modelMapper.map(updatedBook, BookResponse.class);
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}