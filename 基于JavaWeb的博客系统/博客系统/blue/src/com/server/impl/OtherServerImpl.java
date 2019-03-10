package com.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import persist.other.Other;

import com.dao.OtherDao;
import com.server.OtherServer;

@Repository("otherServer")
public class OtherServerImpl implements OtherServer {

	@Autowired OtherDao otherDao;
	
	public int updateAccess(int id) {
		Other ot=(Other)otherDao.getOtherById(id);
		int access=ot.getAccess();
		ot.setAccess(access+1);
		otherDao.update(ot);
		return ot.getAccess();
	}

}
