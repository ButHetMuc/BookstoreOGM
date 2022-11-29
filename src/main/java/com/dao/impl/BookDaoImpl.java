package com.dao.impl;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import com.dao.BookDao;
import com.entities.Book;
import com.utils.HibernateUtils;

public class BookDaoImpl implements BookDao{
	private OgmSessionFactory sessionFactory ;
	
	public BookDaoImpl() {
		sessionFactory= HibernateUtils.getInstance().getSessionFactory();
	}
	@Override
	public boolean addBook(Book book) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(book);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

}
