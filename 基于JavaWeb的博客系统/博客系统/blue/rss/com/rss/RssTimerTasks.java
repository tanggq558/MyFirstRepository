package com.rss;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.server.AlbumServer;
import com.server.ArticleServer;

public class RssTimerTasks extends TimerTask{

	@Autowired ArticleServer articleServer;
	@Autowired AlbumServer albumServer;
	
	private String path;
	private int top;
	
	public void run() {
		RssAction rss=new RssAction();//Éú³Érss
		try {
			rss.testBuildObject(articleServer.getLatestArticle(top),
								null,
								path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setTop(int top) {
		this.top = top;
	}
	
}
