package com.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.entities.Publisher;

public interface IPublisherDao extends Remote {
	public boolean add(Publisher publisher) throws RemoteException;
	public List<Publisher> getAll() throws RemoteException;
	public Publisher findBySdt(String sdtPublisher) throws RemoteException;
	

}
