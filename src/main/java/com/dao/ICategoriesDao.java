package com.dao;

import java.util.List;

import com.entities.Category;

public interface ICategoriesDao {
	public boolean add(Category category);
	public List getAll();
}	
