package com.qwj.o2o.util;

public class PathUtil {
	public static String getImgBasePath() {
		
		String os=System.getProperty("os.name");
		String basePath="";
		if(os.toLowerCase().startsWith("win")) {
			basePath="C:/Users/2317/Desktop/img";
		}else {
			basePath="/home/qwj/img/";
		}
		return basePath;
	}
	
	public static String getShopImagePath(Long shopId) {
		String imagePath="/upload/item/shop/"+shopId+"/";
		return imagePath;
	}
}
