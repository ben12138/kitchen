package com.jlkj.kitchen.databasehelper;
//单例模式操作数据连接

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DataBaseHelper {
	private static SessionFactory factory;
	private static Session session;
	//构造方法私有化
	private DataBaseHelper() {}
	public static Session getSession() {
		if(factory == null || session == null) {
			Configuration configuration=new Configuration().configure();
			ServiceRegistry serviceRegistry=
					new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			factory=configuration.buildSessionFactory(serviceRegistry);
			session=factory.openSession();
		}
		return session;
	}
	public static void destory() {
		session.close();
		factory.close();
	}
}
