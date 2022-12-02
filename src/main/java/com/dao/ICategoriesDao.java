package com.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.entities.Category;

public interface ICategoriesDao extends Remote {
	public boolean add(Category category) throws RemoteException;
	public List getAll() throws RemoteException;
	public Category findByName(String nameCategory) throws RemoteException;
}	

