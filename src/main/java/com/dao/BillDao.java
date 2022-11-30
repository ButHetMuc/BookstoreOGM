package com.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.entities.Bill;

public interface BillDao {
	public boolean add(Bill bill);
	public boolean delete(Bill bill);
	public boolean update(Bill bill);
	public Bill findById(ObjectId billId);
	public List<Bill> getAllBills();

}
