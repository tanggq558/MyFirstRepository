package com.dao;

import java.util.List;


import persist.user.Login;
import persist.user.UserInfo;

public interface UserDao {
	
	public Login getLogin(Integer id);
	
	public Login getLogin(String username);
	
	public UserInfo getUser(Integer id);
	
	public void save(Object obj);
	
	public void update(Object obj);
	
	public void deleteLoginById(Integer id);
	
	public void deleteUserById(Integer id);
	
	public void deleteByObject(Object obj);
	@SuppressWarnings("unchecked")
	public List findAllLogin();
	@SuppressWarnings("unchecked")
	public List findAllUser();
	
	public UserInfo findUserByLoginId(Integer login_id);
	
	public UserInfo findUserByLoginName(String username);
	
}
