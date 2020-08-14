package com.joaooliveira.bookstoremanager.service;

import com.joaooliveira.bookstoremanager.dto.BookDTO;
import com.joaooliveira.bookstoremanager.dto.MessageResponseDTO;
import com.joaooliveira.bookstoremanager.entity.Book;
import com.joaooliveira.bookstoremanager.exception.BookNotFoundException;
import com.joaooliveira.bookstoremanager.mapper.BookMapper;
import com.joaooliveira.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create (BookDTO bookDTO){
        Book bookToSave = bookMapper.toModel(bookDTO);

        Book savedBook = bookRepository.save(bookToSave);
        return MessageResponseDTO.builder()
                .message("Book created with ID " + savedBook.getId())
                .build();
    }

    public BookDTO findById(long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

       return bookMapper.toDTO(book);

    }
}
