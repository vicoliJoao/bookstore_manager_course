package com.joaooliveira.bookstoremanager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/*classe criada para que na hora da criaçao do livro, tenha uma mensagem de volta para o ususário*/
public class MessageResponseDTO {

    private String message;
}
