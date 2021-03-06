package com.joaooliveira.bookstoremanager.service;

import com.joaooliveira.bookstoremanager.dto.BookDTO;
import com.joaooliveira.bookstoremanager.entity.Book;
import com.joaooliveira.bookstoremanager.exception.BookNotFoundException;
import com.joaooliveira.bookstoremanager.repository.BookRepository;
import com.joaooliveira.bookstoremanager.utils.BookUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static com.joaooliveira.bookstoremanager.utils.BookUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        Book expectedFoundBook = createFakeBook();

        when(bookRepository.findById(expectedFoundBook.getId()))
                .thenReturn(Optional.of(expectedFoundBook));/*criado para dizer ao Mock o que ele deve
                retornar quando o método findById do BookRepository é chamado em uma simulação.*/

        BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());

        /*confirmação dos teste com alguns dados, o atributo do expectedFoundBook
        tem que ser igual ao do BookDTO por conta do assertEquals*/
        assertEquals(expectedFoundBook.getName(), bookDTO.getName());
        assertEquals(expectedFoundBook.getIsbn(), bookDTO.getIsbn());
        assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());

    }

    @Test
    void whenGivenUnexistingIdThenNotFindThrowAnException() {
        long invalidId = 10L;

        when(bookRepository.findById(invalidId))
                .thenReturn(Optional.ofNullable(any(Book.class)));/*quando o método findById der invalido, então ele retorna um objeto nulo de qualquer instância da classe Book*/

        assertThrows(BookNotFoundException.class, () -> bookService.findById(invalidId));/*lança a exceção quando for passado um findById inválido*/
    }
}
