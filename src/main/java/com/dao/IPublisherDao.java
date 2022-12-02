package com.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.entities.Publisher;

public interface IPublisherDao extends Remote {
	public boolean add(Publisher publisher) throws RemoteException;
	public boolean delete(ObjectId pubId) throws RemoteException;
	public boolean update(Publisher pub) throws RemoteException; 
	public List<Publisher> getAll() throws RemoteException;
	public Publisher findBySdt(String sdtPublisher) throws RemoteException;
	public List<Publisher> findByName(String namePub) throws RemoteException;
	

}
