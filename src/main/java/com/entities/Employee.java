package com.entities;

import javax.persistence.*;

import org.bson.types.ObjectId;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
	@Id
	private long id;
	private String name;
	private String socialId;
	private String phoneNumber;
	private String address;
	private Account acount; 
	
}
