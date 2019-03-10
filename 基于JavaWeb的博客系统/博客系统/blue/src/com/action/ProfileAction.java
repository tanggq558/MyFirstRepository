package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import persist.album.Img;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.AlbumServer;
import com.server.ArticleServer;
import com.server.LeavemsgServer;
import com.server.UserServer;

@SuppressWarnings({"serial"})
public class ProfileAction extends ActionSupport{
	
	private int uid=1;
	private int type=1;
	private int article_top=5;
	private int msg_top=3;
	private int img_top=6;
	
	/**********/
	@Autowired UserServer userServer;
	@Autowired ArticleServer articleServer;
	@Autowired LeavemsgServer leavemsgServer;
	@Autowired AlbumServer albumServer;
	
	@SuppressWarnings("unchecked")
	public String showProfile() throws Exception {
		ActionContext act=ActionContext.getContext();
		act.put("USER_INFO", userServer.login(uid));
		act.put("LATEST_ARTICLE",articleServer.getLatestArticle(article_top));
		act.put("LATEST_LEAVEMSG",leavemsgServer.getLatestMsg(msg_top));
		
		
		
		List latest_img_top=albumServer.getLatestImgByTypeInTop(type, img_top);
		int size=latest_img_top.size();
		if(size<2){
			act.put("LATEST_IMG_STATUS",size);
		}else{
			act.put("LATEST_IMG_STATUS",null);
			String urls="",txts="",links="",sp="|";
			for(int i=0;i<size;i++){
				Img img=(Img)latest_img_top.get(i);
				if(i==size-1) sp="";
				urls+=img.getImgurl()+"/s"+img.getImgname()+sp;
				txts+=img.getImgdesc()+sp;
				links+="imgShow.action?id="+img.getId()+sp;
			}

			act.put("LATEST_IMG_URLS",urls);
			act.put("LATEST_IMG_TXTS",txts);
			act.put("LATEST_IMG_LINKS",links);
		}
		
		return SUCCESS;
	}
	
	public String userinfoOp() throws Exception{
		ActionContext act=ActionContext.getContext();
		act.put("USER_INFO_OP", userServer.login(uid));
		return SUCCESS;
	}

	
/*********************************/	
	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setArticle_top(int article_top) {
		this.article_top = article_top;
	}

	public void setMsg_top(int msg_top) {
		this.msg_top = msg_top;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setImg_top(int img_top) {
		this.img_top = img_top;
	}
	
}
