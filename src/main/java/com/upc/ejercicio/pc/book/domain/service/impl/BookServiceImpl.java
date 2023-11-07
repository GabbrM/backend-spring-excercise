package com.upc.ejercicio.pc.book.domain.service.impl;

import com.upc.ejercicio.pc.book.api.dto.BookDTO;
import com.upc.ejercicio.pc.shared.exception.model.ResourceNotFoundException;
import com.upc.ejercicio.pc.book.domain.model.entity.Book;
import com.upc.ejercicio.pc.book.domain.repository.BookRepository;
import com.upc.ejercicio.pc.book.domain.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        }
        throw new ResourceNotFoundException("Book not found with id: " + id);
    }

    @Override
    public Book createBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = getBookById(id);
        modelMapper.map(bookDTO, existingBook);
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }

    @Override
    public BookDTO convertToDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public Book convertToEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    @Override
    public BookDTO getBookDTOById(Long requestedBookId) {
        Book book = bookRepository.findById(requestedBookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + requestedBookId));
        return modelMapper.map(book, BookDTO.class);
    }
}