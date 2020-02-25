package com.qwj.o2o.dao;

import com.qwj.o2o.entity.Shop;

public interface ShopDao {

	Shop queryByShopId(Long shopId);

	int insertShop(Shop shop);

	int updateShop(Shop shop);
}
