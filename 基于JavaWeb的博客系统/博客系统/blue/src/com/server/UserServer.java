package com.server;

import persist.user.Login;
import persist.user.UserInfo;

public interface UserServer {
	
	public Login login(String username);
	
	public UserInfo getInfoByLoginName(String username);
	
	public Login login(int id);
	
	public void update(Object obj);
}
