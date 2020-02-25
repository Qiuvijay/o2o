package com.qwj.o2o.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qwj.o2o.BastTest;
import com.qwj.o2o.entity.Area;
import com.qwj.o2o.util.ImageUtil;

public class AreaServiceTest extends BastTest{
	@Autowired
	private AreaService areaService;
//	@Test
	public void testGetAreaList() {
		List<Area> arealist=areaService.getAreaList();
		for(Area a:arealist) {
			System.out.println(a);
		}
	}
	public static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	public static final Random r=new Random();
	@Test
	public void documenttest() {
	/*	File f =new File("Test.txt");      
		  String fileName=f.getName();      
		  String prefix=fileName.substring(fileName.lastIndexOf("."));      
		  System.out.println(prefix);*/

		int ranmun=r.nextInt(89999)+10000;
		String nowTimeStr=sDateFormat.format(new Date());
System.out.println(nowTimeStr);
	}
}
