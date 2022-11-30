package com.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import com.dao.BillDao;
import com.entities.Bill;
import com.entities.BillDetails;
import com.utils.HibernateUtils;

public class BillDaoImpl implements BillDao {

	private OgmSessionFactory sessionFactory;
	
	public BillDaoImpl() {
		sessionFactory = HibernateUtils.getInstance().getSessionFactory();
	}
	@Override
	public boolean add(Bill bill) {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
//			session.save(bill.getCustomer());
			session.save(bill);
			tr.commit();
			System.out.println("inserted bill");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Bill bill) {
		OgmSession session= sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.delete(bill);
			tr.commit();
			System.out.println("deleted");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Bill bill) {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(bill.getCustomer());
//			for(BillDetails details : bill.getBillDetails()) {
//				session.save(details);
//			}
			session.update("bills",bill);
			tr.commit();
			System.out.println("updated bill");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Bill findById(ObjectId billId) {
		return null;
	}

	@Override
	public List getAllBills() {
		// TODO Auto-generated method stub
		return null;
	}

}
