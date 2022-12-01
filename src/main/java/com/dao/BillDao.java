package com.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.entities.Bill;
import com.entities.Book;

public interface BillDao extends Remote {
	public boolean add(Bill bill) throws RemoteException;
	public boolean delete(ObjectId billId)throws RemoteException;
	public boolean update(Bill newBill)throws RemoteException;
	public Bill findById(ObjectId bookId)throws RemoteException;
	public List<Bill> getAllBills()throws RemoteException;
	public List<Bill> findByCustomerName(String customerName)throws RemoteException;
	public List<Bill> findByCustomerPhonenumber(String CustomerPhonenumber)throws RemoteException;
}
