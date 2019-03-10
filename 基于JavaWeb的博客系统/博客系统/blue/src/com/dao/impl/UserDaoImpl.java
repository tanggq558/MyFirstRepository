package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import persist.user.Login;
import persist.user.UserInfo;

import com.dao.UserDao;
import com.myHibernateDao.MyHibernateDaoSupport;

@Repository("userDao")
public class UserDaoImpl extends MyHibernateDaoSupport implements UserDao {

	public Login getLogin(Integer id) {
		return (Login)super.getHibernateTemplate().get(Login.class, id);
	}

	public Login getLogin(String username) {
		String hql="from Login where username=?";
		Object[] params={username};
		@SuppressWarnings("unchecked")
		List list=super.getHibernateTemplate().find(hql,params);
		if(list.size()==1)
			return (Login)list.get(0);
		else
			return null;
	}

	public UserInfo getUser(Integer id) {
		return (UserInfo)super.getHibernateTemplate().get(UserInfo.class, id);
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

	public void deleteLoginById(Integer id) {
		super.getHibernateTemplate().delete(this.getLogin(id));
	}

	public void deleteUserById(Integer id) {
		super.getHibernateTemplate().delete(this.getUser(id));
	}
	@SuppressWarnings("unchecked")
	public List findAllLogin() {
		return super.getHibernateTemplate().find("from Login");
	}
	@SuppressWarnings("unchecked")
	public List findAllUser() {
		return super.getHibernateTemplate().find("from UserInfo");
	}

	public UserInfo findUserByLoginId(Integer login_id) {
		String hql="from UserInfo u where u.login.id=?";
		Object[] params={login_id};
		return (UserInfo)super.getHibernateTemplate().find(hql,params);
	}

	public UserInfo findUserByLoginName(String username) {
		String hql="from UserInfo u where u.login.username=?";
		Object[] params={username};
		@SuppressWarnings("unchecked")
		List list=super.getHibernateTemplate().find(hql,params);
		if(list.size()==1)
			return (UserInfo)list.get(0);
		else
			return null;
	}

}
