package com.utils;

import javax.persistence.Embeddable;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.ogm.boot.OgmSessionFactoryBuilder;
import org.hibernate.ogm.cfg.OgmProperties;
import org.hibernate.service.ServiceRegistry;

import com.entities.*;

public class HibernateUtils {
	private static HibernateUtils instance = null;
	private OgmSessionFactory sessionFactory; 
	
	private HibernateUtils() {
		ServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySetting(OgmProperties.ENABLED, true)
				.configure()
				.build();
		Metadata metatdata = new MetadataSources(registry)
				.addAnnotatedClass(Book.class)
				.addAnnotatedClass(Author.class)
				.addAnnotatedClass(Category.class)
				.addAnnotatedClass(Customer.class)
//				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Bill.class)
				.addAnnotatedClass(BillDetails.class)
//				.addAnnotatedClass(Publisher.class)
				.getMetadataBuilder()
				.build();
		sessionFactory = metatdata.getSessionFactoryBuilder()
				.unwrap(OgmSessionFactoryBuilder.class)
				.build();
		
	}
	
	public static HibernateUtils getInstance() {
		if(instance ==null) {
			instance = new HibernateUtils();
		}
		return instance;
	}
	public OgmSessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
