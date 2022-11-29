//package com.rmi.server;
//
//import java.rmi.*;
//import java.rmi.registry.*;
//import java.rmi.server.UnicastRemoteObject;
//
//import com.rmi.interfaces.ITestEntityManage;
//import com.rmi.remote.TestEntityManageRemote;
//import com.utils.ConnectDB;
//import com.utils.Constants;
//import com.utils.MainUtils;
//
//import dev.morphia.Datastore;
//
//public class MyServer {
//	public static void main(String args[]) {
//		try {
//			
//			Registry rgsty = LocateRegistry.createRegistry(Constants.PORT_RMI);
//			Datastore datastore = ConnectDB.getDatastore();
//
//			// rebind stub
//			ITestEntityManage testEntityStub = new TestEntityManageRemote(datastore);
//
////			rgsty.rebind("employee", employeeStub);
//			rgsty.rebind(Constants.STUB_TEST_ENTITY, testEntityStub);
//			System.out.println("Rebind stub ok!");
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
//}
