package com.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.jsonplugin.annotations.JSON;
import com.interceptor.ACI;
import com.opensymphony.xwork2.ActionSupport;
import com.server.AlbumServer;
import com.tools.Chinese;

@SuppressWarnings({"serial"})
public class AlbumAjaxAction extends ActionSupport{
	
	private final Log log=LogFactory.getLog(getClass());
	
	private int iid;
	private int id;
	private String typeName;
	private String typeDesc;
	private String imgDesc;
	
	/**********/
	private String tips;
	private int myid=-1;
	
	@SuppressWarnings("unchecked")
	private List mylist=null;

/*****************************/
	
	@Autowired AlbumServer albumServer;
	
/****************************/	
	public String addAlbum() throws Exception {
		return SUCCESS;
	}
	public String alterAlbum() throws Exception {
		return SUCCESS;
	}
	public String delAlbum() throws Exception {
		return SUCCESS;
	}
	public String alterImg() throws Exception {
		return SUCCESS;
	}
	public String delImg() throws Exception{
		return SUCCESS;
	}
	
	public String showImgAjax() throws Exception{
		return SUCCESS;
	}

/*******************************/
	
	public boolean public_validate(){
		if(!ACI.intercept()){
			tips="no_login";return false;
		}
		if(typeName==null||typeName.length()>10||typeName.length()<1){
			tips="������Ƴ��Ȳ���ȷ,1-10λ";
			return false;
		}
		if(typeDesc==null||typeDesc.length()>25||typeDesc.length()<1){
			tips="����������Ȳ���ȷ,1-25λ";
			return false;
		}
		if(albumServer.getImgTypeByName(typeName)!=null){
			tips="��������Ѿ�����";
			return false;
		}
		return true;
	}
	
	public void validateAddAlbum() {
		if(public_validate()){
			myid=albumServer.addNewImgType(typeName, typeDesc);
			tips="ok";
			
			log.error("��������{"+myid+"."+typeName+"}");
		}
	}
	public void validateAlterAlbum() {
		if(public_validate()){
			albumServer.renameImgType(id, typeName, typeDesc);
			tips="ok";
			
			log.error("�޸������Ϣ{"+id+"."+typeName+"}");
		}
	}
	
	public void validateDelAlbum() {
		if(!ACI.intercept()){
			tips="no_login";return;
		}
		albumServer.delImgTypeById(id);
		tips="ok";
		log.error("ɾ�����{id="+id+"}");
	}
	public void validateAlterImg() {
		if(!ACI.intercept()){
			tips="no_login";return;
		}
		if(imgDesc==null||imgDesc.length()>15||imgDesc.length()<1){
			tips="��Ƭ�������Ȳ���ȷ,1-15λ";return;
		}
		albumServer.renameImg(id, imgDesc);
		tips="ok";
		
		log.error("�޸���Ƭ��Ϣ{"+id+"."+imgDesc+"}");
		
	}
	
	public void validateDelImg() {
		if(!ACI.intercept()){
			tips="no_login";return;
		}
		albumServer.delImgById(id);
		tips="ok";
		
		log.error("ɾ����Ƭ{id="+id+"}");
	}
	
	public void validateShowImgAjax() {
		myid=iid;
		mylist=albumServer.getImgByImgTypeId(id);
	}
	
/*****************************/
	@JSON(serialize=false)
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	
	@JSON(serialize=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JSON(serialize=false)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = Chinese.toChinese(typeName);
	}
	@JSON(serialize=false)
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = Chinese.toChinese(typeDesc);
	}
	@JSON(serialize=false)
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = Chinese.toChinese(imgDesc);
	}
	/*************/
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public int getMyid() {
		return myid;
	}
	public void setMyid(int myid) {
		this.myid = myid;
	}
	
	@SuppressWarnings("unchecked")
	public List getMylist() {
		return mylist;
	}
	@SuppressWarnings("unchecked")
	public void setMylist(List mylist) {
		this.mylist = mylist;
	}
	
}
