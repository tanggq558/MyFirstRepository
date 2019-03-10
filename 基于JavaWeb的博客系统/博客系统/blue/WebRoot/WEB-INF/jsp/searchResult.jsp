<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
  <head>
    <title>搜索结果</title>
  </head>
  
  <body>
   	<div class="content">
		<div id="main">
			<div id="searchResult">
				<s:set name="aSearch" value="#request.SEARCHRESULT_ARTICLE"/>
				<div class="result">
					<div class="top">文章查询结果</div>
					<div class="center">
						<s:if test="#aSearch.size==0">没有与你搜索条件相关的文章</s:if>
						<s:else>
							<div class="tips">共搜索到相关文章<font color="red" size="3">${fn:length(aSearch)}</font>篇</div>
							<div class="list">
								<s:iterator id="AS" value="aSearch" status="st">
									<div class="title"><span>${st.count}</span>.&nbsp;<a href="#">${AS.title}</a></div>
									<div class="cont">${fn:substring(AS.content,0,100)}...</div>
									<div class="author">作者：${AS.author}</div>
									<div class="other">
										文章类别:<a href="showArticleList.htm?type=${AS.articleType.id}">${AS.articleType.typeName}</a>
										<img src="decorators/images/more.gif" alt="" />
										<a href="ArticleDetail.htm?id=${AS.id}">查看详细</a>
										<img src="decorators/images/scan.gif" alt="" />
										<a href="ArticleDetail.htm?id=${AS.id}#comment">评论(${fn:length(AS.articleComments)})</a>
										<img src="decorators/images/comment.gif" alt="" />
										<a href="ArticleDetail.htm?id=${AS.id}">浏览(${AS.scan})</a>
										<img src="decorators/images/timeicon.gif" alt="" />
										${AS.time}
									</div>
								</s:iterator>
							</div>
						</s:else>
					</div>
				</div>
				<s:set name="iSearch" value="#request.SEARCHRESULT_ALBUM"/>
				<div class="result">
					<div class="top">图片查询结果</div>
					<div class="center">
						<s:if test="#iSearch.size==0">没有与你搜索条件相关的图片</s:if>
						<s:else>
							<div class="tips">共搜索到相关相片<font color="red" size="3">${fn:length(iSearch)}</font>张</div>
							<div class="list">
								<s:iterator id="IS" value="iSearch" status="st">
									<div class="desc"><span>${st.count}</span>.&nbsp;<a href="imgShow.htm?id=${IS.id}">${IS.imgdesc}</a></div>
									<div class="url"><a href="imgShow.htm?id=${IS.id}"><img src="${IS.imgurl}/s${IS.imgname}"/></a></div>
									<div class="info">信息: ${IS.imginfo}</div>
									<div class="other">
										相册名称: <a href="imgListShow.htm?type=${IS.imgType.id}">${IS.imgType.typeName}</a>
										<img src="decorators/images/more.gif" alt="" />
										<a href="imgShow.htm?id=${IS.id}">查看详细</a>	
										<img src="decorators/images/timeicon.gif" alt="" />
										${IS.time}
									</div>
								</s:iterator>
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
