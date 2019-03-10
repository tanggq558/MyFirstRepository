package com.rss;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.feed.synd.SyndImageImpl;
import com.sun.syndication.io.SyndFeedOutput;


public class RssBuilder {
	private SyndFeed feed;
	
	@SuppressWarnings("unchecked")
	private List entries;
	private SyndEntry entry;

	
	@SuppressWarnings("unchecked")
	public RssBuilder() {
		this.feed = new SyndFeedImpl();
		this.feed.setFeedType("rss_2.0");
		this.entries = new ArrayList();
	}

	public void createChannelImage(String title, String link, String url,
			String description) throws Exception {
		SyndImage image = this.feed.getImage();
		if (image != null) {
			image.setTitle(title);
			image.setLink(link);
			image.setUrl(url);
			image.setDescription(description);
			this.feed.setImage(image);
		} else {
			image = new SyndImageImpl();
			image.setTitle(title);
			image.setLink(link);
			image.setUrl(url);
			image.setDescription(description);
			this.feed.setImage(image);
		}
	}
	
	public void createChannelImage(ChannelImage image) throws Exception {
		SyndImage simage = this.feed.getImage();
		if (simage != null) {
			simage.setTitle(image.getTitle());
			simage.setLink(image.getLink());
			simage.setUrl(image.getUrl());
			simage.setDescription(image.getDescription());
			this.feed.setImage(simage);
		}else{
			simage = new SyndImageImpl();
			simage.setTitle(image.getTitle());
			simage.setLink(image.getLink());
			simage.setUrl(image.getUrl());
			simage.setDescription(image.getDescription());
			this.feed.setImage(simage);
		}
	}

	public void createChannel(String title, String link, String description,
			String language, Date pubDate, String copyright) throws Exception {
		this.feed.setTitle(title);
		this.feed.setLink(link);
		this.feed.setDescription(description);
		this.feed.setLanguage(language);
		this.feed.setPublishedDate(pubDate);
		this.feed.setCopyright(copyright);
	}
	
	@SuppressWarnings("unchecked")
	public void createItems(ChannelItem item) throws RuntimeException {
		entry = new SyndEntryImpl();
		// 设置新闻标题
		entry.setTitle(item.getTitle());
		// 设置新闻的连接地址
		entry.setLink(item.getLink());
		// 设置新闻简介
		SyndContent content = new SyndContentImpl();
		content.setType("text/plain");
		content.setValue(item.getDescription());
		entry.setDescription(content);
		// 设置发布时间
		entry.setPublishedDate(item.getPubDate());
		// 设置频道所属的范围
		SyndCategory cate = new SyndCategoryImpl();
		cate.setName(item.getCategory());
	
		List cateList = new ArrayList();
		cateList.add(cate);
		entry.setCategories(cateList);
		// 设置作者
		entry.setAuthor(item.getAuthor());
		// 将新闻项添加至数组中
		entries.add(entry);
	}

	@SuppressWarnings("unchecked")
	public void createItems(String title, String link, String description,
			Date pubDate, String category, String author) throws Exception {
		this.entry = new SyndEntryImpl();

		this.entry.setTitle(title);

		this.entry.setLink(link);

		SyndContent content = new SyndContentImpl();
		content.setType("text/plain");
		content.setValue(description);
		this.entry.setDescription(content);

		this.entry.setPublishedDate(pubDate);

		SyndCategory cate = new SyndCategoryImpl();
		cate.setName(category);
		
		List cateList = new ArrayList();
		cateList.add(cate);
		this.entry.setCategories(cateList);

		this.entry.setAuthor(author);

		this.entries.add(this.entry);
	}

	@SuppressWarnings("unchecked")
	public void createItems(String title, String link, String description,
			Date pubDate, String category, String author, String enclosure)
			throws Exception {
		this.entry = new SyndEntryImpl();

		this.entry.setTitle(title);

		this.entry.setLink(link);

		SyndContent content = new SyndContentImpl();
		content.setType("text/plain");
		content.setValue(description);
		this.entry.setDescription(content);

		this.entry.setPublishedDate(pubDate);

		SyndCategory cate = new SyndCategoryImpl();
		cate.setName(category);
		
		List cateList = new ArrayList();
		cateList.add(cate);
		this.entry.setCategories(cateList);

		this.entry.setAuthor(author);

		SyndEnclosure en = new SyndEnclosureImpl();
		en.setUrl(enclosure);
	
		List enList = new ArrayList();
		enList.add(en);
		this.entry.setEnclosures(enList);

		this.entries.add(this.entry);
	}

	public void buildChannel(String filePath) throws Exception {
		this.feed.setEntries(this.entries);

		SyndFeedOutput output = new SyndFeedOutput();

		Writer writer = new OutputStreamWriter(new FileOutputStream(filePath),
				"UTF-8");
		output.output(this.feed, writer);
	}
}
