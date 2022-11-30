package com.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.entities.Book;

public interface IBook_dao extends Remote {
	public boolean add(Book b) throws RemoteException;
	public boolean delete(int bookId) throws RemoteException;
	public boolean update(int bookId, Book newBook) throws RemoteException;
	public List getAllBooks() throws RemoteException;
	public Book findById(int bookId) throws RemoteException;
}
