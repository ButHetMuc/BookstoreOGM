package com.rmi.server;

import java.rmi.registry.*;

import com.dao.BillDao;
import com.dao.impl.BillDaoImpl;
import com.rmi.interfaces.IBook_dao;
import com.rmi.remote.BookDao_Remote;
import com.utils.Constants;


public class MyServer {
	public static void main(String args[]) {
		try {
			
			Registry rgsty = LocateRegistry.createRegistry(Constants.PORT_RMI);
			

			// rebind stub
			IBook_dao bookStub = new BookDao_Remote();
			BillDao billDao = new BillDaoImpl();

			rgsty.rebind(Constants.STUB_BOOK, bookStub);
			rgsty.rebind(Constants.STUB_BILL, billDao);
			System.out.println("Rebind stub ok!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
