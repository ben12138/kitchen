package com.jlkj.kitchen.daoimp;

import java.util.List;

import org.hibernate.Session;

import com.jlkj.kitchen.bean.Admin;
import com.jlkj.kitchen.bean.UserInf;
import com.jlkj.kitchen.dao.AdminDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class AdminDaoImp implements AdminDao {

	@Override
	public boolean saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin getAdmin(int id) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		List list = session.createSQLQuery("select * from admin where id="+id).list();
		if(list.size() == 0){
			return null;
		}else{
			Object[] obj = (Object[]) list.get(0);
			Admin admin = new Admin();
			admin.setId((Integer)obj[0]);
			admin.setUsername("");
			admin.setPassword("");
			admin.setNickname((String)obj[3]);
			admin.setImgurl((String)obj[4]);
			admin.setEmail("");
			admin.setBirthday((String)obj[6]);
			admin.setSex((Integer)obj[7]);
			admin.setCompany((String)obj[8]);
			admin.setIntroduction((String)obj[9]);
			return admin;
		}
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
