package com.dao;

import org.bson.types.ObjectId;

import com.entities.Author;

public interface IAuthorDao {
	public boolean add(Author author);
	public boolean findById(ObjectId authorId);

}
