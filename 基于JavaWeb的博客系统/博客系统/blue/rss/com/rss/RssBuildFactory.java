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
import com.sun.syndication.io.SyndFeedOutput;

public class RssBuildFactory {
	private SyndFeed feed;
	@SuppressWarnings("unchecked")
	private List entries;
	private SyndEntry entry;

	@SuppressWarnings("unchecked")
	public RssBuildFactory() {
		feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		entries = new ArrayList();
	}

	/**
	 * ����һ��Ƶ��
	 * 
	 * @param title
	 *            ��Ƶ������
	 * @param link
	 *            ��Ƶ����Ӧ������
	 * @param description
	 *            ��Ƶ������
	 * @param language
	 *            ��Ƶ����������
	 * @param pubDate
	 *            ��Ƶ������ʱ��
	 * @param copyright
	 *            ����Ȩ����
	 * @throws Exception
	 */
	public void buildChannel(String title, String link, String description,
			String language, Date pubDate, String copyright)
			throws RuntimeException {
		feed.setTitle(title);
		feed.setLink(link);
		feed.setDescription(description);
		feed.setLanguage(language);
		feed.setPublishedDate(pubDate);
		feed.setCopyright(copyright);
	}

	/**
	 * ���Ƶ����������
	 * 
	 * @param item
	 *            [email={@link]{@link[/email] ChannelItem}
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void buildItems(ChannelItem item) throws RuntimeException {
		entry = new SyndEntryImpl();
		// �������ű���
		entry.setTitle(item.getTitle());
		// �������ŵ����ӵ�ַ
		entry.setLink(item.getLink());
		// �������ż��
		SyndContent content = new SyndContentImpl();
		content.setType("text/plain");
		content.setValue(item.getDescription());
		entry.setDescription(content);
		// ���÷���ʱ��
		entry.setPublishedDate(item.getPubDate());
		// ����Ƶ�������ķ�Χ
		SyndCategory cate = new SyndCategoryImpl();
		cate.setName(item.getCategory());
		
		List cateList = new ArrayList();
		cateList.add(cate);
		entry.setCategories(cateList);
		// ��������
		entry.setAuthor(item.getAuthor());
		// �������������������
		entries.add(entry);
	}

	/**
	 * ���Ƶ����������
	 * 
	 * @param item
	 *            [email={@link]{@link[/email] ChannelEItem}����̳���ChannelItem��
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void buildItems(ChannelEItem item) throws RuntimeException {
		entry = new SyndEntryImpl();
		// �������ű���
		entry.setTitle(item.getTitle());
		// �������ŵ����ӵ�ַ
		entry.setLink(item.getLink());
		// �������ż��
		SyndContent content = new SyndContentImpl();
		content.setValue(item.getDescription());
		entry.setDescription(content);
		// ���÷���ʱ��
		entry.setPublishedDate(item.getPubDate());
		// ����Ƶ�������ķ�Χ
		SyndCategory cate = new SyndCategoryImpl();
		cate.setName(item.getCategory());
		
		List cateList = new ArrayList();
		cateList.add(cate);
		entry.setCategories(cateList);
		// ��������
		entry.setAuthor(item.getAuthor());
		// ������ý�岥���ļ�
		SyndEnclosure en = new SyndEnclosureImpl();
		en.setUrl(item.getEnclosure());
		
		List enList = new ArrayList();
		enList.add(en);
		entry.setEnclosures(enList);
		// �������������������
		entries.add(entry);
	}

	/**
	 * ����XML�ļ�
	 * 
	 * @param filePath
	 *            ���ļ�����·��������
	 * @throws Exception
	 */
	public void buildChannel(String filePath) throws Exception {
		feed.setEntries(entries);
		SyndFeedOutput output = new SyndFeedOutput();
		Writer writer;
		writer = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
		output.output(feed, writer);
	}

}
