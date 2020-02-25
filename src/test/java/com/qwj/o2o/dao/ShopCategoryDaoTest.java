package com.qwj.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qwj.o2o.BastTest;
import com.qwj.o2o.entity.ShopCategory;
import com.qwj.o2o.service.ShopCategoryService;

public class ShopCategoryDaoTest extends BastTest{
	@Autowired
	private ShopCategoryService shopCategoryDao;
	@Test
	public void testqueryShopCategory() {
		ShopCategory shopCategory=new ShopCategory();
		List<ShopCategory> list=shopCategoryDao.getShopCategoryList(shopCategory);
		for(ShopCategory s:list) {
			System.out.println(s.toString());
		}
	}
}
