package com.upc.ejercicio.pc.book.domain.service.impl;

import com.upc.ejercicio.pc.book.api.dto.BookDTO;
import com.upc.ejercicio.pc.book.api.dto.LoanDTO;
import com.upc.ejercicio.pc.shared.exception.model.ResourceNotFoundException;
import com.upc.ejercicio.pc.book.domain.model.entity.Book;
import com.upc.ejercicio.pc.book.domain.model.entity.Loan;
import com.upc.ejercicio.pc.book.domain.repository.LoanRepository;
import com.upc.ejercicio.pc.book.domain.service.BookService;
import com.upc.ejercicio.pc.book.domain.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    private final ModelMapper modelMapper;
    private final LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(ModelMapper modelMapper, LoanRepository loanRepository) {
        this.modelMapper = modelMapper;
        this.loanRepository = loanRepository;
    }

    @Autowired
    private BookService bookService;

    @Override
    public LoanDTO convertToDTO(Loan loan) {
        return modelMapper.map(loan, LoanDTO.class);
    }

    @Override
    public Loan convertToEntity(LoanDTO loanDTO) {
        return modelMapper.map(loanDTO, Loan.class);
    }

    @Override
    public Loan createLoan(LoanDTO loanDTO) {
        Loan loan = modelMapper.map(loanDTO, Loan.class);

        // Obtener el libro correspondiente al ID proporcionado
        BookDTO bookDTO = bookService.getBookDTOById(loanDTO.getBookId());
        Book book = convertToBook(bookDTO);

        // Asignar el libro al préstamo
        loan.setBook(book);

        // Guardar el préstamo en la base de datos
        return loanRepository.save(loan);
    }
    @Override
    public Loan getLoanById(Long id) {
        // Lógica para obtener un préstamo por su ID usando el repositorio
        return loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));
    }

    @Override
    public List<Loan> getAllLoans() {
        // Lógica para obtener todos los préstamos usando el repositorio
        return loanRepository.findAll();
    }

    @Override
    public Loan updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = getLoanById(id);
        modelMapper.map(loanDTO, loan);
        return loanRepository.save(loan);
    }

    private Book convertToBook(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    @Override
    public void deleteLoan(Long id) {
        Loan loan = getLoanById(id);
        loanRepository.delete(loan);
    }

}