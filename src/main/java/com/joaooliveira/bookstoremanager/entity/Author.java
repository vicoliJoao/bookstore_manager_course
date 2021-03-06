package com.joaooliveira.bookstoremanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/*os '@' são anotações JPA para fazer o mapeamento tanto da classe, quanto dos atributos, na tabela e na coluna, respectivamente*/
@Entity /*indicar que a classe é uma entidade*/
@Data/*anotação que gera getter, setter e equal and hash code em tempo de compilação*/
@Builder/*gera uma classe no padrão de projeto builders, utilizado para construção de objetos de forma segura*/
@NoArgsConstructor/*construtor sem argumentos*/
@AllArgsConstructor/*construtor com argumento*/
public class Author {
    @Id /*indica chave primária*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*estratégia de geração da chave primária, toda vez vez terá um Identity gerado automaticamente,
    ou seja, toda vez que houver uma inserção no BD, automaticamente terá uma chave incrementada*/
    private Long id;

    @Column(nullable = false, unique = true) /*coluna no BD; não pode retornar null e a entrada do nome do autor tem que ser única, se for diferente, vai dar erro*/
    private String name;

    @Column(nullable = false)/*tabela no BD; a entrada não pode ser null*/
    private Integer age;
}
