package com.entities;

import javax.persistence.*;

import org.bson.types.ObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
	@Id
	private ObjectId id;
	private String name;
	private String phoneNumber;
	
	public Author(ObjectId objectId, String string) {
		// TODO Auto-generated constructor stub
	}
}
