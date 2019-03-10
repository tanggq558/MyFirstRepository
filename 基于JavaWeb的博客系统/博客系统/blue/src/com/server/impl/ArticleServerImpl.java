package com.server.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persist.article.Article;
import persist.article.ArticleComment;
import persist.article.ArticleType;

import com.dao.ArticleDao;
import com.server.ArticleServer;
import com.tools.Time;

@Service("articleServer")
@SuppressWarnings("unchecked")
public class ArticleServerImpl implements ArticleServer {

	@Autowired ArticleDao articleDao;

	
	public List getAllArticleType() {
		return articleDao.findAllType();
	}
	
	public int getAllArticleNum(){
		return articleDao.findAllArticle().size();
	}

	public Article getArticleById(int id) {
		Article art=(Article)articleDao.findArticleById(id);
		if(art!=null){
			art.setScan(art.getScan()+1);
			articleDao.update(art);
		}
		return art;
	}


	public List getArticleCommentById(int id) {
		return articleDao.findCommentByArticleId(id);
	}

	public List getArticleByTypeId(Integer type_id) {
		return articleDao.findArticleByTypeId(type_id);
	}

	public List getLatestArticleInEachType() {
		List type=this.getAllArticleType();
		List latestList=new ArrayList();
		for(int i=0;i<type.size();i++){
			Integer type_id=((ArticleType)type.get(i)).getId();
			List alist=articleDao.findArticleByTypeId(type_id);
			if(alist!=null&&alist.size()>0){
				Article article=(Article)alist.get(0);
				latestList.add(article);
			}
		}
		return latestList;
	}

	public int addNewArticleType(String articleTypeName,String articleDesc) {
		ArticleType at=new ArticleType();
		if(articleDao.getArticleTypeByName(articleTypeName)!=null){
			return -1;
		}else{
			at.setTypeName(articleTypeName);
			at.setTypeDesc(articleDesc);
			articleDao.save(at);
			return at.getId();
		}
	}

	public boolean addNewArticle(String title,String author,String content,int articleType){
		ArticleType at=articleDao.getArticleType(articleType);
		Article article=new Article();
		article.setTitle(title);
		article.setAuthor(author);
		article.setContent(content);
		article.setScan(0);
		article.setTime(Time.getTime());
		article.setArticleType(at);
		
		articleDao.save(article);
		return true;
	}

	public boolean editArticle(int id, String title, String author,
		String content, int articleType) {
		Article a=articleDao.getArticle(id);
		ArticleType at=articleDao.getArticleType(articleType);
		a.setArticleType(at);
		a.setTitle(title);
		a.setContent(content);
		a.setAuthor(author);
		
		articleDao.update(a);
		return true;
	}
	
	public List getArticleByPage(int pageNo,int pageSize,int type) {
		return articleDao.findArticleByPage(pageNo,pageSize,type);
	}
	
	public List getLatestComment(int top) {
		return articleDao.findLatestComment(top);
	}
	
	public List getLatestArticle(int top) {
		return articleDao.findLatestArticle(top);
	}

	public void addNewComment(int id, String user, String comment) {
		ArticleComment ac=new ArticleComment();
		Article a=(Article)articleDao.getArticle(id);
		ac.setArticle(a);
		ac.setUser(user);
		ac.setComment(comment);
		ac.setTime(Time.getTime());
		articleDao.save(ac);
	}
	
	public void replyComment(int id, String comment) {
		ArticleComment ac=articleDao.getArticleComment(id);
		ac.setComment(comment);
		articleDao.update(ac);
	}

	public void delArticleById(int id) {
		articleDao.deleteArticleById(id);
	}

	public void delCommentById(int id) {
		articleDao.deleteArticleComment(id);
	}
	
	public void renameArticleType(int id,String typeName) {
		ArticleType at=(ArticleType)articleDao.getArticleType(id);
		at.setTypeName(typeName);
		articleDao.update(at);
	}

	public void delArticleTypeById(int id) {
		articleDao.deleteArticleType(id);
	}
	
	public List getSearchResultInArticle(String keyword) {
		return articleDao.findInArticle(keyword);
	}
	
}