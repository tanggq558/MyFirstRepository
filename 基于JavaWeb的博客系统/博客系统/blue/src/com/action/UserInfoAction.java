package com.action;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import persist.user.Login;
import persist.user.UserInfo;

import com.googlecode.jsonplugin.annotations.JSON;
import com.interceptor.ACI;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.UserServer;
import com.tools.Chinese;
import com.tools.MD5;

@SuppressWarnings("serial")
public class UserInfoAction extends ActionSupport{
	
	private final Log log=LogFactory.getLog(getClass());
	
	private int uid;
	private String realName;
	private String password;
	private String confPassword;
	private String birthPlace;
	private String livePlace;
	private String email;
	private String introduce;
	
	private String tips;
	
	
	@Autowired UserServer userServer;


	public String editUserInfo() throws Exception {
		return SUCCESS;
	}

/**********************************/	
	public boolean public_validation(){
		if(!ACI.intercept()){
			tips="no_login";return false;
		}
		if(realName.length()>10||realName.length()<2){
			tips="�������Ȳ���ȷ,2-10λ";
			return false;
		}
		if(!Pattern.matches("\\w{8,15}",password.trim())){
			tips="���볤��Ϊ8-15λ,�Ҳ���Ϊ���ĺ��������";
			return false;
		}
		if(!Pattern.matches("\\w{8,15}",confPassword.trim())){
			tips="ȷ�����볤��Ϊ8-15λ,�Ҳ���Ϊ���ĺ��������";
			return false;
		}
		if(!password.equals(confPassword)){
			tips="�����������벻һ��";
			return false;
		}
		if(birthPlace.length()>10||birthPlace.length()<4){
			tips="���糤�Ȳ���ȷ,4-10λ.��:�����人";
			return false;
		}
		if(livePlace.length()>10||livePlace.length()<4){
			tips="�־ӵس��Ȳ���ȷ,4-10λ.��:������ˮ";
			return false;
		}
		if(!Pattern.matches("[0-9a-zA-Z_\\-\\.]+@[0-9a-zA-Z_\\-]+(\\.[0-9a-zA-Z_\\-]+)*",email.trim())){
			tips="�����ʽ����ȷ";return false;
		}
		if(introduce.length()>100||introduce.length()<10){
			tips="���˼�鳤�Ȳ���ȷ,����10����";
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public void validateEditUserInfo() {
		boolean mark=false;
		if(public_validation()){
			Login login=userServer.login(uid);
			UserInfo us=login.getUserInfo();
			us.setRealName(realName);
			if(!password.equals("iloveyou")){
				login.setPassword(MD5.code(password));//MD5����
				mark=true;
			}
			us.setBirthPlace(birthPlace);
			us.setLivePlace(livePlace);
			us.setEmail(email);
			us.setIntroduce(introduce);
			us.setLogin(login);
			login.setUserInfo(us);
			
			userServer.update(login);
			tips="ok";
			log.error("�û�{"+login.getUsername()+"}�޸����ϳɹ�");
			
			if(mark){//����������˳���ǰ�û�
				log.error("�û�{"+login.getUsername()+"}�޸�����ɹ�");
			
				Map session=ActionContext.getContext().getSession();
				session.remove("LOGIN");
			}
		}
	}
	
/*********************************/	
	@JSON(serialize=false)
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	@JSON(serialize=false)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = Chinese.toChinese(realName);
	}
	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = Chinese.toChinese(password);
	}
	@JSON(serialize=false)
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	@JSON(serialize=false)
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = Chinese.toChinese(birthPlace);
	}
	@JSON(serialize=false)
	public String getLivePlace() {
		return livePlace;
	}
	public void setLivePlace(String livePlace) {
		this.livePlace = Chinese.toChinese(livePlace);
	}
	@JSON(serialize=false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@JSON(serialize=false)
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = Chinese.toChinese(introduce);
	}
/*****************************/
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	
}
