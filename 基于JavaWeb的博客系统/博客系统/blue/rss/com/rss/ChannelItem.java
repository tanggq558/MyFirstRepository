package com.rss;

import java.util.Date;

public class ChannelItem {
	private String title;// Rss�ļ���Item�ı���
	private String link;// Rss�ļ���Item��Ӧ������
	private String description;// Item������
	private Date pubDate;// Item������ʱ��
	private String author;// Item����
	private String category;// Item������Ƶ������

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
