package com.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import com.dao.CustomerDao;
import com.entities.Book;
import com.entities.Customer;
import com.utils.HibernateUtils;

public class CustomerDaoImpl implements CustomerDao {
	private OgmSessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

	@Override
	public boolean add(Customer customer) {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(customer);
			tr.commit();
			System.out.println("inserted bill");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Book findById(ObjectId cusId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

}
