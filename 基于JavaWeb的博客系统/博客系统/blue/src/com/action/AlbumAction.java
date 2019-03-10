package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.AlbumServer;
import com.tools.DiviedPage;

@SuppressWarnings({"serial"})
public class AlbumAction extends ActionSupport{
	
	private int type=1;
	private int id;
	/*****divied page**********/
	private int showPage=1;
	private int pageSize=1;//每页显示大小
	private int totalPage=1;
	String url="";
	/********/
	private int top=5;//显示5个相册
	
	
	
/******autowired*******************************************/	
	@Autowired AlbumServer albumServer;
	
/****function***************************************/
	
	@SuppressWarnings("unchecked")
	public String imgListShow() throws Exception {
		if(String.valueOf(this.getShowPage())==null){
			this.showPage=1;
		}
		if(String.valueOf(this.getType())==null){
			this.type=1;
		}
		
		ActionContext.getContext().put("IMG_LIST_SHOW", albumServer.getOldestImgInEachType(top));
		ActionContext.getContext().put("ALBUM_INFO",albumServer.getImgTypeById(type));
		/********/
		url="imgListShow.action";
		
		List alist=albumServer.getImgByPageByTypeId(showPage, pageSize, type);
		ActionContext.getContext().put("IMG_LIST_SHOW2",alist);
		/*************/
		int totalSize=albumServer.getImgByImgTypeId(type).size();
		totalPage=(totalSize%pageSize==0)?(totalSize/pageSize):(totalSize/pageSize+1);
		ActionContext.getContext().put("PAGE_LINK",DiviedPage.getPageLink(totalPage,showPage,type,url));
		
		return SUCCESS;
	}
	
	public String imgShow() throws Exception {
		ActionContext.getContext().put("IMG", albumServer.getImgById(id));
		return SUCCESS;
	}

/********************************/
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getShowPage() {
		return showPage;
	}

	public void setShowPage(int showPage) {
		this.showPage = showPage;
	}

/*************************/	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTop(int top) {
		this.top = top;
	}
	
}
