package com.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private long id;
	private String name;
	private Author author;
//	private Caterogy category;
	private int year;
	private int price;
	
}
