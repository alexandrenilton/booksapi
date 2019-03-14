package com.abelem.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abelem.socialbooks.domain.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long>{

}
