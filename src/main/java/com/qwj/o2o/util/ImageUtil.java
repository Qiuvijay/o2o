package com.qwj.o2o.util;


import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import net.coobird.thumbnailator.Thumbnails;
public class ImageUtil {
	//定义日期的格式
	public static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	public static final Random r=new Random();
	public static String generateThumbnail(InputStream thumbnailInputStream,String fileName,String targetAddr) {
		//创建随机名字
		String realFileName=getRandomFileName();
		//获取扩展名
		String extension=getFileExtension(fileName);
		//创建文件存储路径
		makeDirPath(targetAddr);
		//得到新文件名
		String relativeAddr=targetAddr+realFileName+extension;
		//得到文件目录
		File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of(thumbnailInputStream).size(200, 200)
			.outputQuality(0.8f).toFile(dest);
		} catch (Exception e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}
	//创建文件存储路径
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
		File dirPath=new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
	}

	//获取扩展名
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	//创建随机名字
	public  static String getRandomFileName() {
		//获取随机五位数
		int ranmun=r.nextInt(89999)+10000;
		String nowTimeStr=sDateFormat.format(new Date());
		return nowTimeStr+ranmun;
	}

	public static void deleteFileOrPath(String storePath) {
		File fileOrPath=new File(PathUtil.getImgBasePath()+storePath);
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File files[] =fileOrPath.listFiles();
				for(int i=0;i<files.length;i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
