package com.qwj.o2o.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qwj.o2o.BastTest;
import com.qwj.o2o.entity.Area;
import com.qwj.o2o.entity.PersonInfo;
import com.qwj.o2o.entity.Shop;
import com.qwj.o2o.entity.ShopCategory;

public class ShopDaoTest extends BastTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	@Ignore
	public void testInsertShop() {
		Area area = new Area();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		area.setId(3);
		owner.setUserId(10L);
		shopCategory.setShopCategoryId(10L);
		Shop shop = new Shop();
		shop.setShopName("三号百货");
		shop.setShopDesc("百货");
		shop.setPriority(1);
		shop.setEnableStatus(2);
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		System.out.println(shopDao);
		shopDao.insertShop(shop);
		System.out.println(shop);
	}
	@Test
	public void testqueryByShopId() {
	Shop shop=	shopDao.queryByShopId(15L);
	System.out.println("AreaId="+shop.getArea().getId());
	System.out.println("AreaName="+shop.getArea().getAreaName());
	}

}
