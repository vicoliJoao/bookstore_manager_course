package com.joaooliveira.bookstoremanager.repository;

import com.joaooliveira.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;/*biblioteca que disponibiliza e abstrai operações com o BD*/

/*classe criada ,que segue o padrão DAO(data access object) - responsável por
toda criação, consulta,etc, direto da entidade-,
para fazer o gerenciamento de todas as entidades com o BD, com apoio da
biblioteca Spring Data JPA*/
public interface BookRepository extends JpaRepository<Book,Long> {

}