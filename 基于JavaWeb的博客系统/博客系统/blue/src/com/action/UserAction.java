package com.action;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import persist.user.Login;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.UserServer;
import com.tools.MD5;
import com.tools.Time;

@SuppressWarnings({"serial","unchecked"})
public class UserAction extends ActionSupport{
	
	@Autowired UserServer userServer;
	
	private final Log log=LogFactory.getLog(getClass());
	
	private String username;
	private String password;
	private String rand;
	
	private String tips;
	
	@JSON(serialize=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JSON(serialize=false)
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand =rand;
	}
	
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	
	
	public String login() throws Exception {
		return SUCCESS;
	}
	
	public String logout() throws Exception{
	
		Map session=ActionContext.getContext().getSession();
		Login login=(Login)session.get("LOGIN");
		session.remove("LOGIN");
		
		log.error("�û�{"+login.getUsername()+"}�˳���½");
		
		return SUCCESS;
	}
	
	
	public void validateLogin() {
		if(username==null||!Pattern.matches("\\w{4,15}",username.trim())){
			tips="�û�������Ϊ4-18λ,�Ҳ���Ϊ���ĺ��������";
			return;
		}
		if(password==null||!Pattern.matches("\\w{8,15}",password.trim())){
			tips="���볤��Ϊ8-18λ,�Ҳ���Ϊ���ĺ��������";
			return;
		}
		if(rand==null||!Pattern.matches("\\w{4}", rand.trim())){
			tips="��֤�볤��Ϊ4λ,��ֻ��Ϊ���ֺ���ĸ���";
			return;
		}
		
		Map session=ActionContext.getContext().getSession();
		String randCode=(String)session.get("randCode");
		rand=MD5.code(rand.toLowerCase());
		if(rand==null||!rand.equals(randCode)){
			tips="��֤�벻��ȷ";
			return;
		}
		
		Login login=userServer.login(username);
		if(login!=null&&login.getPassword().equals(MD5.code(password))){
			session.put("LOGIN",login);
			
			log.error("�û�{"+username+"}��½�ɹ�");
			
			//���������½ʱ��
			login.getUserInfo().setLastLoginTime(Time.getTime());
			userServer.update(login);
			
			tips="ok";
		}else{
			tips="�û��������벻��ȷ";
			log.error("�û�{"+username+"}���Ե�½,��½ʧ��");
		}
		return;
	}
	
}
