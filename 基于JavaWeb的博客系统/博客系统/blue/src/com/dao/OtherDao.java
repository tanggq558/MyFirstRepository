package com.dao;

import persist.other.Other;

public interface OtherDao {
	
	public Other getOtherById(int id);
	
	public void update(Object obj);
}
