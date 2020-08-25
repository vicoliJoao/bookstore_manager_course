package com.joaooliveira.bookstoremanager.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*classe criada(com os mesmos atributos de Book) para, no momento da criação do livro, caso haja algum erro,
não seja na camada de banco de dados - pelo fato do método create utilizar atributos da classe Book, que estão com anotações
 para a criação da entidade no BD - pois a mensagem não seria amigável*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    /*todas as anotações abaixo são validações da biblioteca bean validation,
    para caso haja algum erro, apareça uma mensagem antes mesmo de chegar na
    camada controller, nem chegando a camada do BD*/

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    private Integer pages;

    @NotNull
    private Integer chapters;

    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
            message = "ISBN format must be a valid format") /* Anotação Pattern utilizada com uma expressão regular para validar o formato isbn, caso esteja errado, volta uma mensagem */
    private String isbn;


    @NotBlank
    @Size(max = 200)
    private String publisherName;

    @Valid
    @NotNull
    private AuthorDTO author;
}
