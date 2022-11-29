package com.entities;

import java.util.ArrayList;
import java.util.Date;

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
@Table(name = "bills")
public class Bill {
	@Id
	private long id;
	private Date createAt;
	private double total;
	
	private Employee employee;
	private Customer customer;
	private ArrayList<BillDetails> billDetais = new ArrayList<BillDetails>();
}
