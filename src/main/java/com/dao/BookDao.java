package com.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.entities.Book;

public interface BookDao extends Remote {
	public boolean add(Book book) throws RemoteException;
	public boolean delete(ObjectId bookId) throws RemoteException;
	public boolean update(Book newBook) throws RemoteException;
	public Book findById(ObjectId bookId) throws RemoteException;
	public List<Book> getAllBook() throws RemoteException;
	public List findManyByName(String bookName) throws RemoteException;
	public List findManyByAuthorName(String authorName) throws RemoteException;
	public List findManyByPublisherName(String publisherName) throws RemoteException;

}
