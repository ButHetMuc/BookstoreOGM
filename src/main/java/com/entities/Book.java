package com.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "Books")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Book {
	
	@Id
	private ObjectId id;
	private String name;
	@OneToOne
	private Author author;
	
	@OneToMany
	private Set<Category> caterogies = new HashSet<Category>();
	private int year;
	private int price;

}
