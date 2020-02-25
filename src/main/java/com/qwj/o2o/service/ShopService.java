package com.qwj.o2o.service;


import java.io.InputStream;

import com.qwj.o2o.dto.ShopExecution;
import com.qwj.o2o.entity.Shop;
import com.qwj.o2o.exceptions.ShopOperationException;

public interface ShopService {
	Shop getByShopId(long shopId) throws ShopOperationException;
	
	ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName)throws ShopOperationException;
	
	ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName)throws ShopOperationException;
}
