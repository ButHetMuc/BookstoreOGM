package com.rmi.server;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import com.rmi.interfaces.IBook_dao;
import com.rmi.interfaces.ITestEntityManage;
import com.rmi.remote.BookDao_Remote;
import com.rmi.remote.TestEntityManageRemote;
import com.utils.Constants;
import com.utils.MainUtils;


public class MyServer {
	public static void main(String args[]) {
		try {
			
			Registry rgsty = LocateRegistry.createRegistry(Constants.PORT_RMI);
			

			// rebind stub
			IBook_dao bookStub = new BookDao_Remote();

			rgsty.rebind(Constants.STUB_BOOK, bookStub);
			System.out.println("Rebind stub ok!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
