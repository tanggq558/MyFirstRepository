package com.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import persist.article.Article;

import com.googlecode.jsonplugin.annotations.JSON;
import com.interceptor.ACI;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.ArticleServer;
import com.tools.Chinese;
import com.tools.MD5;

@SuppressWarnings({"serial","unchecked"})
public class AdetailAction extends ActionSupport{
	
	private final Log log=LogFactory.getLog(getClass());
	
	private int id;
	private String user;
	private String comment;
	private String code;
	private String tips;
	
	@Autowired ArticleServer articleServer;

/*******function****************************************/	
	public String adetail() throws Exception {
		ActionContext request=ActionContext.getContext();
		Article article=articleServer.getArticleById(id);
		if(article!=null){
			request.put("ARTICLE_DETAIL",article);
			request.put("ARTICLE_COMMENT",articleServer.getArticleCommentById(id));
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public String aedit() throws Exception{
		ActionContext request=ActionContext.getContext();
		Article article=articleServer.getArticleById(id);
		if(article!=null){
			request.put("ARTICLE_EDIT",article);
			request.getSession().put("ALL_ARTICLE_TYPE", articleServer.getAllArticleType());
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	/**del article******/
	public String adel() throws Exception{
		return SUCCESS;
	}
	
	/**del comment******/
	public String cdel() throws Exception{
		return SUCCESS;
	}
	
	public String aleavemsg() throws Exception{
		return SUCCESS;
	}
	
	public String acomment() throws Exception{
		return SUCCESS;
	}

	public String areply() throws Exception{
		return SUCCESS;
	}
/******validation***********************************/	
	public boolean public_validation(){
		if(comment==null||comment.length()>500||comment.length()<5){
			tips="文章评论长度不正确[5-500]";return false;
		}
		
		Map session=ActionContext.getContext().getSession();
		String varcode=(String)session.get("randCode");
		code=MD5.code(code.toLowerCase());
		if(code==null||!code.equalsIgnoreCase(varcode)){
			tips="验证码不正确";return false;
		}
		return true;
	}
	
	public void validateAcomment() {
		if(user==null||user.length()>15||user.length()<2){
			tips="姓名长度不正确[2-15]";return;
		}
		if(!public_validation()){
			return ;
		}
		
		articleServer.addNewComment(id, user, comment);
		tips="ok";
	}
	public void validateAreply() {
		if(!public_validation()){
			return ;
		}
		articleServer.replyComment(id,comment);
		tips="ok";
	}
	
	public void validateAdel(){
		if(!ACI.intercept()){
			tips="no_login";return;
		}
		
		articleServer.delArticleById(id);
		tips="ok";
		
		log.error("删除文章{id="+id+"}");
	}
	public void validateCdel(){
		if(!ACI.intercept()){
			tips="no_login";return;
		}
		articleServer.delCommentById(id);
		tips="ok";
		log.error("删除留言{id="+id+"}");
	}
	
	
/****************************/
	@JSON(serialize=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JSON(serialize=false)
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = Chinese.toChinese(user);
	}
	@JSON(serialize=false)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = Chinese.toChinese(comment);
	}
	@JSON(serialize=false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	
}
