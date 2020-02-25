package com.qwj.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qwj.o2o.BastTest;
import com.qwj.o2o.entity.Area;

public class AreaDaoTest extends BastTest{
@Autowired
private AreaDao areadao;
@Test
public void testqueryArea() {
	List<Area> arealist=areadao.queryArea();
	for(Area a:arealist) {
		System.out.println(a);
	}
}
}
