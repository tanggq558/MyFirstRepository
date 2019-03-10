package com.server;

import java.util.List;

import persist.article.Article;

public interface ArticleServer {
	/**
	 * ��ȡ�����������
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List getAllArticleType();
	/**
	 * ��ȡ��������
	 * @return
	 */
	public int getAllArticleNum();
	
	public Article getArticleById(int id);
	
	@SuppressWarnings("unchecked")
	public List getArticleCommentById(int id);
	/**
	 * ͨ���������ID��������
	 * @param type_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getArticleByTypeId(Integer type_id);
	/**
	 * ��ȡÿ������һƪ��������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLatestArticleInEachType();
	
	public int addNewArticleType(String articleTypeName,String articleDesc);
	
	public boolean addNewArticle(String title,String author,String content,int articleType);

	public boolean editArticle(int id,String title,String author,String content,int articleType);
	@SuppressWarnings("unchecked")
	public List getArticleByPage(int pageNo,int pageSize,int type);
	@SuppressWarnings("unchecked")
	public List getLatestComment(int top);
	@SuppressWarnings("unchecked")
	public List getLatestArticle(int top);
	
	public void addNewComment(int id,String user,String comment);

	public void replyComment(int id,String comment);
	
	public void delArticleById(int id);
	
	public void delCommentById(int id);
	
	public void renameArticleType(int id,String typeName);

	public void delArticleTypeById(int id);
	@SuppressWarnings("unchecked")
	public List getSearchResultInArticle(String keyword);
}
