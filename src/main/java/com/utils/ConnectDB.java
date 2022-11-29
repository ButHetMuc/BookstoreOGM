//package com.utils;
//
//import com.mongodb.client.MongoClients;
//
//import dev.morphia.Datastore;
//import dev.morphia.Morphia;
//
//public class ConnectDB {
//	private static final Datastore datastore;
//	
//	static {
//		datastore= Morphia.createDatastore(MongoClients.create(Constants.URI_DB), Constants.DATABASE_NAME);
//		datastore.getMapper().mapPackage("com.mongodb.morphia.entities");
//		datastore.ensureIndexes();
//	}
//	
//	
//	public static Datastore getDatastore() {
//		if(datastore == null) {
//			System.out.println("connect database error");
//			return null;
//		}else {
//			System.out.println("connect database success");
//		return datastore; 
//		}
//	}
//
//}
