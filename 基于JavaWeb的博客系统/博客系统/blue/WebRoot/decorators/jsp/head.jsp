<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<title>BlueBlog</title>
	</head>

	<body>
		<script type="text/javascript" src="<s:url value='myjs/UserJs.js'/>"></script>
		<script type="text/javascript">$(function(){setStatus();});</script>
		<div class="content">
			<div id="top">
				<div class="padding">
					<s:set name="log" value="#session.LOGIN"/>
					<s:if test="#log!=null">欢迎您 <a href="#"><span>${log.username}</span></a> |</s:if> <s:else><a href="#" onclick="javascript:login()">登陆</a> |</s:else> <s:if test="#log!=null"><a href="logout.htm">退出</a>| </s:if><a href="profile.htm">关于我们</a> | <a href="leavemsgShow.htm#leavemsg">联系我们</a>
				</div>
			</div>
			<div id="header">
				<div class="f_search">
						<form method="post" action="search.htm">
							<p><input type="text" name="key" value="搜索..." class="search" onblur="if(this.value=='') this.value='搜索...';" onfocus="if(this.value=='搜索...') this.value='';" /> <input type="submit" value="Go" class="submit" /></p>
						</form>
				</div>
				<div class="title">
					<h1>十月伤感</h1>
					<h2>资源无国界,一切皆共享!</h2>
				</div>
			</div>
		
			<div id="subheader">
				<div id="menu">
				  	<ul>
						<li><a href="goindex.htm">主页</a></li>
						<li><a href="showArticleList.htm">博客</a></li>
						<li><a href="albumList.htm">相册</a></li>
						<li><a href="profile.htm">个人档案</a></li>
						<!-- li><a href="#">资料下载</a></li-->
						<li><a href="leavemsgShow.htm">留言板</a></li>
	      				<s:if test="#log!=null"><li><a href="#">管理中心</a></li></s:if>
	      			</ul>
				</div>
			</div>
		</div>
	</body>
</html>
