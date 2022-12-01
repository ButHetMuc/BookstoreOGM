package com.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.dao.BookDao;
import com.entities.Book;
import com.entities.Category;
import com.utils.HibernateUtils;

public class BookDaoImpl implements BookDao {
	private OgmSessionFactory sessionFactory;

	public BookDaoImpl() {
		sessionFactory = HibernateUtils.getInstance().getSessionFactory();
	}

	public boolean add(Book book) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			for (Category caterogy : book.getCategories()) {
				session.save(caterogy);
			}
			session.save(book.getAuthor());
			session.save(book.getPublisher());
			session.save(book);
			System.out.println("add book ok");
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean delete(ObjectId bookId) {

		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Book b = session.find(Book.class, bookId);
			session.delete(b);
			System.out.println("delete book ok");
			tr.commit();
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("delete book error");
			e.printStackTrace();
			tr.rollback();

		}

		return false;
	}

	@Override
	public boolean update(Book newBook) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();

			session.update(newBook);
			System.out.println("update book ok");
			tr.commit();
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("update err");
			e.printStackTrace();
			tr.rollback();
		}

		return false;
	}

	@Override
	public Book findById(ObjectId bookId) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Book b = null;

		try {
			NativeQuery<Book> query = session.createNativeQuery("db.books.find({'_id': ObjectId('"+bookId+"')})", Book.class);
			b = query.getSingleResult();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return b;
	}

	@Override
	public List<Book> getAllBook() {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Book> list = null;

		try {
			NativeQuery<Book> query = session.createNativeQuery("db.books.find({})", Book.class);
			list = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return list;
	}

	@Override
	public List findManyByName(String bookName) {
		// TODO Auto-generated method stub
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Book> list = null;

		try {
			NativeQuery<Book> query = session
					.createNativeQuery("db.books.find({'name': {'$regex': '.*" + bookName + ".*'}})", Book.class);
			list = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return list;
	}

	@Override
	public List findManyByAuthorName(String authorName) {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Book> list = null;

		try {
			NativeQuery<Book> query = session.createNativeQuery("db.books.find({})", Book.class);
			list = query.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return list;
	}

}
