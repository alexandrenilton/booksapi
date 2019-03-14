package com.abelem.socialbooks.services;

import java.util.List;

import com.abelem.socialbooks.domain.Book;
import com.abelem.socialbooks.domain.Comment;

public interface BooksService extends CrudService<Book, Long>{

	List<Book> findAll();

	Book findById(Long id);

	Book save(Book book);

	void delete(Book book);
	
	void update(Book book);
	
	Comment saveComment(Long bookId, Comment comment);
	
	List<Comment> findCommentsByBook(Long id);
}
