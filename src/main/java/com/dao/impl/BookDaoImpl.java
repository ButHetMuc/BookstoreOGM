package com.dao.impl;

import java.util.Iterator;

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
	 public boolean add(Book book) {
//		OgmSession session = sessionFactory.getCurrentSession();
//		Transaction tr = session.getTransaction();
//		try {
//			tr.begin();
////			for (Caterogy caterogy	: book.getCaterogies()) {
////				session.save(caterogy);
////			}
//			session.save(book.getAuthor());
//			session.save(book);
//			tr.commit();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			tr.rollback();
//		}
		return false;
	}
	
	@Override
	public boolean delete(Book book) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(Book book) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean findById(long bookId) {
		// TODO Auto-generated method stub
		return false;
	}

}
