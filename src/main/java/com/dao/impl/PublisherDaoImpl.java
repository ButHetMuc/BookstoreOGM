package com.dao.impl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import com.dao.IPublisherDao;
import com.entities.Category;
import com.entities.Publisher;
import com.utils.HibernateUtils;

public class PublisherDaoImpl implements IPublisherDao {
	private OgmSessionFactory sessionFactory ;
	
	public PublisherDaoImpl() {
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

}
