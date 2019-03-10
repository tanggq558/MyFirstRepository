package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import persist.article.Article;
import persist.article.ArticleComment;
import persist.article.ArticleType;

import com.dao.ArticleDao;
import com.myHibernateDao.MyHibernateDaoSupport;

@Repository("articleDao")
public class ArticleDaoImpl extends MyHibernateDaoSupport implements ArticleDao {
	
	public Article getArticle(Integer id) {
		return (Article)super.getHibernateTemplate().get(Article.class, id);
	}
	
	public ArticleType getArticleTypeByName(String name) {
		String sql="from ArticleType at where at.typeName=?";
		@SuppressWarnings("unchecked")
		List list=super.getHibernateTemplate().find(sql,name);
		if(list.size()>=1){
			return (ArticleType)list.get(0);
		}else
			return null;
	}

	public ArticleComment getArticleComment(Integer id) {
		return (ArticleComment)super.getHibernateTemplate().get(ArticleComment.class, id);
	}

	public ArticleType getArticleType(Integer id) {
		return (ArticleType)super.getHibernateTemplate().get(ArticleType.class, id);
	}

	public void save(Object obj) {
		super.getHibernateTemplate().save(obj);
	}

	public void update(Object obj) {
		super.getHibernateTemplate().update(obj);
	}
	
	public void deleteArticleById(Integer id) {
		super.getHibernateTemplate().delete(this.getArticle(id));
	}

	public void deleteArticleComment(Integer id) {
		super.getHibernateTemplate().delete(this.getArticleComment(id));
	}

	public void deleteArticleType(Integer id) {
		super.getHibernateTemplate().delete(this.getArticleType(id));
	}

	public void deleteByObject(Object obj) {
		super.getHibernateTemplate().delete(obj);
	}
	@SuppressWarnings("unchecked")
	public List findAllType() {
		return super.getHibernateTemplate().find("from ArticleType");
	}
	@SuppressWarnings("unchecked")
	public List findArticleByTypeId(Integer type_id) {
		String hql="from Article a where a.articleType.id=? order by time desc";
		Object[] params={type_id};
		return super.getHibernateTemplate().find(hql,params);
	}
	@SuppressWarnings("unchecked")
	public List findAllArticle() {
		return super.getHibernateTemplate().find("from Article");
	}
	@SuppressWarnings("unchecked")
	public Article findArticleById(int id) {
		String hql="from Article where id="+id;
		List a=super.getHibernateTemplate().find(hql);
		if(a.size()==1)
			return (Article)a.get(0);
		else
			return null;
	}
	@SuppressWarnings("unchecked")
	public List findCommentByArticleId(int id) {
		String hql="from ArticleComment ac where ac.article.id="+id+" order by ac.time desc";
		return super.getHibernateTemplate().find(hql);
	}
	@SuppressWarnings("unchecked")
	public List findArticleByPage(int pageNo, int pageSize,int type) {
		int offset=(pageNo-1)*pageSize;
		String hql;
		if(type==0)
			hql="from Article order by time desc";
		else
			hql="from Article a where a.articleType.id="+type+" order by time desc";
		return super.findByPage(hql, offset, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List findLatestComment(int top) {
		String hql="from ArticleComment order by time desc";
		
		return super.findByTop(hql, top);
	}
	@SuppressWarnings("unchecked")
	public List findLatestArticle(int top) {
		String hql="from Article order by time desc";
		
		return super.findByTop(hql, top);
	}
	@SuppressWarnings("unchecked")
	public List findInArticle(String keyword) {
		String hql="from Article where title like ? or author like ? or content like ? order by time desc";
		keyword="%"+keyword+"%";
		Object[] obj={keyword,keyword,keyword};
		return super.getHibernateTemplate().find(hql,obj);
	}
	
}
