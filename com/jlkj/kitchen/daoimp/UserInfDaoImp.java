package com.jlkj.kitchen.daoimp;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.UserInf;
import com.jlkj.kitchen.dao.UserInfDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;
import com.jlkj.kitchen.util.SendMail;

public class UserInfDaoImp implements UserInfDao{
	
	@Override
	public UserInf findpassword(String username) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from userinf where username='"+username+"'").list();
		if(list.size() == 0){
			return null;
		}else{
			Object[] obj = (Object[]) list.get(0);
			UserInf user = new UserInf();
			user.setId((Integer)obj[0]);
			user.setUsername((String)obj[1]);
			user.setPassword((String)obj[2]);
			user.setNickname((String)obj[3]);
			user.setImgurl((String)obj[4]);
			user.setEmail((String)obj[5]);
			user.setBirthday((String)obj[6]);
			user.setSex((Integer)obj[7]);
			user.setCompany((String)obj[8]);
			user.setIntroduction((String)obj[9]);
			return user;
		}
	}
	
	@Override
	public String getVerification(String email) {
		// TODO Auto-generated method stub
		SendMail mail = new SendMail();
		try {
			mail.send(email);
			return mail.getContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new UserInfDaoImp().isRegistered("13151567657@163.com"));
	}
	
	@Override
	public boolean isRegistered(String username) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from userinf where username='"+username+"'").list();
		if(list.size() == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public boolean register(UserInf user) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}
	
	@Override
	public UserInf login(String username, String password) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from userinf where username='"+username+"' and password="+password).list();
		if(list.size() == 0){
			return null;
		}else{
			Object[] obj = (Object[]) list.get(0);
			UserInf user = new UserInf();
			user.setId((Integer)obj[0]);
			user.setUsername((String)obj[1]);
			user.setPassword((String)obj[2]);
			user.setNickname((String)obj[3]);
			user.setImgurl((String)obj[4]);
			user.setEmail((String)obj[5]);
			user.setBirthday((String)obj[6]);
			user.setSex((Integer)obj[7]);
			user.setCompany((String)obj[8]);
			user.setIntroduction((String)obj[9]);
			return user;
		}
	}
	
	@Override
	public boolean updateUserInf(UserInf user) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(user);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public UserInf getUserInfExceptpassword(int id) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		List list = session.createSQLQuery("select * from userinf where id="+id).list();
		if(list.size() == 0){
			return null;
		}else{
			Object[] obj = (Object[]) list.get(0);
			UserInf user = new UserInf();
			user.setId((Integer)obj[0]);
			user.setUsername((String)obj[1]);
			user.setPassword("");
			user.setNickname((String)obj[3]);
			user.setImgurl((String)obj[4]);
			user.setEmail((String)obj[5]);
			user.setBirthday((String)obj[6]);
			user.setSex((Integer)obj[7]);
			user.setCompany((String)obj[8]);
			user.setIntroduction((String)obj[9]);
			return user;
		}
	}
	
}
