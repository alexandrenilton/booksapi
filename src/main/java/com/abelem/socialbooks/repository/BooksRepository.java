package com.abelem.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abelem.socialbooks.domain.Book;

public interface BooksRepository extends JpaRepository<Book, Long>{

}
