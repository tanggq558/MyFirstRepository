package com.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.server.AlbumServer;
import com.server.ArticleServer;

@SuppressWarnings("serial")
public class SearchAction extends ActionSupport{

	private String key;
	
/******************************/
	@Autowired ArticleServer articleServer;
	@Autowired AlbumServer albumServer;

/******************************/	
	public String execute() throws Exception {
		ActionContext request=ActionContext.getContext();
		request.put("SEARCHRESULT_ARTICLE",articleServer.getSearchResultInArticle(key));
		request.put("SEARCHRESULT_ALBUM",albumServer.getSearchResultInAlbum(key));
		return SUCCESS;
	}

/****************************************/	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
