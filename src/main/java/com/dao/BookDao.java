package com.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.entities.Book;

public interface BookDao {
	public boolean add(Book book);
	public boolean delete(ObjectId bookId);
	public boolean update(Book newBook);
	public Book findById(ObjectId bookId);
	public List<Book> getAllBook();
	public List findManyByName(String bookName);
	public List findManyByAuthorName(String authorName);
}
