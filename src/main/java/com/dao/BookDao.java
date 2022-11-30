package com.dao;

import java.util.List;

import com.entities.Book;

public interface BookDao {
	public boolean add(Book book);
	public boolean delete(Book book);
	public boolean update(Book book);
	public boolean findById(long bookId);
	public List getAllBook();
}
