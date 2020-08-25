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

/*classe criada para fazer a integração com o BD(criação do livro), desacoplando essa responsa
da classe BookControler, facilitando, assim, a criação de testes*/

@Service/*anotação do spring, diz ao framework que essa classe será gerenciada
pelo spring e ela terá outros serviços que podem ser injetados, como o controle transacional, por exemplo.*/
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create (BookDTO bookDTO){
        Book bookToSave = bookMapper.toModel(bookDTO);/*transforma tipo BookDTO para Book, vem da classe BookMaper. Instanciação de uma entidade completa convertida a partir de um DTO*/

        Book savedBook = bookRepository.save(bookToSave);/*cria o livro*/

        return MessageResponseDTO.builder()/*.builder.build() vem do lombalk, para construção do objeto sem o new*/
                .message("Book created with ID " + savedBook.getId())/*chama o atributo message da classe
                MessageResponseDTO atribuindo uma mensagem junto com o ID do livro que acabou de ser criado*/
                .build();
    }

    public BookDTO findById(long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));/*caso não retorne um livro pelo id, vai ser chamada uma exceção.*/

       return bookMapper.toDTO(book);

    }

}
