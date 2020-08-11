package com.joaooliveira.bookstoremanager.entity;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private Integer chapters;

    @Column(nullable = false)
    private String isbn;


    @Column(name = "publisher_name", nullable = false, unique = true)/* o nome da coluna publisherName utiliza o padrão camel case, por isso precisou ser mapeado de forma mais direta no BD para ele pegar o padrão name = "publisher_name"*/
    private String publisherName;

    /*aqui mapeamos direto a entidade, por isso não usamos @Column*/
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST ,CascadeType.MERGE, CascadeType.REMOVE} )/*indica que vamos mapear muitos para um, um livro - um autor/um autor-vários livros,mas como no nosso sistema o autor não vai gerenciar o livro, vamos fazer um redirecionamento unidirecional para o livro ter um único autor. Estratégias usadas:FetchType.LAZY-para fazer uma consulta do livro e de seu autor,precisará ser usado um JOIN para ter retorno, a vantagem é otimizar a performance; cascade-toda vez que inserir um livro, será inserido um autor(caso informado), ou seja, não precisamos chamar duas operações, uma pra inserir livro e outra pra autor.PERSIST-inserção,MERGE-atualização,REMOVE-remoção, toda vez que criarmos, atualizarmos e removermos um dado no BD, automaticamente na mesma operação de livro, um autor será tanto criado, atualizado e removido */
    @JoinColumn(name = "author_id")/* vai dizer qual coluna(no caso, author_id) no BD vai fazer o relacionamento, que vai ser a chave estrangeira para a tabela de Author*/
    private Author author;
}