package com.dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.dao.BillDao;
import com.entities.Bill;
import com.entities.BillDetails;
import com.entities.Book;
import com.entities.Customer;
import com.utils.HibernateUtils;

public class BillDaoImpl extends UnicastRemoteObject implements BillDao  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OgmSessionFactory sessionFactory;
	
	public BillDaoImpl() throws RemoteException {
		sessionFactory = HibernateUtils.getInstance().getSessionFactory();
	}
	@Override
	public boolean add(Bill bill)throws RemoteException {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
//			session.save(bill.getCustomer());
			for(BillDetails d : bill.getBillDetails()) {
				session.save(d);
			}
			session.save(bill);
			tr.commit();
			System.out.println("inserted bill");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	
	@Override
	public boolean update(Bill newBill)throws RemoteException {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update("bills",newBill);
			tr.commit();
			System.out.println("updated bill");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public Bill findById(ObjectId billId)throws RemoteException {
		return null;
	}
	@Override
	public boolean delete(ObjectId billId) {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Bill b = session.find(Bill.class, billId);
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
	public List<Bill> getAllBills()throws RemoteException {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		List<Bill> bills = null;

		try {
			tr.begin();
//			String query = "db.bills.find({})";
//			bills = session.createNativeQuery( query )
//			                      .addEntity( "Bill", Bill.class )
//			                      .list();
			NativeQuery<Bill> query = session.createNativeQuery("db.bills.find({})", Bill.class);
			bills = query.getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return bills;
	}
	@Override
	public List<Bill> findByCustomerName(String customerName)throws RemoteException {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List<Bill> list = null;

		try {
			
			NativeQuery<Bill> query = session.createNativeQuery("db.bills.find({'customer.name': {'$regex': '.*" + customerName + ".*'}})", Bill.class);
			list = query.getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return list;
	}
	@Override
	public List<Bill> findByCustomerPhonenumber(String CustomerPhonenumber) throws RemoteException{
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List<Bill> list = null;

		try {
			
			NativeQuery<Bill> query = session.createNativeQuery("db.bills.find({'customer.phoneNumber': {'$regex': '.*" + CustomerPhonenumber + ".*'}})", Bill.class);
			list = query.getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return list;
	}
	@Override
	public List<Customer> getCustomers() throws RemoteException {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List<Customer> list = null;

		try {
			String queryString = "db.bills.aggregate([{'$group':{'_id':'$customer.phoneNumber','name':{'$first':'$customer.name'}}}])";
			NativeQuery<Customer> query = session.createNativeQuery(queryString, Customer.class);
			list = query.getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return list;
	}

}
