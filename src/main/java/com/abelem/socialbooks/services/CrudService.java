package com.abelem.socialbooks.services;

import java.util.List;

public interface CrudService<T, ID> {
	
	List<T> findAll();
	
	T findById(ID id);
	
	T save (T object);
	
	void delete (T object);
	
	void update (T object);
}
