package com.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.jsonplugin.annotations.JSON;
import com.interceptor.ACI;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.LeavemsgServer;
import com.tools.Chinese;
import com.tools.DiviedPage;
import com.tools.MD5;

@SuppressWarnings({"serial"})
public class LeavemsgAction extends ActionSupport{
	
	private final Log log=LogFactory.getLog(getClass());

	@Autowired LeavemsgServer msgServer;
	
	private int id;
	private String name;
	private String content;
	private String code;
	
	/*****divied page**********/
	private int showPage=1;
	private int pageSize=1;//每页显示大小
	private int totalPage=1;
	String url="";
	/********/
	
	private String tips;
	private int myid;
	
	public String leavemsg() throws Exception {
		return SUCCESS;
	}
	public String replymsg() throws Exception {
		return SUCCESS;
	}
	public String delmsg() throws Exception{
		return SUCCESS;
	}
	public String showmsg() throws Exception{
		
		if(String.valueOf(this.getShowPage())==null){
			this.showPage=1;
		}
		int totalSize=msgServer.getAllLeaveMsg().size();
		totalPage=(totalSize%pageSize==0)?(totalSize/pageSize):(totalSize/pageSize+1);
		url="leavemsgShow.action";
		
		ActionContext act=ActionContext.getContext();
		act.put("ALL_LEAVEMSG",msgServer.getMsgByPage(showPage, pageSize));
		act.put("PAGE_LINK",DiviedPage.getPageLink(totalPage,showPage,0,url));
		return SUCCESS;
	}
	

/***************************************/
	
	@SuppressWarnings("unchecked")
	public boolean public_validation(){
		if(content==null||content.length()>500||content.length()<5){
			tips="留言长度不正确[5-500]";return false;
		}
		
		Map session=ActionContext.getContext().getSession();
		String varcode=(String)session.get("randCode");
		code=MD5.code(code.toLowerCase());
		if(code==null||!code.equals(varcode)){
			tips="验证码不正确";return false;
		}
		return true;
	}
	
	public void validateLeavemsg() {
		if(name==null||name.length()>15||name.length()<2){
			tips="姓名长度不正确[2-15]";return;
		}
		if(!public_validation()){
			return;
		}
		
		myid=msgServer.leaveMsg(name, content);
		if(myid>0) tips="ok";
		else tips="error";
	}
	public void validateReplymsg() {
		if(!public_validation()){
			return;
		}
		
		myid=msgServer.replyMsg(id,content);
		if(myid>0) tips="ok";
		else tips="error";
	}
	
	public void validateDelmsg() {
		if(!ACI.intercept()){
			tips="no_login";return;
		}
		msgServer.delMsg(id);
		tips="ok";
		
		log.error("删除留言{id="+id+"}");
	}

	
/*************************/
	@JSON(serialize=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JSON(serialize=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = Chinese.toChinese(name);
	}
	@JSON(serialize=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = Chinese.toChinese(content);
	}
	@JSON(serialize=false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JSON(serialize=false)
	public int getShowPage() {
		return showPage;
	}
	public void setShowPage(int showPage) {
		this.showPage = showPage;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/****json******/
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}

	public int getMyid() {
		return myid;
	}
	public void setMyid(int myid) {
		this.myid = myid;
	}
	
}
