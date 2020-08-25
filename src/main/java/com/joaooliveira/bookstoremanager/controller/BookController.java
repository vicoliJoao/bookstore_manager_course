package com.joaooliveira.bookstoremanager.controller;


import com.joaooliveira.bookstoremanager.dto.BookDTO;
import com.joaooliveira.bookstoremanager.dto.MessageResponseDTO;
import com.joaooliveira.bookstoremanager.exception.BookNotFoundException;
import com.joaooliveira.bookstoremanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*clase criada para receber operações http pela interação do usuário*/

@RestController/*indica que esta classe para o spring vai
representar um controller,onde vai ser uma api rest, e esse bean vai ser gerenciado pelo proprio spring*/
@RequestMapping("/api/v1/books")/*indica o endereço da nossa aplicação, o argumento é o endpoint, no padrão rest*/
public class BookController {

    private BookService bookService;

    @Autowired/*usada para injeção de dependência de um atributo de outra classe, nesse caso,
    injeção da classe BookService para BookControler. Não precisando usar o new para instanciar*/
    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    @PostMapping/*Marca uma operação POST / RequestBody indica que a operação
    POST deverá passar com um corpo específico, no caso, do tipo BookDTO / Valid para fazer a aplicação de todas as anotações bean validation feitas na classe BookDTO*/
    public MessageResponseDTO create (@RequestBody @Valid BookDTO bookDTO){

        return bookService.create(bookDTO);/*pega o o método de criação(create) do BookService*/
    }

    @GetMapping("/{id}")/*método retorna o livro pela ID / GetMapping traduz o código numa chamada http GET, com argumento sendo o caminho para essa chamada GET, diferente do caminho da chamada POST*/
    public BookDTO findById(@PathVariable Long id) throws BookNotFoundException {

        return bookService.findById(id);
    }
}
