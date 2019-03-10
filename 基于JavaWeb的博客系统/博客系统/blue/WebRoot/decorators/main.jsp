<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<%@ taglib  prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib  prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<html>
	<head>
		<title><decorator:title default="BlueBlog"/>-吴宾个人博客-十月伤感-郑大工学院计算机09级在校生</title>
		<meta http-equiv="Content-Language" content="en-us" />
		<meta http-equiv="imagetoolbar" content="no" />
		<meta name="MSSmartTagsPreventParsing" content="true" />
		<meta name="keywords" content="吴宾,wubin,个人博客,blueblog,十月伤感,fly,qq:1091347626,1091347626@qq.com" />
		<meta name="author" content="by dukai" />
		<link rel="alternate" type="application/rss+xml" title="十月伤感" href="rss.xml" />
		<link href="decorators/css/style.css" rel="stylesheet" type="text/css"/>
		<link href="mycss/mycss.css" rel="stylesheet" type="text/css"/>
		<link type="text/css" rel="stylesheet" href="mycss/editor.css" />
		<script type="text/javascript" src="decorators/js/jquery/jquery-latest.js"></script>
		<script type="text/javascript" src="decorators/js/popup/popup.js"></script>
		<script type="text/javascript" src="<s:url value='myjs/UserJs.js'/>"></script> 
	</head>
	<body>
		<jsp:include page="jsp/head.jsp"/>
		<decorator:body/>
		<jsp:include page="jsp/foot.jsp"/>
	</body>
</html>