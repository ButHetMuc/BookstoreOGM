package com.dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.dao.IPublisherDao;
import com.entities.Author;
import com.entities.Category;
import com.entities.Publisher;
import com.utils.HibernateUtils;

public class PublisherDaoImpl extends UnicastRemoteObject implements IPublisherDao {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher findBySdt(String sdtPublisher) {
		// TODO Auto-generated method stub
		System.out.println(sdtPublisher);
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Publisher publisher = null;

		try {
			String sql = "db.publishers.find({'phoneNumber': '" + sdtPublisher + "'})";
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

}
