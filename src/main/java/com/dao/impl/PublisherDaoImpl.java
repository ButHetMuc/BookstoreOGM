package com.dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.dao.IPublisherDao;
import com.entities.Author;
import com.entities.Bill;
import com.entities.Book;
import com.entities.Category;
import com.entities.Publisher;
import com.utils.HibernateUtils;

public class PublisherDaoImpl extends UnicastRemoteObject implements IPublisherDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OgmSessionFactory sessionFactory ;
	
	public PublisherDaoImpl() throws RemoteException {
		sessionFactory= HibernateUtils.getInstance().getSessionFactory();
	}
	
	@Override
	public boolean add(Publisher publisher) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			
			session.save(publisher);
			System.out.println("add publisher ok");
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("add publisher error");
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<Publisher> getAll() {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		List<Publisher> pubs = null;

		try {
			tr.begin();
//			String query = "db.bills.find({})";
//			bills = session.createNativeQuery( query )
//			                      .addEntity( "Bill", Bill.class )
//			                      .list();
			NativeQuery<Publisher> query = session.createNativeQuery("db.publishers.find({})", Publisher.class);
			pubs = query.getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return pubs;
	}

	@Override
	public Publisher findBySdt(String sdtPublisher) {
		// TODO Auto-generated method stub
		System.out.println(sdtPublisher);
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Publisher publisher = null;

		try {
			String sql = "db.publishers.find({'phoneNumber': {'$regex': '.*" + sdtPublisher + ".*'}})";
			NativeQuery<Publisher> query = session
					.createNativeQuery(sql,Publisher.class);
			publisher = query.getSingleResult();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return publisher;
	}

	@Override
	public boolean delete(ObjectId pubId) throws RemoteException {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Publisher b = session.find(Publisher.class, pubId);
			session.delete(b);
			tr.commit();
			System.out.println("deleted publiser");
			return true;
		} catch (Exception e) {
			System.out.println("delete publisher error");
			e.printStackTrace();
			tr.rollback();

		}
		return false;
	}

	@Override
	public boolean update(Publisher pub) throws RemoteException {
		OgmSession session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update("bills",pub);
			tr.commit();
			System.out.println("updated publisher");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<Publisher> findByName(String namePub) throws RemoteException {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Publisher> list = null;

		try {
			NativeQuery<Publisher> query = session
					.createNativeQuery("db.publishers.find({'name': {'$regex': '.*" + namePub + ".*'}})", Publisher.class);
			list = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return list;
	}

}
