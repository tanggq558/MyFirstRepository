<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文章列表</title>
  </head>
  
  <body>
  	<script type="text/javascript" src="<s:url value='myjs/ArticleJs.js'/>"></script> 
  		<div class="content">
			<div id="main">
				<s:set name="log" value="#session.LOGIN"/>
				<div class="left_side">
					<div id="atop"><div class="to1">文章列表</div><div class="to2"><s:if test="#log!=null"><a href="go_new_article.htm"><img src="decorators/images/postnew.gif" alt="" align="absmiddle"/>写新文章</a></s:if>&nbsp;</div></div>
						<br>
						<s:set name="article_list" value="#request.ARTICLE_LIST"/>
						<s:if test="#article_list.size==0">
							<div id="no_article">
								暂无文章
									<s:if test="#log!=null">,马上<s:if test="#attr.type>0"><a href="go_new_article.htm?hid=${param.type}">写新文章</a></s:if><s:else><a href="go_new_article.htm?hid=0">写新文章</a></s:else>
									</s:if>
							</div>
						</s:if>
						<s:else>
							<s:iterator id="ALIST" value="article_list">
								<div id="alist">
									<div class="atit"><a href="ArticleDetail.htm?id=${ALIST.id}">${ALIST.title}</a></div>
									<div class="atime">${ALIST.time}</div>
									<div class="acon">
										${fn:substring(ALIST.content,0,400)}
									</div>
									<div class="all"><a href="ArticleDetail.htm?id=${ALIST.id}">阅读全文&gt;&gt;</a></div>
									<div class="aopr">
										<div class="op">类别：<a href="showArticleList.htm?type=${ALIST.articleType.id}" title="查看该分类中所有文章">${ALIST.articleType.typeName }</a> <s:if test="#log!=null">| <a href="ArticleEdit.htm?id=${ALIST.id}">编辑</a> | <a href="javascript:delArticle(${ALIST.id})">删除</a></s:if> |  
											<a href="ArticleDetail.htm?id=${ALIST.id}#comment">评论(${fn:length(ALIST.articleComments)})</a> | <a href="ArticleDetail.htm?id=${ALIST.id}">浏览(${ALIST.scan})</a> 
										</div>
								   </div>
								 </div>
							   </s:iterator>
							  <div id="apage">
								    <s:set name="page" value="#request.PAGE_LINK"/>
								  	${page}
							  </div>
						</s:else>
				</div>
				
				<div class="right_side">
					<div id="yboder">
						<div id="ytype">
							<div class="aty">
								<div class="ty1">文章分类</div>
								<div class="ty2"><s:if test="#log!=null"><a href="javascript:editTypeName();"><img src="decorators/images/postnew.gif" alt="" align="absmiddle"/>编辑</a></s:if></div>
							</div>
							<br>
							<div id="ylist">
								<ul>
									<s:iterator id="ATP" value="#session.ALL_ARTICLE_TYPE">
										<li><a href="showArticleList.htm?type=${ATP.id}" title="查看该分类中所有文章">${ATP.typeName}</a>(${fn:length(ATP.articles)})</li>
									</s:iterator>
								</ul>
							</div>
						</div>
					</div>
					<div id="yboder">
						<div id="acomment">
							<span><b>最新文章评论</b></span>
							<s:set name="latest_comment" value="#request.LATEST_COMMENT"/>
							<s:if test="#latest_article.size==0">
								暂无文章评论
							</s:if>
							<s:else>
								<s:iterator id="LC" value="latest_comment" status="st">
									<div class="author"><span><s:if test="#st.even">☆</s:if><s:else>★</s:else></span>&nbsp;${LC.user}</div> 
									<div class="cont"><a href="ArticleDetail.htm?id=${LC.article.id}#comment">${LC.comment}</a></div>  
								</s:iterator>
							</s:else>
						</div>
					</div>
				</div>
				<br>
			</div>
		</div>
  </body>
</html>
