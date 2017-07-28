package com.jlkj.kitchen.dao;

import com.jlkj.kitchen.bean.Admin;

public interface AdminDao {
	public boolean saveAdmin(Admin admin);
	public Admin getAdmin(int id);
	public boolean updateAdmin(Admin admin);
}
