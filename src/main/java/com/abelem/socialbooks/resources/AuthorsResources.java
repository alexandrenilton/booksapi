package com.abelem.socialbooks.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abelem.socialbooks.domain.Author;
import com.abelem.socialbooks.services.AuthorsService;


@RestController
@RequestMapping("/authors")
public class AuthorsResources {

	@Autowired
	private AuthorsService authorsService;
	
	@GetMapping
	public List<Author> findAll(HttpServletResponse response) {
		List<Author> authors = authorsService.findAll();
		response.setStatus(HttpServletResponse.SC_OK);
		return authors;
	}
	
	@PostMapping
	public Author save(@Valid @RequestBody Author author, HttpServletResponse response) {
		Author authorSaved = authorsService.save(author);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(authorSaved.getId()).toUri();
		
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.setHeader("location", uri.toString());
		
		return authorSaved;
	}
	
	@GetMapping(value = "{id}")
	public Author findById(@PathVariable Long id, HttpServletResponse response) {
		Author author = authorsService.findById(id);
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		return author;
	}
}
