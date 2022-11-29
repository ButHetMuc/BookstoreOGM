package com.rmi.interfaces;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

import com.entities.Book;

public interface IBook_dao extends Remote {
	public boolean add(Book b);
	public boolean delete(int bookId);
	public boolean update(int bookId, Book newBook);
	public List getAllBooks();
	public Book findById(int bookId);
}
