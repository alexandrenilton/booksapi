package com.abelem.socialbooks.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.abelem.socialbooks.domain.Book;
import com.abelem.socialbooks.domain.Comment;
import com.abelem.socialbooks.exceptions.BookNotFoundException;
import com.abelem.socialbooks.repository.BooksRepository;
import com.abelem.socialbooks.repository.CommentsRepository;

@Service
public class BooksServiceImpl implements BooksService{
	
	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;
	

	public BooksServiceImpl(BooksRepository booksRepository, CommentsRepository commentsRepository) {
		super();
		this.booksRepository = booksRepository;
		this.commentsRepository = commentsRepository;
	}

	public List<Book> findAll() {
		return booksRepository.findAll();
	}
	
	@Override
	public Book findById(Long id) {
		Book book = booksRepository.findById(id).orElse(null);
		if (book == null) {
			throw new BookNotFoundException("Book not found.");
		}
		return book;
	}

	@Override
	public Book save(Book book) {
		book.setId(null); // garantir que está salvando algo novo
		return booksRepository.save(book);
	}

	@Override
	public void delete(Book book) {
		try {
			booksRepository.delete(book);
		} catch(EmptyResultDataAccessException e) {
			throw new BookNotFoundException("Book not found!");
		}
	}
	
	@Override
	public void update(Book book) {
		checkExistence(book);
		booksRepository.save(book);
	}
	
	/** comments **/
	@Override
	public Comment saveComment(Long bookId, Comment comment) {
		Book book = findById(bookId);
		
		comment.setBook(book);
		comment.setDate(new Date());
		
		return commentsRepository.save(comment);
	}
	
	@Override
	public List<Comment> findCommentsByBook(Long bookId) {
		Book book  = findById(bookId);
		
		return book.getComments();
	}
	
	/**
	 * Just for check if a book exists in database.
	 * @param book
	 */
	private void checkExistence(Book book) {
		findById(book.getId()); //se nao achar, vai lançar exception
	}


	
}
