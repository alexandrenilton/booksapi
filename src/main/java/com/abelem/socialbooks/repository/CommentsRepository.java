package com.abelem.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abelem.socialbooks.domain.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Long>{

}
