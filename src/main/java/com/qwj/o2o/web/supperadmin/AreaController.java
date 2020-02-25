package com.qwj.o2o.web.supperadmin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qwj.o2o.entity.Area;
import com.qwj.o2o.service.AreaService;

@Controller
@RequestMapping("/supperadmin")
public class AreaController {
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/listarea",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> listArea(){
		File f =new File("Test.txt");      
		  String fileName=f.getName();      
		  String prefix=fileName.substring(fileName.lastIndexOf(".")+1);      
		  System.out.println(prefix);
		return null;
	}
}
