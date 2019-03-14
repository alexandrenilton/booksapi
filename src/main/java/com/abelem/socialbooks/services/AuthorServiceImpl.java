package com.abelem.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abelem.socialbooks.domain.Author;
import com.abelem.socialbooks.exceptions.AuthorExistsException;
import com.abelem.socialbooks.exceptions.AuthorNotFoundException;
import com.abelem.socialbooks.repository.AuthorsRepository;

@Service
public class AuthorServiceImpl implements AuthorsService {

	@Autowired
	private AuthorsRepository authorsRepository;

	@Override
	public List<Author> findAll() {
		return authorsRepository.findAll();
	}

	@Override
	public Author findById(Long id) {
		Author author = authorsRepository.findById(id).orElse(null);
		if (author == null) {
			throw new AuthorNotFoundException("Author not found.");
		}
		
		return author;
	}

	@Override
	public Author save(Author author) {
		if (author.getId() != null) {
			Author a = authorsRepository.findById(author.getId()).orElse(null);

			if (a != null) {
				throw new AuthorExistsException("Author already exist.");
			}
		}
		return authorsRepository.save(author);
	}

	@Override
	public void delete(Author author) {
		authorsRepository.delete(author);
	}

	@Override
	public void update(Author author) {
		authorsRepository.save(author);
	}

}
