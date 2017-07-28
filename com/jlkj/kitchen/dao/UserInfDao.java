package com.jlkj.kitchen.dao;

import com.jlkj.kitchen.bean.UserInf;

public interface UserInfDao {
	public UserInf login(String username,String password);
	public boolean register(UserInf user);
	public String getVerification(String email);
	public boolean isRegistered(String username);
	public UserInf findpassword(String username);
	public boolean updateUserInf(UserInf user);
	public UserInf getUserInfExceptpassword(int id);
}
