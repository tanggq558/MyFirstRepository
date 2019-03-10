package com.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persist.user.Login;
import persist.user.UserInfo;

import com.dao.UserDao;
import com.server.UserServer;

@Service("userServer")
public class UserServerImpl implements UserServer {

	@Autowired UserDao userDao;
	
	public Login login(String username) {
		return userDao.getLogin(username);
	}

	public Login login(int id) {
		return userDao.getLogin(id);
	}

	public UserInfo getInfoByLoginName(String username) {
		return userDao.findUserByLoginName(username);
	}

	public void update(Object obj) {
		userDao.update(obj);
	}
	
}
