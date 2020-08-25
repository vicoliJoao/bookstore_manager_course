package com.joaooliveira.bookstoremanager.controller;

import com.joaooliveira.bookstoremanager.dto.BookDTO;
import com.joaooliveira.bookstoremanager.dto.MessageResponseDTO;
import com.joaooliveira.bookstoremanager.service.BookService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.joaooliveira.bookstoremanager.utils.BookUtils.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)/*ExtendWith-anotação do JUNIT 5, com a biblioteca mockito para podermos criar objetos que simulam a inclusão de novas classes ao sistema*/
public class BookControllerTest {
    public static final String BOOK_API_URL_PATH = "/api/v1/books";

    private MockMvc mockMvc; /*fará as simulações*/

    @Mock/*simular uma chamada ao atributo*/
    private BookService bookService;/*classe de dependência do BookController*/

    @InjectMocks/*injeção de dependência no mock*/
    private BookController bookController;

    /*incialização do objeto mockmvc*/
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test /*Quando o método POST é chamado, um livro é criado*/
    void testWhenPOSTisCalledThenABookShouldBeCreated() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();

        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Book created with ID " + bookDTO.getId())
                .build();

        when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);/*criado para dizer ao Mock o que ele deve retornar quando o método create do BookService é chamado em uma simulação.*/

        mockMvc.perform(post(BOOK_API_URL_PATH)/*chamada do mockmvc, com a simulação de uma chamada POST de argumento com com o endpoint*/
                .contentType(MediaType.APPLICATION_JSON) /*tipo de conteúdo esperado na entrada*/
                .content(asJsonString(bookDTO)))/*conteúdo da classe*/
                .andExpect(status().isOk())/*espera o status 200, que é isOk()*/
                .andExpect(jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));/*espera a mensagem do atributo message da classe MessageResponde*/

    }

    @Test
    void testWhenPOSTWithInvalidISBNIsCalledThenBadRequestShouldBeReturned() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();
        bookDTO.setIsbn("Invalid isbn");

        /*validar o como ele vai retornar o erro de bad request*/
        mockMvc.perform(post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookDTO)))
                .andExpect(status().isBadRequest());


    }

}
