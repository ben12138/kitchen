package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.Production;
import com.jlkj.kitchen.bean.ProductionComment;
import com.jlkj.kitchen.dao.ProductionCommentDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class ProductionCommentDaoImp implements ProductionCommentDao {

	@Override
	public boolean addComment(ProductionComment comment) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(comment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public boolean deleteComment(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProductionComment> getProductionComment(int productionid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		try{
			String sql = "select * from productioncomment where productionid="+productionid+" order by id desc";
			//执行sql语句返回结果集
			Query q = session.createSQLQuery(sql);
			java.util.List list = (java.util.List) q.list();
			if(list.size() == 0){
				return null;
			}else{
				List<ProductionComment> data = new ArrayList<>();
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					ProductionComment b = new ProductionComment();
					b.setId((Integer)obj[0]);
					b.setProductionid((Integer)obj[1]);
					b.setSenderid((Integer)obj[2]);
					b.setComment((String)obj[3]);
					b.setPraise((Integer)obj[4]);
					b.setTime((String)obj[5]);
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
	public boolean update(ProductionComment comment) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(comment);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
