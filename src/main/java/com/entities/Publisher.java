package com.entities;

import java.io.Serializable;

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
@Table(name = "publishers")
public class Publisher implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private ObjectId id;
	private String name;
	@Column(unique = true)
	private String phoneNumber;
	private String address;
}
