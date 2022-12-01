package com.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
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
public class Book implements Serializable {
	
	@Id
	private ObjectId id;
	private String name;
	@OneToOne
	private Author author;
	
	@OneToMany (fetch = FetchType.EAGER)
	private Set<Category> categories = new HashSet<Category>();
	
	@OneToOne
	private Publisher publisher;
	private int year;
	private int price;
	private int quantity;
	
	
	

	
}
