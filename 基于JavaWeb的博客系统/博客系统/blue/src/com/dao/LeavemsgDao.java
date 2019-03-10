package com.dao;

import java.util.List;

import persist.leavemsg.Leavemsg;

public interface LeavemsgDao {
	
	public Leavemsg getMsgById(Integer id);
	
	public void save(Object obj);
	
	public void update(Object obj);
	
	public void deleteMsgById(Integer id);
	
	@SuppressWarnings("unchecked")
	public List findAllMsg();
	@SuppressWarnings("unchecked")
	public List findMsgByPage(int pageNo,int pageSize);
	@SuppressWarnings("unchecked")
	public List findLatestMsg(int top);
}
