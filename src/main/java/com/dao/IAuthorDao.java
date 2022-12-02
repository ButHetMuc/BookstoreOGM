package com.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.entities.Author;

public interface IAuthorDao  extends Remote{
	public boolean add(Author author) throws RemoteException;
	public Author findById(ObjectId authorId) throws RemoteException;
	public Author findBySdt(String sdtAuthor) throws RemoteException;

}
