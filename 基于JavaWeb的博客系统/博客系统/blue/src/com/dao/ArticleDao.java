package com.dao;

import java.util.List;

import persist.article.Article;
import persist.article.ArticleComment;
import persist.article.ArticleType;

public interface ArticleDao {
	
	public Article getArticle(Integer id);

	public ArticleType getArticleType(Integer id);
	
	public ArticleType getArticleTypeByName(String name);
	
	public ArticleComment getArticleComment(Integer id);
	
	public void save(Object obj);
	
	public void update(Object obj);
	
	public void deleteArticleById(Integer id);
	
	public void deleteArticleType(Integer id);
	
	public void deleteArticleComment(Integer id);
	
	public void deleteByObject(Object obj);
	
	@SuppressWarnings("unchecked")
	public List findAllType();
	
	@SuppressWarnings("unchecked")
	public List findAllArticle();
	
	@SuppressWarnings("unchecked")
	public List findLatestComment(int top);

	@SuppressWarnings("unchecked")
	public List findLatestArticle(int top);
	
	@SuppressWarnings("unchecked")
	public List findArticleByTypeId(Integer type_id);
	
	public Article findArticleById(int id);
	
	@SuppressWarnings("unchecked")
	public List findCommentByArticleId(int id);
	
	@SuppressWarnings("unchecked")
	public List findArticleByPage(int pageNo,int pageSize,int type);
	
	@SuppressWarnings("unchecked")
	public List findInArticle(String keyword);
	
}
