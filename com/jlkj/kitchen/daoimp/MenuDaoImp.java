package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.bean.UserInf;
import com.jlkj.kitchen.dao.MenuDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class MenuDaoImp implements MenuDao {

	@Override
	public boolean addMenu(Menu menu) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(menu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public boolean deleteMenu(int id) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction tran = session.beginTransaction();
		try {
			Object obj = session.get(Menu.class, new Integer(id));
			if (obj == null) {
				return false;
			}
			session.delete(obj);
			tran.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tran.rollback();
			return false;
		}
	}

	@Override
	public boolean updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(menu);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Menu> getMenu() {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from menu order by id desc").list();
		List<Menu> menus;
		if (list.size() == 0) {
			return null;
		} else {
			menus = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				Menu menu = new Menu();
				menu.setId((Integer) obj[0]);
				menu.setSenderid((Integer) obj[1]);
				menu.setTitle((String) obj[2]);
				menu.setCover((String) obj[3]);
				menu.setStory((String) obj[4]);
				menu.setFood((String) obj[5]);
				menu.setCategory1((Integer) obj[6]);
				menu.setCategory2((Integer) obj[7]);
				menu.setCategory3((Integer) obj[8]);
				menu.setStep1((String) obj[9]);
				menu.setStep2((String) obj[10]);
				menu.setStep3((String) obj[11]);
				menu.setPic1((String) obj[12]);
				menu.setPic2((String) obj[13]);
				menu.setPic3((String) obj[14]);
				menu.setTime((String) obj[15]);
				menus.add(menu);
			}
			return menus;
		}
	}

	@Override
	public List<Menu> searchMenu(int category1, int category2, int category3) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		String sql = "select * from menu where category1=" + category1 + " or category2=" + category2 + " or category3="
				+ category3;
		// 执行sql语句返回结果集
		List<Menu> menus;
		Query q = session.createSQLQuery(sql);
		java.util.List list = (java.util.List) q.list();
		if (list.size() == 0) {
			return null;
		} else {
			menus = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				Menu menu = new Menu();
				menu.setId((Integer) obj[0]);
				menu.setSenderid((Integer) obj[1]);
				menu.setTitle((String) obj[2]);
				menu.setCover((String) obj[3]);
				menu.setStory((String) obj[4]);
				menu.setFood((String) obj[5]);
				menu.setCategory1((Integer) obj[6]);
				menu.setCategory2((Integer) obj[7]);
				menu.setCategory3((Integer) obj[8]);
				menu.setStep1((String) obj[9]);
				menu.setStep2((String) obj[10]);
				menu.setStep3((String) obj[11]);
				menu.setPic1((String) obj[12]);
				menu.setPic2((String) obj[13]);
				menu.setPic3((String) obj[14]);
				menu.setTime((String) obj[15]);
				menus.add(menu);
			}
			return menus;
		}
	}

	public static void main(String[] args) {
		MenuDao dao = new MenuDaoImp();
		System.out.println(dao.searchMenu(1, 1, 1));
	}

	@Override
	public List<Menu> searchMenu(String key) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		String sql = "select * from menu where title like '%" + key + "%' or food like '%" + key + "%' or story like '%"
				+ key + "%'";
		// 执行sql语句返回结果集
		List<Menu> menus;
		Query q = session.createSQLQuery(sql);
		java.util.List list = (java.util.List) q.list();
		if (list.size() == 0) {
			return null;
		} else {
			menus = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				Menu menu = new Menu();
				menu.setId((Integer) obj[0]);
				menu.setSenderid((Integer) obj[1]);
				menu.setTitle((String) obj[2]);
				menu.setCover((String) obj[3]);
				menu.setStory((String) obj[4]);
				menu.setFood((String) obj[5]);
				menu.setCategory1((Integer) obj[6]);
				menu.setCategory2((Integer) obj[7]);
				menu.setCategory3((Integer) obj[8]);
				menu.setStep1((String) obj[9]);
				menu.setStep2((String) obj[10]);
				menu.setStep3((String) obj[11]);
				menu.setPic1((String) obj[12]);
				menu.setPic2((String) obj[13]);
				menu.setPic3((String) obj[14]);
				menu.setTime((String) obj[15]);
				menus.add(menu);
			}
			return menus;
		}
	}

	@Override
	public List<Menu> getMenu(int senderid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from menu where senderid=" + senderid + " order by id desc")
				.list();
		List<Menu> menus;
		if (list.size() == 0) {
			return null;
		} else {
			menus = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				Menu menu = new Menu();
				menu.setId((Integer) obj[0]);
				menu.setSenderid((Integer) obj[1]);
				menu.setTitle((String) obj[2]);
				menu.setCover((String) obj[3]);
				menu.setStory((String) obj[4]);
				menu.setFood((String) obj[5]);
				menu.setCategory1((Integer) obj[6]);
				menu.setCategory2((Integer) obj[7]);
				menu.setCategory3((Integer) obj[8]);
				menu.setStep1((String) obj[9]);
				menu.setStep2((String) obj[10]);
				menu.setStep3((String) obj[11]);
				menu.setPic1((String) obj[12]);
				menu.setPic2((String) obj[13]);
				menu.setPic3((String) obj[14]);
				menu.setTime((String) obj[15]);
				menus.add(menu);
			}
			return menus;
		}
	}

	@Override
	public Menu getMenu(int id, int type) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from menu where id=" + id).list();
		List<Menu> menus;
		if (list.size() == 0) {
			return null;
		} else {
			Object[] obj = (Object[]) list.get(0);
			Menu menu = new Menu();
			menu.setId((Integer) obj[0]);
			menu.setSenderid((Integer) obj[1]);
			menu.setTitle((String) obj[2]);
			menu.setCover((String) obj[3]);
			menu.setStory((String) obj[4]);
			menu.setFood((String) obj[5]);
			menu.setCategory1((Integer) obj[6]);
			menu.setCategory2((Integer) obj[7]);
			menu.setCategory3((Integer) obj[8]);
			menu.setStep1((String) obj[9]);
			menu.setStep2((String) obj[10]);
			menu.setStep3((String) obj[11]);
			menu.setPic1((String) obj[12]);
			menu.setPic2((String) obj[13]);
			menu.setPic3((String) obj[14]);
			menu.setTime((String) obj[15]);
			return menu;
		}

	}

}
