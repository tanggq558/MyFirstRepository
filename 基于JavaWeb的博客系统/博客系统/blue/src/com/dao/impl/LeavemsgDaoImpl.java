package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import persist.leavemsg.Leavemsg;

import com.dao.LeavemsgDao;
import com.myHibernateDao.MyHibernateDaoSupport;

@Repository("leavemsgDao")
public class LeavemsgDaoImpl extends MyHibernateDaoSupport implements LeavemsgDao {

	public Leavemsg getMsgById(Integer id) {
		return (Leavemsg)super.getHibernateTemplate().get(Leavemsg.class, id);
	}
	
	public void save(Object obj) {
		super.getHibernateTemplate().save(obj);
	}
	
	public void update(Object obj) {
		super.getHibernateTemplate().update(obj);
	}
	
	public void deleteMsgById(Integer id) {
		super.getHibernateTemplate().delete(this.getMsgById(id));
	}
	@SuppressWarnings("unchecked")
	public List findAllMsg() {
		return super.getHibernateTemplate().find("from Leavemsg");
	}
	@SuppressWarnings("unchecked")
	public List findMsgByPage(int pageNo, int pageSize) {
		int offset=(pageNo-1)*pageSize;
		String hql="from Leavemsg order by comTime desc";
		return super.findByPage(hql, offset, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List findLatestMsg(int top) {
		String hql="from Leavemsg order by comTime desc";
		return super.findByTop(hql, top);
	}
	
}
