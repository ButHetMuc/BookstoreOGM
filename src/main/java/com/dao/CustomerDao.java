package com.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.entities.Book;
import com.entities.Customer;

public interface CustomerDao {
	public boolean add(Customer customer);
	public boolean delete(Customer customer);
	public boolean update(Customer customer);
	public Book findById(ObjectId cusId);
	public List<Customer> getAllCustomers();
}
