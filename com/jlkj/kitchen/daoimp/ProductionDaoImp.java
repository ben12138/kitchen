package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.Production;
import com.jlkj.kitchen.bean.Question;
import com.jlkj.kitchen.dao.ProductionDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class ProductionDaoImp implements ProductionDao{

	@Override
	public boolean addProduction(Production production) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(production);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public boolean deleteProduction(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProduction(Production production) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(production);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Production> getProduction() {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		try{
			String sql = "select * from production order by praise desc";
			//执行sql语句返回结果集
			Query q = session.createSQLQuery(sql);
			java.util.List list = (java.util.List) q.list();
			if(list.size() == 0){
				return null;
			}else{
				List<Production> data = new ArrayList<>();
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					Production b = new Production();
					b.setId((Integer)obj[0]);
					b.setSenderid((Integer)obj[1]);
					b.setDescription((String)obj[2]);
					b.setImgurl((String)obj[3]);
					b.setPraise((Integer)obj[4]);
					data.add(b);
				}
				return data;
			}
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Production> searchProduction(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		ProductionDaoImp dao = new ProductionDaoImp();
		List<Production> productions = dao.getProduction();
		for(Production temp:productions) {
			System.out.println(temp.getPraise());
		}
	}
	
	@Override
	public List<Production> getProduction(int senderid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		try{
			String sql = "select * from production where senderid="+senderid;
			//执行sql语句返回结果集
			Query q = session.createSQLQuery(sql);
			java.util.List list = (java.util.List) q.list();
			if(list.size() == 0){
				return null;
			}else{
				List<Production> data = new ArrayList<>();
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					Production b = new Production();
					b.setId((Integer)obj[0]);
					b.setSenderid((Integer)obj[1]);
					b.setDescription((String)obj[2]);
					b.setImgurl((String)obj[3]);
					b.setPraise((Integer)obj[4]);
					data.add(b);
				}
				return data;
			}
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}
	
}
