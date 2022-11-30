package com.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.entities.Book;

public interface BookDao {
	public boolean add(Book book);
	public boolean delete(Book book);
	public boolean update(Book book);
	public Book findById(ObjectId bookId);
	public List getAllBook();
}
