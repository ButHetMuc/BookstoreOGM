package com.dao;

import java.util.List;

import com.entities.Publisher;

public interface IPublisherDao {
	public boolean add(Publisher publisher);
	public List<Publisher> getAll();
	

}
