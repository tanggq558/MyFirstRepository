package com.server;

import java.util.List;

import persist.album.Img;
import persist.album.ImgType;

public interface AlbumServer {
	
	public Img getImgById(int id);
	
	public ImgType getImgTypeById(int id);
	
	public ImgType getImgTypeByName(String name);
	@SuppressWarnings("unchecked")
	public List getAllImg();
	@SuppressWarnings("unchecked")
	public List getAllImgType();
	@SuppressWarnings("unchecked")
	public List getImgByImgTypeId(int id);
	@SuppressWarnings("unchecked")
	public List getOldestImgInEachType(int top);
	
	public int addNewImgType(String typeName,String typeDesc);
	
	public boolean saveInWebapp(int typeId,String filename,String url,String desc,String info,int type);
	
	public void delImgById(int id);
	
	public void delImgTypeById(int id);
	
	public void renameImgType(int id,String typeName,String typeDesc);
	
	public void renameImg(int id,String imgDesc);
	@SuppressWarnings("unchecked")
	public List getImgByPageByTypeId(int showPage, int pageSize,int type);
	@SuppressWarnings("unchecked")
	public List getLatestImgByTypeInTop(int type,int top);
	@SuppressWarnings("unchecked")
	public List getSearchResultInAlbum(String keyword);
}
