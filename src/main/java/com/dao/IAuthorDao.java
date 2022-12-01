package com.dao;

import org.bson.types.ObjectId;

import com.entities.Author;

public interface IAuthorDao {
	public boolean add(Author author);
	public Author findById(ObjectId authorId);
	public Author findBySdt(String sdtAuthor);

}
