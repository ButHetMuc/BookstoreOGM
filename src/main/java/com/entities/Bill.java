package com.entities;

import java.util.ArrayList;
import java.util.Date;

public class Bill {
	private long id;
	private Date createAt;
	private double total;
	
	private Employee employee;
	private Customer customer;
	private ArrayList<BillDetails> billDetais = new ArrayList<BillDetails>();
}
