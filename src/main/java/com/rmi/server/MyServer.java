package com.rmi.server;

import java.rmi.registry.*;

import org.hibernate.hql.ast.origin.hql.parse.HQLParser.new_key_return;

import com.dao.BillDao;
import com.dao.BookDao;
import com.dao.IAuthorDao;
import com.dao.ICategoriesDao;
import com.dao.IPublisherDao;
import com.dao.impl.AuthorDaoImpl;
import com.dao.impl.BillDaoImpl;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.CategoryDaoImpl;
import com.dao.impl.PublisherDaoImpl;
import com.rmi.interfaces.IBook_dao;
import com.rmi.remote.BookDao_Remote;
import com.utils.Constants;


public class MyServer {
	public static void main(String args[]) {
		try {
			
			Registry rgsty = LocateRegistry.createRegistry(Constants.PORT_RMI);
			

			
			// rebind stub
			BillDao billDao = new BillDaoImpl();
			IAuthorDao authorDao = new AuthorDaoImpl();
			BookDao bookDao = new BookDaoImpl();
			IPublisherDao publisherDao = new PublisherDaoImpl ();
			ICategoriesDao categoriesDao = new CategoryDaoImpl();
		

			
			rgsty.rebind(Constants.STUB_BOOK, bookDao);
			rgsty.rebind(Constants.STUB_BILL, billDao);
			rgsty.rebind(Constants.STUB_AUTHOR, authorDao);
			rgsty.rebind(Constants.STUB_CATEGORY, categoriesDao);
			rgsty.rebind(Constants.STUB_PUBLISHER, publisherDao);
			System.out.println("Rebind stub ok!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
