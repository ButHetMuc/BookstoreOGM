package com.dao.impl;

import org.bson.types.ObjectId;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import com.dao.IAuthorDao;
import com.entities.Author;
import com.utils.HibernateUtils;

public class AuthorDaoImpl implements IAuthorDao {
	
	private OgmSessionFactory sessionFactory;

	public AuthorDaoImpl() {
		sessionFactory= HibernateUtils.getInstance().getSessionFactory();
	}

	@Override
	public boolean add(Author author) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(author);
			tr.commit();
			System.out.println("add author ok");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean findById(ObjectId authorId) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
