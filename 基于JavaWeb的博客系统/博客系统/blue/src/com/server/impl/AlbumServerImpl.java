package com.server.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persist.album.Img;
import persist.album.ImgType;

import com.dao.AlbumDao;
import com.myHibernateDao.MyHibernateDaoSupport;
import com.server.AlbumServer;
import com.tools.Time;

@SuppressWarnings("unchecked")
@Service("albumServer")
public class AlbumServerImpl extends MyHibernateDaoSupport implements AlbumServer {

	@Autowired AlbumDao albumDao;
	
	public int addNewImgType(String typeName,String typeDesc) {
		ImgType it=new ImgType();
		it.setTypeName(typeName);
		it.setTypeDesc(typeDesc);
		albumDao.save(it);
		return it.getId();
	}

	@SuppressWarnings("deprecation")
	public void delImgById(int id) {
		Img img=albumDao.getImgById(id);
		String url=img.getImgurl();
		String name=img.getImgname();
		String path=url+"\\"+name;
		String spath=url+"\\s"+name;
		String p=ServletActionContext.getRequest().getRealPath(path);
		String sp=ServletActionContext.getRequest().getRealPath(spath);
		
		File f=new File(p);
		File sf=new File(sp);
		
		if(f.exists()){
			f.delete();
		}
		if(sf.exists()){
			sf.delete();
		}
		
		albumDao.deleteImgById(id);
	}

	public void delImgTypeById(int id) {
		
		List list=albumDao.findImgByImgTypeId(id);
		if(list.size()>0){
			ImgType it=albumDao.getImgTypeById(1);//ƒ¨»œ∑÷¿‡id
			for(int i=0;i<list.size();i++){
				Img img=(Img)list.get(i);
				img.setImgType(it);
				albumDao.update(img);
			}
		}
		albumDao.deleteImgTypeById(id);
	}

	
	public List getAllImg() {
		return null;
	}
	
	public List getAllImgType() {
		return albumDao.findAllImgType();
	}

	public Img getImgById(int id) {
		return albumDao.getImgById(id);
	}
	
	public List getImgByImgTypeId(int id) {
		return albumDao.findImgByImgTypeId(id);
	}

	public ImgType getImgTypeById(int id) {
		return albumDao.getImgTypeById(id);
	}
	
	public ImgType getImgTypeByName(String name) {
		return albumDao.getImgTypeByName(name);
	}
	public List getOldestImgInEachType(int top) {
		List type;
		if(top>0)
			type=albumDao.findLatestAlbum(top);
		else
			type=this.getAllImgType();
		List oldestList=new ArrayList();
		for(int i=0;i<type.size();i++){
			Integer type_id=((ImgType)type.get(i)).getId();
			List ilist=albumDao.findImgByImgTypeId(type_id);
			if(ilist!=null&&ilist.size()>0){
				Img img=(Img)ilist.get(0);
				oldestList.add(img);
			}else{
				oldestList.add(albumDao.getImgTypeById(type_id));
			}
		}
		return oldestList;
	}

	public void renameImgType(int id, String typeName,String typeDesc) {
		ImgType it=albumDao.getImgTypeById(id);
		it.setTypeName(typeName);
		it.setTypeDesc(typeDesc);
		albumDao.update(it);
	}
	
	public void renameImg(int id, String imgDesc) {
		Img im=albumDao.getImgById(id);
		im.setImgdesc(imgDesc);
		albumDao.update(im);
	}

	public boolean saveInWebapp(int typeId,String filename,String url,String desc,String info,int type){
		if(desc==null||desc.equals("")) desc=filename;
		ImgType it=albumDao.getImgTypeById(typeId);
		Img img=new Img();
		img.setImgType(it);
		img.setImgname(filename);
		img.setImgurl(url);
		img.setImgdesc(desc);
		img.setImginfo(info);
		img.setImgtype(type==1?true:false);
		img.setTime(Time.getTime());
		
		albumDao.save(img);
		return true;
	}
	
	public List getImgByPageByTypeId(int showPage, int pageSize, int type) {
		return albumDao.findImgByPage(showPage, pageSize, type);
	}
	
	public List getLatestImgByTypeInTop(int type, int top) {
		return albumDao.findLatestImg(type, top);
	}
	
	public List getSearchResultInAlbum(String keyword) {
		return albumDao.findInAlbum(keyword);
	}
}
