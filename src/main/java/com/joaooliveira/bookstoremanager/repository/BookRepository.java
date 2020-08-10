package com.joaooliveira.bookstoremanager.repository;

import com.joaooliveira.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;/*biblioteca que disponibiliza e abstrai operações com o BD*/

public interface BookRepository extends JpaRepository<Book,Long> {
} /*classe criada para o gerenciamento da nossa entidade com o BD, ou seja,basicamente uma classe
que implementa o padrão arquitetural DAO(data access object)*/
