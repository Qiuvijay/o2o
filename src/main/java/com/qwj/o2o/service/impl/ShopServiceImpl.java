package com.qwj.o2o.service.impl;
import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qwj.o2o.dao.ShopDao;
import com.qwj.o2o.dto.ShopExecution;
import com.qwj.o2o.entity.Shop;
import com.qwj.o2o.enums.ShopStateEnum;
import com.qwj.o2o.exceptions.ShopOperationException;
import com.qwj.o2o.service.ShopService;
import com.qwj.o2o.util.ImageUtil;
import com.qwj.o2o.util.PathUtil;
@Service
@Transactional
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;
	@Override
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException{
		if(shop==null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		
		
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum=shopDao.insertShop(shop);
			if(effectedNum<=0) {
				throw new ShopOperationException("店铺创建失败");
			}else {
				if(shopImgInputStream!=null) {
					try {	
						addShopImg(shop,shopImgInputStream,fileName);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error"+e.getMessage());
					}
					//更新店铺地址
					effectedNum=shopDao.updateShop(shop);
					if(effectedNum<=0) {
						throw new ShopOperationException("updateShopImg error");
					}
					
				}
			}
		} 
		
		catch (Exception e) {
			throw new ShopOperationException("addshop error"+e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}
	private void addShopImg(Shop shop,InputStream shopImgInputStream,String fileName) {
		String dest=PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr=ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
		shop.setShopImg(shopImgAddr);
	}
	@Override
	public Shop getByShopId(long shopId) throws ShopOperationException{
		return shopDao.queryByShopId(shopId);
	}
	@Override
	public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException{
		if(shop ==null|| shop.getShopId()==null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else {
			//1.判断图片是否需要处理
			try {
			if(shopImgInputStream!=null&&fileName!=null&&!fileName.equals("")) {
				Shop tempShop=shopDao.queryByShopId(shop.getShopId());
				if(tempShop.getShopImg()!=null) {
					ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
				addShopImg(shop,shopImgInputStream,fileName);
			}
			//2.更新店铺信息
			shop.setLastEditTime(new Date());
			int effectedNum=shopDao.updateShop(shop);
			if(effectedNum<=0) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}else {
				shop=shopDao.queryByShopId(shop.getShopId());
				return new ShopExecution(ShopStateEnum.SUCCESS,shop);
			}
			}catch(Exception e) {
		 throw new ShopOperationException("modifyShop error"+e.getMessage());
	}
}
}
}