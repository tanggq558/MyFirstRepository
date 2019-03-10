package com.dao;

import java.util.List;

import persist.album.Img;
import persist.album.ImgType;

public interface AlbumDao {
	
	public Img getImgById(Integer id);
	
	public ImgType getImgTypeById(Integer id);
	
	public ImgType getImgTypeByName(String name);
	
	public void save(Object obj);
	
	public void update(Object obj);
	
	public void deleteImgTypeById(Integer id);
	
	public void deleteImgById(Integer id);
	
	public void deleteByObject(Object obj);
	
	
	@SuppressWarnings("unchecked")
	public List findAllImgType();
	@SuppressWarnings("unchecked")
	public List findAllImg();
	@SuppressWarnings("unchecked")
	public List findImgByImgTypeId(Integer id);
	@SuppressWarnings("unchecked")
	public List findImgByPage(int pageNo,int pageSize,int type);
	@SuppressWarnings("unchecked")
	public List findLatestImg(int type,int top);
	@SuppressWarnings("unchecked")
	public List findLatestAlbum(int top);
	@SuppressWarnings("unchecked")
	public List findInAlbum(String keyword);
}
