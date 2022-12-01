package com.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.dao.CustomerDao;
import com.entities.Bill;
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
	
	public boolean delete(ObjectId customerId) {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Customer b = session.find(Customer.class, customerId);
			session.delete(b);
			tr.commit();
			System.out.println("deleted bill");
			return true;
		} catch (Exception e) {
			System.out.println("delete book error");
			e.printStackTrace();
			tr.rollback();

		}
		return false;
	}

	@Override
	public boolean update(Customer customer) {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(customer);
			tr.commit();
			System.out.println("updated customer");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findByPhonenumber(String phonenumber) {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Customer cus= null;

		try {
			
			NativeQuery<Customer> query = session.createNativeQuery("db.customers.find({'phoneNumber': {'$regex': '.*" + phonenumber + ".*'}})", Customer.class);
			cus = (Customer) query.getSingleResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

}
