package com.rmi.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.rmi.interfaces.ITestEntityManage;



public class TestEntityManageRemote extends UnicastRemoteObject implements ITestEntityManage {

	protected TestEntityManageRemote() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
//	Datastore datastore;
//
//	public TestEntityManageRemote(Datastore datastore) throws RemoteException {
//		super();
//		// TODO Auto-generated constructor stub
//		this.datastore = datastore;
//	}
//
//	public Integer addTestEntity(TestEntity obj) throws RemoteException {
//		// TODO Auto-generated method stub
//		try {
//			if(datastore == null) {
//				System.out.println("datastore null");
//				return 0;
//			}
//			datastore.save(obj);
//			System.out.println("insert ok");
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("insert error");
//			return 0;
//		}
//		return 1;
//	}

}
