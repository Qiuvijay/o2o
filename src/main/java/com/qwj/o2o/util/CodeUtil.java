package com.qwj.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request,String verifyCodeActual) {
		String verifyCodeExpected=(String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//String verifyCodeActual=HttpServletRequestUtil.getString(request,"verifyCodeActual");
	System.out.println("verifyCodeExpected="+verifyCodeExpected);
		if(verifyCodeActual==null||!verifyCodeActual.equals(verifyCodeExpected)) {
			return false;
		}
		return true;
	}
}
