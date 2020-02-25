package com.qwj.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qwj.o2o.BastTest;
import com.qwj.o2o.dto.ShopExecution;
import com.qwj.o2o.entity.Area;
import com.qwj.o2o.entity.PersonInfo;
import com.qwj.o2o.entity.Shop;
import com.qwj.o2o.entity.ShopCategory;
import com.qwj.o2o.enums.ShopStateEnum;
import com.qwj.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BastTest{
	@Autowired
	private ShopService shopService;
	@Test
	public void testModifyShop() throws ShopOperationException,FileNotFoundException {
		Shop shop=new Shop();
		shop.setShopId(52l);
		shop.setShopName("修改后的店铺名称7");
		 File img=new File("C:/Users/2317/Desktop/img/图像 010.jpg"); 
		 InputStream is=new FileInputStream(img);
			ShopExecution se= shopService.modifyShop(shop, is, "图像 010.jpg");
			System.out.println("新的图片地址"+se.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void addShopimgTest() throws FileNotFoundException {
		 Area area=new Area();
		 PersonInfo owner=new PersonInfo();
	 ShopCategory shopCategory=new ShopCategory();
	 area.setId(3);
	 owner.setUserId(11L);
	 shopCategory.setShopCategoryId(12L);
	 Shop shop=new Shop();
	 shop.setShopName("花生超市");
	 shop.setShopDesc("毒品");
	 shop.setPriority(1);
	 shop.setEnableStatus(ShopStateEnum.CHECK.getState());
	 shop.setAdvice("审核中");
	 shop.setArea(area);
	 shop.setOwner(owner);
	 shop.setShopCategory(shopCategory);
	 shop.setShopImg("C:/Users/2317/Desktop/img/xiyiji.jpg");
	 File img=new File("C:/Users/2317/Desktop/img/taobao.jpg");
	 InputStream is=new FileInputStream(img);
	ShopExecution se= shopService.addShop(shop,is,img.getName()); 
	System.out.println(se);
	}
}
