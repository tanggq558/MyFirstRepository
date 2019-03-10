package com.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persist.leavemsg.Leavemsg;

import com.dao.LeavemsgDao;
import com.server.LeavemsgServer;
import com.tools.Time;

@Service("leavemsgServer")
public class LeavemsgServerImpl implements LeavemsgServer {

	@Autowired LeavemsgDao leavemsgDao;
	
	public Leavemsg getMsgById(int id) {
		return leavemsgDao.getMsgById(id);
	}
	
	@SuppressWarnings("unchecked")
	public List getAllLeaveMsg() {
		return leavemsgDao.findAllMsg();
	}
	
	@SuppressWarnings("unchecked")
	public List getMsgByPage(int showPage, int pageSize) {
		return leavemsgDao.findMsgByPage(showPage, pageSize);
	}
	
	public int leaveMsg(String name, String content) {
		Leavemsg msg=new Leavemsg();
		msg.setComName(name);
		msg.setComContent(content);
		msg.setComTime(Time.getTime());
		
		leavemsgDao.save(msg);
		return msg.getId();
	}
	
	
	public int replyMsg(int id,String content) {
		Leavemsg msg=leavemsgDao.getMsgById(id);
		msg.setComContent(content);
		leavemsgDao.update(msg);
		return msg.getId();
	}

	public void delMsg(int id) {
		leavemsgDao.deleteMsgById(id);
	}
	@SuppressWarnings("unchecked")
	public List getLatestMsg(int top) {
		return leavemsgDao.findLatestMsg(top);
	}
}
