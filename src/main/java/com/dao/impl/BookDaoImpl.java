package com.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.dao.BookDao;
import com.dao.IAuthorDao;
import com.dao.IPublisherDao;
import com.entities.Author;
import com.entities.Book;
import com.entities.Category;
import com.entities.Publisher;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllBook() {
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
			List<Book> tmpList = query.getResultList();
			if(authorName.equals("")) {
				tx.commit();
				return tmpList;
			}
			
			list = new ArrayList<Book>();
			for (Book book : tmpList) {
				if(book.getAuthor().getName().equals(authorName)) {
					list.add(book);
				}
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return list;
	}

	@Override
	public List findManyByPublisherName(String publisherName) {
		
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Book> list = null;

		try {
			NativeQuery<Book> query = session.createNativeQuery("db.books.find({})", Book.class);
			List<Book> tmpList = query.getResultList();
			if(publisherName.equals("")) {
				tx.commit();
				return tmpList;
			}
			
			list = new ArrayList<Book>();
			for (Book book : tmpList) {
				if(book.getPublisher().getName().equals(publisherName)) {
					list.add(book);
				}
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return list;
	}
	
	

}
