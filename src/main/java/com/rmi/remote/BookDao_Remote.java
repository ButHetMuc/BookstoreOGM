package com.rmi.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import com.entities.Book;
import com.entities.Category;
import com.rmi.interfaces.IBook_dao;
import com.utils.HibernateUtils;

public class BookDao_Remote extends UnicastRemoteObject implements IBook_dao {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private OgmSessionFactory sessionFactory ;
	
	public BookDao_Remote() throws RemoteException {
		sessionFactory= HibernateUtils.getInstance().getSessionFactory();
	}


	@Override
	public boolean add(Book book) throws RemoteException {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			for (Category caterogy	: book.getCategories()) {
				session.save(caterogy);
			}
			session.save(book.getAuthor());
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
	public boolean delete(int bookId) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int bookId, Book newBook)throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getAllBooks() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findById(int bookId)  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

}
