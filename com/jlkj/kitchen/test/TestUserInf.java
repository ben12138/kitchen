package com.jlkj.kitchen.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.File;
import java.util.List;

import org.hibernate.Query;

import com.jlkj.kitchen.bean.UserInf;

import net.sf.ehcache.hibernate.HibernateUtil;

public class TestUserInf {
	public static void main(String[] args) {
//		Configuration conf = new Configuration().configure();
//		ServiceRegistry service = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
//		SessionFactory factory = conf.buildSessionFactory(service);
//		Session session = factory.openSession();
//		Transaction transaction = session.beginTransaction();
//		List list = session.createSQLQuery("select * from comment").list();
//		System.out.println(list.size());
////		System.out.println(list.get(0)[0]);
//		transaction.commit();
//		session.close();
//		factory.close();
//		save();
		File file = new File("。/headimage/default_personal_image.png");
		System.out.println(file.exists());
		
	}
	//查询测试，根据主键值查询
	public void search() {
//		UserInf user = (UserInf) session.get(UserInf.class, 1);
//		System.out.println(user.getUsername());
	}
	//删除测试
	public void delete() {
		
	}
	//修改测试
	public void update() {
		
	}
	//查询所有测试
	public void get() {
		
	}
	
	//保存测试
	public static void save() {
		Configuration conf = new Configuration().configure();
		ServiceRegistry service = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory factory = conf.buildSessionFactory(service);
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		UserInf user = new UserInf();
		user.setUsername("1");
		user.setPassword("1");
		user.setNickname("1");
		user.setEmail("1");
		user.setBirthday("1");
		user.setCompany("1");
		user.setImgurl("1");
		user.setSex(1);
		user.setIntroduction("1");
		session.save(user);
		transaction.commit();
		session.close();
		factory.close();
				
	}
}
