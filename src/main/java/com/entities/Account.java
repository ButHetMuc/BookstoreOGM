package com.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@Id
	private ObjectId id;
	private String username;
	private String password;
}
