package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.ProductionComment;

public interface ProductionCommentDao {
	public boolean addComment(ProductionComment comment);
	public boolean deleteComment(int id);
	public boolean update(ProductionComment comment);
	public List<ProductionComment> getProductionComment(int productionid);
}
