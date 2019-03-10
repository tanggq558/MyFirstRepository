<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>首页</title>
	</head>

	<body>
		<script type="text/javascript" src="<s:url value='myjs/ArticleJs.js'/>"></script> 
		<div class="content">
			<div id="main">
				<div class="left_side">
					<s:set name="log" value="#session.LOGIN"/>
					<s:iterator id="art" value="#request.LATEST_ARTICLE_EACH_TYPE" status="st">
						<h2>
							<a href="showArticleList.htm?type=${art.articleType.id}">${art.articleType.typeName}</a>
							<span class="newArticle">
								<s:if test="#log!=null"><a href="go_new_article.htm?hid=${art.articleType.id}"><img src="decorators/images/postnew.gif" alt="" align="top"/>写新文章</a></s:if>
								<a href="javascript:slide('blog${st.index}','slide${st.index}')" class="slide${st.index}">[&nbsp;收&nbsp;起&nbsp;]</a>
							</span>
						</h2>
						<h3>
							${art.articleType.typeDesc}
						</h3>
						<div class="blog${st.index}">
							<div class="title"><a href="ArticleDetail.htm?id=${art.id}">${art.title}</a></div>
							<div class="article">${fn:substring(art.content,0,400)}...</div>
							<p class="date">
								发表:${art.author}
								<s:if test="#log!=null">
									<img src="decorators/images/edit.gif" alt="" />
									<a href="ArticleEdit.htm?id=${art.id}">编辑</a>
									<img src="decorators/images/del.gif" alt="" />
									<a href="javascript:delArticle(${art.id})">删除</a>
								</s:if>
								<img src="decorators/images/more.gif" alt="" />
								<a href="ArticleDetail.htm?id=${art.id}">更多</a>
								<img src="decorators/images/scan.gif" alt="" />
								<a href="ArticleDetail.htm?id=${art.id}#comment">评论(${fn:length(art.articleComments)})</a>
								<img src="decorators/images/comment.gif" alt="" />
								<a href="ArticleDetail.htm?id=${art.id}">浏览(${art.scan})</a>
								<img src="decorators/images/timeicon.gif" alt="" />
								${fn:substring(art.time,0,10)}
							</p>
						</div>
						<br />
					</s:iterator>
				</div>
				<div class="right_side">

					<div class="nav">
						<h2>
							关于我们
						</h2>
						<div class="padding">
							<div class="img2">
								<img src="decorators/images/aboutus.gif" alt="" />
							</div>
							<s:set name="AM" value="#request.ABOUT_ME"/>
							<p>
							${AM.userInfo.realName}<p>
							<s:if test="#AM.userInfo.sex">男</s:if><s:else>女</s:else>, ${AM.userInfo.birthday}<p>
							家乡 ${AM.userInfo.birthPlace}<p>
							现居 ${AM.userInfo.livePlace}<p>
							${AM.userInfo.introduce}
							<div class="myms" style="text-align:right">
								<a href="leavemsgShow.htm#leavemsg"><img src="decorators/images/my_leave.gif" alt="认识我的朋友或同学要给我留言哦" align="absmiddle"/>&nbsp;给我留言</a>　
								<a href="leavemsgShow.htm#more"><img src="decorators/images/my_msg.gif" alt="查看给我的留言" align="absmiddle"/>查看留言</a>
							</div>
						</div>
						<h2>
							本站推荐
						</h2>
						<ul>
							<li>
								<a href="http://www.blueidea.com">蓝色理想</a>
							</li>
							<li>
								<a href="http://www.templateworld.com">Website Templates</a>
							</li>
							<li>
								<a href="http://www.52css.com">我爱css</a>
							</li>
							<li>
								<a href="http://www.javaeye.com">JavaEye</a>
							</li>
							<li>
								<a href="http://hi.baidu.com/wubin7019088">我的百度空间</a>
							</li>

						</ul>
						<br />
						<h2>
							友情链接
						</h2>
						<ul>
							<li>
								<a href="http://www.solucija.com">solucija.com</a>
							</li>
							<li>
								<a href="#">free-css-templates.com</a>
							</li>
							<li>
								<a href="http://snews.vietbee.net/">snews.vietbee.net</a>
							</li>
							<li>
								<a href="http://p-ahlqvist.com/">p-ahlqvist.com</a>
							</li>
							<li>
								<a href="http://www.brauck.nl/">www.brauck.nl</a>
							</li>
							<li>
								<a href="http://www.design4.ltd.pl/">www.design4.ltd.pl</a>
							</li>
						</ul>
						<div style="text-align:right;padding:5px 0 10px 0;">
							<a href="#"><img src="decorators/images/my_link.gif" alt="申请友情链接" align="absbottom"/>申请友情链接</a>
						</div>
						<div class="access">小站访问量&nbsp;<span>${access}</span></div>
					</div>
				</div>
				<br>
			</div>
		</div>
	</body>
</html>
