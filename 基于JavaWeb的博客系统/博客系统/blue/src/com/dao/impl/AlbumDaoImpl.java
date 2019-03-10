package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import persist.album.Img;
import persist.album.ImgType;

import com.dao.AlbumDao;
import com.myHibernateDao.MyHibernateDaoSupport;


@Repository("albumDao")
public class AlbumDaoImpl extends MyHibernateDaoSupport implements AlbumDao {
	
	public Img getImgById(Integer id) {
		return (Img)super.getHibernateTemplate().get(Img.class, id);
	}

	public ImgType getImgTypeById(Integer id) {
		return (ImgType)super.getHibernateTemplate().get(ImgType.class, id);
	}

	public ImgType getImgTypeByName(String name) {
		String hql="from ImgType it where it.typeName=?";
		@SuppressWarnings("unchecked")
		List list=super.getHibernateTemplate().find(hql,name);
		if(list.size()>=1){
			return (ImgType)list.get(0);
		}else{
			return null;
		}
	}

	public void save(Object obj) {
		super.getHibernateTemplate().save(obj);
	}

	public void update(Object obj) {
		super.getHibernateTemplate().update(obj);
	}

	public void deleteByObject(Object obj) {
		super.getHibernateTemplate().delete(obj);
	}

	public void deleteImgById(Integer id) {
		super.getHibernateTemplate().delete(this.getImgById(id));
	}

	public void deleteImgTypeById(Integer id) {
		super.getHibernateTemplate().delete(this.getImgTypeById(id));
	}
	@SuppressWarnings("unchecked")
	public List findAllImg() {
		return super.getHibernateTemplate().find("from Img");
	}
	@SuppressWarnings("unchecked")
	public List findAllImgType() {
		return super.getHibernateTemplate().find("from ImgType");
	}
	@SuppressWarnings("unchecked")
	public List findImgByImgTypeId(Integer id) {
		String hql="from Img i where i.imgType.id=? order by time asc";
		return super.getHibernateTemplate().find(hql,id);
	}
	@SuppressWarnings("unchecked")
	public List findImgByPage(int pageNo, int pageSize, int type) {
		int offset=(pageNo-1)*pageSize;
		String hql="from Img a where a.imgType.id="+type+" order by time asc";
		return super.findByPage(hql, offset, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List findLatestImg(int type,int top) {
		String hql;
		if(type==-1){
			hql="from Img order by time desc";
		}else{
			hql="from Img i where i.imgType.id="+type+" order by time desc";
		}
		return super.findByTop(hql, top);
	}
	@SuppressWarnings("unchecked")
	public List findLatestAlbum(int top) {
		String hql="from ImgType order by id desc";
		return super.findByTop(hql, top);
	}
	@SuppressWarnings("unchecked")
	public List findInAlbum(String keyword) {
		String hql="from Img where imgdesc like ? order by time desc";
		keyword="%"+keyword+"%";
		Object[] obj={keyword};
		return super.getHibernateTemplate().find(hql,obj);
	}
	
}
