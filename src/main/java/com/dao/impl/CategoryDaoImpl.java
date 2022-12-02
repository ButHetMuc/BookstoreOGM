package com.dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.query.NativeQuery;

import com.dao.ICategoriesDao;
import com.entities.Book;
import com.entities.Category;
import com.utils.HibernateUtils;

public class CategoryDaoImpl extends UnicastRemoteObject implements ICategoriesDao {
	private OgmSessionFactory sessionFactory;

	public CategoryDaoImpl() throws RemoteException {
		sessionFactory = HibernateUtils.getInstance().getSessionFactory();
	}

	@Override
	public boolean add(Category category) throws RemoteException {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			
			session.save(category);
			
			tr.commit();
			System.out.println("add category ok");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("add cate error");
			e.printStackTrace();
			tr.rollback();
		}
		
		return false;
	}

	@Override
	public List getAll() throws RemoteException {
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<Category> list = null;

		try {
			tr.begin();
			NativeQuery<Category> query = session.createNativeQuery("db.categories.find({})", Category.class);
			list = query.getResultList();

			tr.commit();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("get all category err");
			tr.rollback();
		}

		return list;
	}

	@Override
	public Category findByName(String nameCategory) throws RemoteException {
		// TODO Auto-generated method stub
		OgmSession session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		Category category = null;
		System.out.println("here");
		try {
			tr.begin();
			String sql = "db.categories.find({'name': '" + nameCategory + "'})";
			NativeQuery<Category> query = session.createNativeQuery(sql, Category.class);
			category = query.getSingleResult();

			tr.commit();

		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}

		return category;
	}

	
}
