package com.joaooliveira.bookstoremanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*classe da exceção*/
@ResponseStatus(HttpStatus.NOT_FOUND)/*Resposta not found http*/
public class BookNotFoundException extends Exception {

    public BookNotFoundException(Long id) {

        super(String.format("Book with Id not found", id)); /*quando recebe o id do livro não encontrado, passa
        no construtor uma mensagem direto para a exceção*/
    }
}
