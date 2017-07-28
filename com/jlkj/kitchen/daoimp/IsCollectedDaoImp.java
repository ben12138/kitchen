package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.Collection;
import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.dao.IsCollectedDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class IsCollectedDaoImp implements IsCollectedDao{

	@Override
	public boolean isCollected(int menuid, int userid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from collection where menuid="+menuid+" and userid="+userid).list();
		if(list.size() == 0){
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean cancelCollect(int menuid,int userid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		System.out.println("delete from collection where menuid="+menuid+" and userid="+userid);
		int result = session.createSQLQuery("delete from collection where menuid="+menuid+" and userid="+userid).executeUpdate();
		transaction.commit();
		if(result == 0){
			return false;
		}else {
			return true;
		}
	}
	
	public static void main(String[] args) {
		IsCollectedDaoImp dao = new IsCollectedDaoImp();
		System.out.println(dao.getCollections(15));
	}

	@Override
	public boolean collect(Collection collection) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(collection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public List<Collection> getCollections(int userid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from collection where userid="+userid+" order by id desc").list();
		List<Collection> collections;
		if(list.size() == 0){
			return null;
		}else{
			collections = new ArrayList<>();
			for(int i=0;i<list.size();i++) {
				Object[] obj = (Object[]) list.get(i);
				Collection collection = new Collection();
				collection.setId((Integer)obj[0]);
				collection.setMenuid((Integer)obj[1]);
				collection.setUserid((Integer)obj[2]);
				collections.add(collection);
			}
			return collections;
		}
	}
	
}
