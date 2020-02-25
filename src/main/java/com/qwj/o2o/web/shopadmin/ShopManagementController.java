package com.qwj.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwj.o2o.dto.ShopExecution;
import com.qwj.o2o.entity.Area;
import com.qwj.o2o.entity.PersonInfo;
import com.qwj.o2o.entity.Shop;
import com.qwj.o2o.entity.ShopCategory;
import com.qwj.o2o.enums.ShopStateEnum;
import com.qwj.o2o.service.AreaService;
import com.qwj.o2o.service.ShopCategoryService;
import com.qwj.o2o.service.ShopService;
import com.qwj.o2o.util.CodeUtil;
import com.qwj.o2o.util.HttpServletRequestUtil;

@RestController
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	ShopService shopService;
	@Autowired
	ShopCategoryService shopCategoryService;
	@Autowired
	AreaService areaService;
	@RequestMapping(value="/getShopById", method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> getShopById(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		Long shopId=HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId>-1) {
			try {
			Shop shop=shopService.getByShopId(shopId);
			List<Area> areaList=areaService.getAreaList();
			map.put("shop", shop);
			map.put("areaList", areaList);
			map.put("success",true);
			}catch(Exception e){
				map.put("success",false);
				map.put("errMsg", e.toString());
			}
		}else {
			map.put("success",false);
			map.put("errMsg","empty shopId");
		}
				return map;
		
	}
	
	
	@RequestMapping(value="/getshopinitinfo", method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getshopinitinfo(){
		Map<String,Object> map=new HashMap<String,Object>();
		List<ShopCategory> shopCategoryList=new ArrayList<ShopCategory>();
		List<Area> areaList=new ArrayList<Area>();
		try {
			shopCategoryList=shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList=areaService.getAreaList();
			map.put("shopCategoryList",shopCategoryList);
			map.put("areaList",areaList);
			map.put("success",true);
		} catch (Exception e) {
			map.put("success",false);
			map.put("errMsg",e.getMessage());
		}
		return map;
	}
	
	
	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerShop(/*@RequestParam("verifyCodeActual")  String verifyCodeActual,*/HttpServletRequest request){
		System.out.println("进入了registershop");
		String verifyCodeActual= HttpServletRequestUtil.getString(request,"verifyCodeActual");
		System.out.println("verifyCodeActual="+verifyCodeActual);
		Map<String,Object> modelMap=new HashMap<String, Object>();
		if(!CodeUtil.checkVerifyCode(request,verifyCodeActual)) {
			System.out.println("进入了111111");
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			System.out.println("进入了22222222222");
			return modelMap;
		}
		
		//1.接受并转化相应的参数，包括店铺信息以及图片信息
		String shopstr = HttpServletRequestUtil.getString(request,"shopstr");
		ObjectMapper mapper=new ObjectMapper();
		Shop shop=null;
		try {
			shop=mapper.readValue(shopstr, Shop.class);
			System.out.println("shop="+shop.toString());
		} catch (Exception e) {
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg=null;
		CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
			shopImg=(CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","上传图片不能为空");
			return modelMap;
		}
		//2.注册店铺
		if(shop!=null&&shopImg!=null) {
			PersonInfo owner=(PersonInfo)request.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution se;
			try {
				se = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				if(se.getState()==ShopStateEnum.CHECK.getState()) {
					modelMap.put("success",true);
					//该用户可以操作的店铺列表
					@SuppressWarnings("unchecked")
					List<Shop> shopList=(List<Shop>)request.getSession().getAttribute("shopList");
					if(shopList==null||shopList.size()==0) {
						shopList=new ArrayList<Shop>();
					}
						shopList.add(se.getShop());
						request.getSession().setAttribute("shopList",shopList);
					
				}else {
					modelMap.put("success",false);
					modelMap.put("errMsg",se.getStateInfo());
				}
			} catch (IOException e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
			}
			return modelMap;
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","请输入店铺信息");
			return modelMap;
		}
		//3.返回结果
	}
	
	@RequestMapping(value="/modifyshop", method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> modifyShop(HttpServletRequest request){
		Map<String,Object> modelMap=new HashMap<String, Object>();
		
		if(CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		
		//1.接受并转化相应的参数，包括店铺信息以及图片信息
		String shopstr = HttpServletRequestUtil.getString(request,"shopstr");
		ObjectMapper mapper=new ObjectMapper();
		Shop shop=null;
		try {
			shop=mapper.readValue(shopstr, Shop.class);
		
		} catch (Exception e) {
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg=null;
		CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
			shopImg=(CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}
		//2.修改店铺
		if(shop!=null&&shop.getShopId()!=null) {
			ShopExecution se;
			try {
				if(shopImg==null) {
					se = shopService.modifyShop(shop,null,shopImg.getOriginalFilename());
				}else {
					se = shopService.modifyShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				}
				if(se.getState()==ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success",true);
				}else {
					modelMap.put("success",false);
					modelMap.put("errMsg",se.getStateInfo());
				}
			} catch (IOException e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
			}
			return modelMap;
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","请输入店铺Id");
			return modelMap;
		}
		//3.返回结果
	}
	
	/*private static void inputStreamToFile(InputStream ins,File file) {
		FileOutputStream os=null;
		try {
			os =new FileOutputStream(file);
			int bytesread=0;
			byte[] buffer=new byte[1024];
			while ((bytesread=ins.read(buffer))!=-1) {
			os.write(buffer,0,bytesread);
			}
		} catch (Exception e) {
		throw new RuntimeException("调用inputStreamTofile产生异常"+e.getMessage());
		}finally {
			try {
				if(os!=null) {
					os.close();
				}if(ins!=null) {
					ins.close();
				}
			} catch (Exception e) {
				throw new RuntimeException("调用inputStreamTofile关闭IO产生异常"+e.getMessage());
			}
		
		}
	}*/
}
