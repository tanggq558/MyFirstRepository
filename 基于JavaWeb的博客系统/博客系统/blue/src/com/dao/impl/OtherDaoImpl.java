package com.dao.impl;

import org.springframework.stereotype.Repository;

import persist.other.Other;

import com.dao.OtherDao;
import com.myHibernateDao.MyHibernateDaoSupport;

@Repository("otherDao")
public class OtherDaoImpl extends MyHibernateDaoSupport implements OtherDao {

	public Other getOtherById(int id) {
		return (Other)super.getHibernateTemplate().get(Other.class, id);
	}

	public void update(Object obj) {
		super.getHibernateTemplate().update(obj);
	}

}
