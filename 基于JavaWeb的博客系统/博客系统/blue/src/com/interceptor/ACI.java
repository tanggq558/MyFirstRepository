package com.interceptor;

import java.util.Map;

import persist.user.Login;

import com.opensymphony.xwork2.ActionContext;

public class ACI {

	public static boolean intercept(){
		@SuppressWarnings("unchecked")
		Map session=ActionContext.getContext().getSession();
		Login log=(Login)session.get("LOGIN");
		if(log==null){
			return false;
		}
		return true;
	}

}
//glq