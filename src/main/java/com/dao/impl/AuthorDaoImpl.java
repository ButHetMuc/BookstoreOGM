package com.dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.dao.IAuthorDao;
import com.entities.Author;
import com.entities.Book;
import com.utils.HibernateUtils;

public class AuthorDaoImpl extends UnicastRemoteObject implements IAuthorDao {
	
	private OgmSessionFactory sessionFactory;

	public AuthorDaoImpl() throws RemoteException {
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
	public Author findById(ObjectId authorId) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

	@Override
	public Author findBySdt(String sdtAuthor) {
		// TODO Auto-generated method stub
		System.out.println(sdtAuthor);
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Author author = null;

		try {
			String sql = "db.authors.find({'phoneNumber': '" + sdtAuthor + "'})";
			NativeQuery<Author> query = session
					.createNativeQuery(sql,Author.class);
			author = query.getSingleResult();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return author;
	}
	
}
