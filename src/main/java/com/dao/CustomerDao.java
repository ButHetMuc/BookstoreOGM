package com.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.entities.Book;
import com.entities.Customer;

public interface CustomerDao {
	public boolean add(Customer customer);
	public boolean delete(ObjectId customerId);
	public boolean update(Customer customer);
	public List<Customer> getAllCustomers();
	public Customer findByPhonenumber(String phonenumber);
}
