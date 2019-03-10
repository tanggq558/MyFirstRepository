package com.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.AlbumServer;
import com.server.ArticleServer;
import com.server.OtherServer;
import com.server.UserServer;

@SuppressWarnings({"serial","unchecked"})
public class GoAction extends ActionSupport{
	
	private int uid;
	private int access=0;

	@Autowired ArticleServer articleServer;
	@Autowired AlbumServer albumServer;
	@Autowired OtherServer otherServer;
	@Autowired UserServer userServer;
	
	public String execute() throws Exception {
		ActionContext act=ActionContext.getContext();
		act.put("LATEST_ARTICLE_EACH_TYPE",articleServer.getLatestArticleInEachType());
		act.put("ABOUT_ME",userServer.login(uid));
		
		access=otherServer.updateAccess(1);//更新访问量
		
		return SUCCESS;
	}
	
	public String articleType() throws Exception{
		ActionContext act=ActionContext.getContext();
		act.getSession().put("ALL_ARTICLE_TYPE", articleServer.getAllArticleType());
		return SUCCESS;
	}
	
	public String eachImgType() throws Exception{
		ActionContext act=ActionContext.getContext();
		act.put("OLDEST_IMG_EACH_TYPE",albumServer.getOldestImgInEachType(0));
		return SUCCESS;
	}
	
	public String allImgType() throws Exception{
		ActionContext act=ActionContext.getContext();
		act.put("ALL_IMG_TYPE", albumServer.getAllImgType());
		return SUCCESS;
	}
	
	public String imgUpload() throws Exception{
		ActionContext act=ActionContext.getContext();
		act.getSession().put("ALL_IMG_TYPE", albumServer.getAllImgType());
		return SUCCESS;
	}

	
	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}
	
}
