package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.interceptor.ACI;
import com.opensymphony.xwork2.ActionSupport;
import com.server.AlbumServer;
import com.tools.CompressPic;
import com.tools.Time;

@SuppressWarnings("serial")
public class UploadImgAction extends ActionSupport{
	
	private final Log log=LogFactory.getLog(getClass());
	
	private int typeid;
	private String[] desc;
	private File[] upload;
	private String[] uploadContentType;
	private String[] uploadFileName;
	
	private String savePath;
	private int compressType;
	private int new_width1;
	private int new_height1;
	private int new_width2;
	private int new_height2;

/*******************************************/
	
	@Autowired AlbumServer albumServer;
	
/*****************************************/	
	
	public String execute() throws Exception {
		/***check***/
		if(!ACI.intercept()){
			addFieldError("uploadnologin",getText("struts.messages.nologin"));
			return INPUT;
		}
		File[] files=getUpload();
		if(files.length<1){
			addFieldError("uploadnofile",getText("struts.uploadimg.nofile"));
			return INPUT;
		}
		if(String.valueOf(typeid)==null||String.valueOf(typeid)==""){
			addFieldError("uploadnotype",getText("struts.uploadimg.notype"));
			return INPUT;
		}
		/************/
		String type="";
		String filename="";
		String fileinfo="";
		for(int i=0;i<files.length;i++){
			type=uploadFileName[i].substring((uploadFileName[i]).lastIndexOf("."),uploadFileName[i].length());
			filename=Time.getNoFormatTime()+i+type;//以时间命名
			FileOutputStream fos=new FileOutputStream(getSavePath()+"\\"+filename);
			FileInputStream fis=new FileInputStream(files[i]);
			int size=fis.available();
			String info=(int)((float)(size/1024)+1)+"k";
			byte[] buffer=new byte[1024];
			int len=0;
			while((len=fis.read(buffer))>0){
				fos.write(buffer,0,len);
			}
			fos.close();fis.close();
			
			/***compress*******/
			String widthAndHeight=CompressPic.darwSmallImg(getSavePath(),compressType, filename, new_width1, new_height1, new_width2, new_height2);
			int j=widthAndHeight.lastIndexOf(".");
			int pict=Integer.parseInt(widthAndHeight.substring(j+1));//获取图片类别，是长还是宽
			fileinfo=widthAndHeight.substring(0,j)+"|"+info;
			
			/***save*******/
			if(albumServer.saveInWebapp(typeid, filename, savePath, desc[i], fileinfo, pict)){
				addFieldError("uploadsuccess",getText("struts.uploadimg.success"));
				log.error("上传相片成功{"+typeid+"."+savePath+"/"+filename+"}");
			}else{
				addFieldError("uploadfail",getText("struts.uploadimg.fail"));
				log.error("上传相片失败");
			}
		}
		return SUCCESS;
	}
	
/****************************************/
	public int getTypeid() {
		return typeid;
	}
	
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public void setDesc(String[] desc) {
		this.desc = desc;
	}

	public String[] getDesc() {
		return desc;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@SuppressWarnings("deprecation")
	public String getSavePath() {
		String path=ServletActionContext.getRequest().getRealPath(this.savePath);
		File f=new File(path);
		if(!f.exists()){
			f.mkdir();
		}
		return path;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setCompressType(int compressType) {
		this.compressType = compressType;
	}

	public void setNew_width1(int new_width1) {
		this.new_width1 = new_width1;
	}

	public void setNew_height1(int new_height1) {
		this.new_height1 = new_height1;
	}

	public void setNew_width2(int new_width2) {
		this.new_width2 = new_width2;
	}

	public void setNew_height2(int new_height2) {
		this.new_height2 = new_height2;
	}
	
}
