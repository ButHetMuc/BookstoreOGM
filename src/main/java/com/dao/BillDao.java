package com.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.entities.Bill;
import com.entities.Book;

public interface BillDao {
	public boolean add(Bill bill);
	public boolean delete(ObjectId billId);
	public boolean update(Bill newBill);
	public Bill findById(ObjectId bookId);
	public List<Bill> getAllBills();
	public List<Bill> findByCustomerName(String customerName);
	public List<Bill> findByCustomerPhonenumber(String CustomerPhonenumber);
}
