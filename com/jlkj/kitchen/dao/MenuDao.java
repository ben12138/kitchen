package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.Menu;

public interface MenuDao {
	public boolean addMenu(Menu menu);
	public boolean deleteMenu(int id);
	public boolean updateMenu(Menu menu);
	public List<Menu> getMenu();
	public List<Menu> getMenu(int senderid);
	public Menu getMenu(int id ,int type);
	public List<Menu> searchMenu(int category1,int category2,int category3);
	public List<Menu> searchMenu(String key);
}
