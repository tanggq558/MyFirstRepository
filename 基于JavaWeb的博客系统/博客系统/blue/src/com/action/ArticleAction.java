package com.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.jsonplugin.annotations.JSON;
import com.interceptor.ACI;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.ArticleServer;
import com.tools.Chinese;
import com.tools.DiviedPage;

@SuppressWarnings({"serial","unchecked"})
public class ArticleAction extends ActionSupport{
	
	private final Log log=LogFactory.getLog(getClass());
	
	private int articleType;
	private String articleTypeName;
	private String articleDesc;
	private String title;
	private String author;
	private String content;
	private String time;
	
	/**************/
	private int type=0;
	
	/*****divied page**********/
	private int showPage=1;
	private int pageSize=1;//每页显示大小
	private int totalPage=1;
	private int aid=1;
	String url="";
	/********/
	
	int top=1;//最新评论数目
	
	/*********/
	private String tips;
	private int myid=-1;
	
	@Autowired ArticleServer articleServer;

/*********function************************************************/
	
	public String addNewArticle() throws Exception {
		if(!public_validation()){
			return INPUT;
		}
		else{
			articleServer.addNewArticle(title, author, content, articleType);
			log.error("发表新文章{"+title+"}");
			addFieldError("addSuccess",getText("struts.addArticle.success"));
			return SUCCESS;
		}	
	}
	public String confEditArticle() throws Exception{
		if(!public_validation()){
			addFieldError("editFail",getText("struts.editArticle.fail"));
			return INPUT;
		}
		else{
			articleServer.editArticle(aid, title, author, content, articleType);
			log.error("编辑文章{"+aid+"."+title+"}");
			addFieldError("editSuccess",getText("struts.editArticle.success"));
			return SUCCESS;
		}
	}
	public String addNewArticleType() throws Exception {
		return SUCCESS;
	}
	public String showArticle() throws Exception{
		//ActionContext.getContext().put("ALL_ARTICLE_TYPE",articleServer.getAllArticleType());
		return SUCCESS;
	}
	public String showArticleList() throws Exception{
		if(String.valueOf(this.getShowPage())==null){
			this.showPage=1;
		}
		if(String.valueOf(this.getType())==null){
			this.type=0;
		}
		
		int totalSize;
		
		List list_type;
		if(type==0)
			totalSize=articleServer.getAllArticleNum();
		else{
			list_type=articleServer.getArticleByTypeId(type);
			totalSize=list_type.size();
		}
		totalPage=(totalSize%pageSize==0)?(totalSize/pageSize):(totalSize/pageSize+1);
		url="showArticleList.action";
		
		
		List alist=articleServer.getArticleByPage(showPage, pageSize,type);
		ActionContext request=ActionContext.getContext();
		request.put("PAGE_LINK",DiviedPage.getPageLink(totalPage,showPage,type,url));
		request.put("ARTICLE_LIST", alist);
		request.getSession().put("ALL_ARTICLE_TYPE",articleServer.getAllArticleType());
		request.put("LATEST_COMMENT",articleServer.getLatestComment(top));
		
		return SUCCESS;
	}
	
/********articleTypeOp*****************************************************/	
	public String renameArticleType() throws Exception{
		return SUCCESS;
	}
	public String delArticleType() throws Exception{
		return SUCCESS;
	}
/*******validation*********************************************/	
	public boolean public_validation(){
		if(!ACI.intercept()){
			addFieldError("nologin",getText("struts.messages.nologin"));
			return false;
		}
		if(title==null||title.length()>20||title.length()<1){
			addFieldError("titleError",getText("struts.addArticle.title.error"));return false;
		}
		if(content==null||content.length()<1){
			addFieldError("contentError",getText("struts.addArticle.content.error"));return false;
		}
		if(articleType<0){
			addFieldError("typeError",getText("struts.addArticle.type.error"));return false;
		}
		return true;
	}

	public void validateAddNewArticleType() {
		if(!ACI.intercept()){
			tips="no_login";return;
		}
		if(articleDesc==null||articleDesc.length()>15||articleDesc.length()<8){
			tips="描述长度不正确,长度在8-15 之间";return;
		}
		if(articleTypeName==null||articleTypeName.length()>8||articleTypeName.length()<4){
			tips="类别长度不正确,长度在4-8 之间";return;
		}
		myid=articleServer.addNewArticleType(articleTypeName,articleDesc);
		if(myid>0){
			tips="ok";
			log.error("新增文章类别{"+myid+"."+articleTypeName+"}");
		}else{
			tips="类别名已经存在,请重新输入";
		}
	}
	
	public void validateRenameArticleType(){
		if(!ACI.intercept()){
			tips="no_login";return;
		}
		if(articleTypeName==null||articleTypeName.length()>8||articleTypeName.length()<4){
			tips="类别长度不正确,长度在4-8 之间";return;
		}
		articleServer.renameArticleType(aid, articleTypeName);
		tips="ok";
		log.error("修改文章类别{"+aid+"."+articleTypeName+"}");
	}
	
	public void validateDelArticleType(){
		if(!ACI.intercept()){
			tips="no_login";return;
		}
		if(articleServer.getArticleByTypeId(aid).size()>0){
			tips="此类别下存在文章,不能删除";
		}else{
			articleServer.delArticleTypeById(aid);
			tips="ok";
			log.error("删除文章类别{id="+aid+"}");
		}
	}
	
	
/**********************************************************/	
	@JSON(serialize=false)
	public int getArticleType() {
		return articleType;
	}
	public void setArticleType(int articleType) {
		this.articleType = articleType;
	}
	@JSON(serialize=false)
	public String getArticleTypeName() {
		return articleTypeName;
	}
	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = Chinese.toChinese(articleTypeName);
	}
	@JSON(serialize=false)
	public String getArticleDesc() {
		return articleDesc;
	}
	public void setArticleDesc(String articleDesc) {
		this.articleDesc = Chinese.toChinese(articleDesc);
	}
	@JSON(serialize=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@JSON(serialize=false)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@JSON(serialize=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JSON(serialize=false)
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@JSON(serialize=false)
	public int getShowPage() {
		return showPage;
	}
	public void setShowPage(int showPage) {
		this.showPage = showPage;
	}
	@JSON(serialize=false)
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	@JSON(serialize=false)
	public void setType(int type) {
		this.type = type;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}

	/***************/
	public String getTips() {
		return tips;
	}
	public int getType() {
		return type;
	}
	
	public int getMyid() {
		return myid;
	}
	public void setMyid(int myid) {
		this.myid = myid;
	}
	/***************/
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTop(int top) {
		this.top = top;
	}
	
}
