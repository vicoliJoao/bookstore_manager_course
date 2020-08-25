package com.joaooliveira.bookstoremanager.dto;


import com.joaooliveira.bookstoremanager.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*classe criada(com os mesmos atributos de Author) para, no momento da criação do livro, caso haja algum erro,
não seja na camada de banco de dados - pelo fato do método create utilizar atributos da classe Book, que estão com anotações
 para a criação da entidade no BD - pois a mensagem não seria amigável*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO{

    private Long id;

    /*todas as anotações abaixo são validações da biblioteca bean validation,
    para caso haja algum erro, apareça uma mensagem antes mesmo de chegar na
    camada controller, nem chegando a camada do BD*/

    @NotBlank /*não pode ser vazio*/
    @Size(max = 200) /*tam máx de 200 caracteres*/
    private String name;

    @NotNull /*não pode ser nulo*/
    @Size(max = 200)
    private Integer age;
}
