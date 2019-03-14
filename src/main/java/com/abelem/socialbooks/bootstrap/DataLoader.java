package com.abelem.socialbooks.bootstrap;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.abelem.socialbooks.domain.Author;
import com.abelem.socialbooks.domain.Book;
import com.abelem.socialbooks.domain.Comment;
import com.abelem.socialbooks.repository.AuthorsRepository;
import com.abelem.socialbooks.repository.BooksRepository;
import com.abelem.socialbooks.repository.CommentsRepository;

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private final BooksRepository booksRepository;
	
	@Autowired
	private final CommentsRepository commentsRepository;
	
	@Autowired
	private final AuthorsRepository authorsRepository;
	
	public DataLoader(BooksRepository booksRepository, CommentsRepository commentsRepository, 
				AuthorsRepository authorsRepository) {
		super();
		this.booksRepository = booksRepository;
		this.commentsRepository = commentsRepository;
		this.authorsRepository = authorsRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Book b1 = new Book("DDD: Domain Driven Design", new Date());
		Book b2 = new Book("Clean Code", new Date());
		Book b3 = new Book("Designer Thinks", new Date());
		Book b4 = new Book("Scrum Master", new Date());
		
		Comment c1 = new Comment(Comments.TEXT1, "abelem", new Date());
		Comment c2 = new Comment(Comments.TEXT2, "mohammed", new Date());
		Comment c3 = new Comment(Comments.TEXT3, "indianboy", new Date());
		Comment c4 = new Comment(Comments.TEXT4, "germandude", new Date());
		
		saveAllBooks(b1, b2, b3, b4);

		
		c1.setBook(b1);
		c2.setBook(b1);
		c3.setBook(b2);
		c4.setBook(b3);
		
		System.out.println("Loaded books...");
		
		commentsRepository.save(c1);
		commentsRepository.save(c2);
		commentsRepository.save(c3);
		commentsRepository.save(c4);
		
		saveAllComments(c1, c2, c3, c4);
		
		System.out.println("Loaded comments...");
		
		
		Date datePast = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		datePast = sdf.parse("01/01/1980");
		
		Author a1 = new Author("Eric Evans", "american", datePast);
		Author a2 = new Author("Martin Fowler", "american", datePast);
		Author a3 = new Author("Robert C. Martin", "american", datePast);
		Author a4 = new Author("Mert Çalışkan", "turkish", datePast);
		Author a5 = new Author("Gavin King", "american", datePast);
		Author a6 = new Author("James Gosling", "american", datePast);
		
		saveAllAuthors(a1, a2, a3, a4, a5, a6);
		
		System.out.println("Loaded authors...");
	}
	
	private void saveAllBooks(Book ...books) {
		for (Book book : books) {
			booksRepository.save(book);
		}
	}

	private void saveAllComments(Comment ...comments) {
		for (Comment comment : comments) {
			commentsRepository.save(comment);
		}
	}

	private void saveAllAuthors(Author ...authors) {
		for (Author author : authors) {
			authorsRepository.save(author);
		}
	}
	
	
	
}
