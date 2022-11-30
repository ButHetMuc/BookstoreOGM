package com.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.bson.types.ObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "books")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Book {
	
	@Id
	private ObjectId id;
	private String name;
	@OneToOne
	private Author author;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Category> caterogies = new HashSet<Category>();
	private int year;
	private int price;
	
	public Book(String name, Author author, int year, int price) {
		super();
		this.name = name;
		this.author = author;
		this.year = year;
		this.price = price;
	}
	
}
