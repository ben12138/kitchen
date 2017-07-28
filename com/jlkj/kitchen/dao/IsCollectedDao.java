package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.Collection;

public interface IsCollectedDao {
	public boolean isCollected(int menuid,int userid);
	public boolean cancelCollect(int menuid,int userid);
	public boolean collect(Collection collection);
	public List<Collection> getCollections(int userid);
}
