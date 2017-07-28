package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.Production;

public interface ProductionDao {
	public boolean addProduction(Production production);
	public boolean deleteProduction(int id);
	public boolean updateProduction(Production production);
	public List<Production> getProduction();
	public List<Production> getProduction(int senderid);
	public List<Production> searchProduction(String key);
}
