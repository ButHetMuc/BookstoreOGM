package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class Bill implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ObjectId id;
	private Date createAt;
	private double total;
	@Embedded
	private Customer customer;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<BillDetails> billDetails = new ArrayList<BillDetails>();
}
