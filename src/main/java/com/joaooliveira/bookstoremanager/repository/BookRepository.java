package com.joaooliveira.bookstoremanager.repository;

import com.joaooliveira.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
