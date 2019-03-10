package com.server;

import java.util.List;

import persist.leavemsg.Leavemsg;

public interface LeavemsgServer {
	
	public Leavemsg getMsgById(int id);
	@SuppressWarnings("unchecked")
	public List getAllLeaveMsg();
	
	public int leaveMsg(String name,String content);

	public int replyMsg(int id,String content);
	
	public void delMsg(int id);
	@SuppressWarnings("unchecked")
	public List getMsgByPage(int showPage, int pageSize);
	@SuppressWarnings("unchecked")
	public List getLatestMsg(int top);
	
}
