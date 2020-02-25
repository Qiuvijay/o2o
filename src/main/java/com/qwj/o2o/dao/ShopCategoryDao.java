package com.qwj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qwj.o2o.entity.ShopCategory;

public interface ShopCategoryDao {
	List<ShopCategory> queryShopCategory(@Param("ShopCategoryCondition")ShopCategory shopCategoryCondition);
}
