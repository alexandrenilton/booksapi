package com.abelem.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abelem.socialbooks.domain.Book;
import com.abelem.socialbooks.domain.Comment;
import com.abelem.socialbooks.services.BooksService;
/**
 * REST using HATEOS 
 * @author alexandre.belem
 *
 */

@RestController
@RequestMapping("/books")
public class BooksResources {
	
	private List<Book> books;
	
	@Autowired
	private BooksService booksService;
	
	@GetMapping
	public List<Book> listAll(HttpServletResponse response) {
		books = booksService.findAll();
		response.setStatus(HttpServletResponse.SC_OK);
		return books;
	}
	
	@GetMapping(value="/{id}")
	public Book find(@PathVariable Long id, HttpServletResponse response) {
		Book book = booksService.findById(id);
		
		String headerValue = CacheControl.maxAge(30, TimeUnit.SECONDS)
                .getHeaderValue(); // ça veut dire: 10 seg
		
		response.setHeader("Cache-Control", headerValue);
		response.setStatus(HttpServletResponse.SC_OK);
		return book;
	}
	
	/** save **/
	@PostMapping
	public void save(@Valid @RequestBody Book book, HttpServletResponse response) {
		book = booksService.save(book);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(book.getId()).toUri();
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.setHeader("locattion", uri.toString());
	}
	
	/** delete **/
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		Book b = booksService.findById(id);
		booksService.delete(b);
		
		response.setStatus(HttpServletResponse.SC_NO_CONTENT); //sem conteudo para informar	
	}
	
	/** update **/
	@PutMapping(value="/{id}")
	public void update(@RequestBody Book book, @PathVariable Long id, HttpServletResponse response) {
		book.setId(id);
		booksService.update(book);
		
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	/** COMMENTS METHODS **/
	@PostMapping(value = "/{id}/comments")
	public void addComment(@PathVariable("id") Long bookId, @RequestBody Comment comment, HttpServletResponse response) {
		
		
		//capturar o context de segurança 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// mostra qual o user authenticado
		comment.setUser(auth.getName());
		
		booksService.saveComment(bookId, comment);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		System.out.println("----> URI.: " + uri);
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.setHeader("location", uri.toString());
	}

	@GetMapping(value = "/{id}/comments")
	public List<Comment> findCommentsByBook(@PathVariable("id") Long bookId, HttpServletResponse response) {
		booksService.findById(bookId);
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		return booksService.findCommentsByBook(bookId);
	}
	
}
