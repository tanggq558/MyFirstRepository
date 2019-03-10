<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
  <head>
    <title>查看文章</title>
  </head>
  <body>
  <script type="text/javascript" src="<s:url value='myjs/ArticleJs.js'/>"></script> 
  <script type="text/javascript" src="<s:url value='myjs/ReplyPart_public.js'/>"></script>	
  	<div class="content">
		<div id="main">
   			<div id="detial">
   				<s:set name="log" value="#session.LOGIN"/>
   				<div id="dtop"><div class="do1">查看文章</div><div class="do2"><s:if test="#log!=null"><a href="go_new_article.htm"><img src="decorators/images/postnew.gif" alt="" align="absmiddle"/>写新文章</a></s:if>&nbsp;</div></div>
   				<br>
   				<s:set name="AD" value="#request.ARTICLE_DETAIL"/>
   				<div class="tit">${AD.title}</div>
   				<div class="time">作者:${AD.author}　时间:${AD.time}</div>
   				<div class="cont">
   						${AD.content}
				</div>
				<br>
   				<p class="date">类别：<a href="showArticleList.htm?type=${AD.articleType.id}" title="查看该分类中所有文章">${AD.articleType.typeName }</a>  <s:if test="#log!=null">|  <a href="ArticleEdit.htm?id=${AD.id}">编辑</a>  | <a href="javascript:delArticle(${AD.id})">删除</a></s:if> | <a href="#comment">评论(${fn:length(AD.articleComments)})</a> | <a href="javascript:void(0)">浏览(${AD.scan})</a></p>
   				<a name="comment"></a>
   				<div class="span_title">网友评论：</div>
	   			<div id="dcomment">
	   				<s:iterator id="COM" value="#request.ARTICLE_COMMENT" status="com">
		   				<div class="info">
		   					<div class="num"><s:if test="#com.count<10">0${com.count}</s:if><s:else>${com.count}</s:else></div>
		   					<div class="user">${COM.user}</div>
		   					<div class="dc_time">${COM.time}</div>
		   					<s:if test="#log!=null"><div class="op"><a href="#reply" onclick="reply(1,'${COM.id}')">回复</a> | <a href="javascript:delComment(${COM.id})">删除</a></div></s:if>
		   				<br>
		   				</div>
	   					<div id="msg${COM.id}" class="com_cont">${COM.comment}</div>
	   				</s:iterator>
	   			</div>
	   			<br>
	   			<a name="reply"></a>
   				<div class="span_title">发表评论：</div>
	   			<div id="publish_comment">
	   				<form action="#">
	   				<input type="hidden" id="HideId" name="id" value="${AD.id}"/>
	   				<s:if test="#log!=null"><input type="hidden" id="HideUser" value="${log.username}"/></s:if>
	    			<div class="user">姓　名:  <s:if test="#log!=null"><input type="text" name="user" id="user" size="30" value="${log.username}" style="border:none;color:#075181;" readonly="readonly"/></s:if><s:else><input type="text" name="user" id="user" size="30" maxlength="15"/>　[  姓名长度不能超过15个字符  ]</s:else></div>
   					<div class="userNum">
   						内　容:  [  留言长度不能超过<span>500</span>个字符  ]<br>
   					</div>
   					<div class="word">
   						<FCK:editor id="reply"  basePath="fckeditor/" width="550" height="200" toolbarSet="Basic"
				            imageBrowserURL="/blueblog/fckeditor/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"  
				            linkBrowserURL="/blueblog/fckeditor/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"  
				            flashBrowserURL="/blueblog/fckeditor/editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"  
				            imageUploadURL="/blueblog/fckeditor/editor/filemanager/upload/simpleuploader?Type=Image"  
				            linkUploadURL="/blueblog/fckeditor/editor/filemanager/upload/simpleuploader?Type=File"  
				            flashUploadURL="/blueblog/fckeditor/editor/filemanager/upload/simpleuploader?Type=Flash"> </FCK:editor> 
   					</div>
   					<div class="varcodeinput">
   						验证码:  <input type="text" id="code" name="code" onclick="showCode();" size="4" maxlength="4"/>
   						　[  点击输入框获取验证码,不区分大小写  ]
   					</div>
   					<div class="varcode" style="display:none">
						<img src="RandImg" id="randimg" />
						<a href="javascript:refresh()" title="看不清左边的字符?点下换个!">看不清?换一个</a>
					</div>
   					<div class="error"></div>
   					<div class="sub"><span id="sub"><input type="submit" value="发表评论" onclick="return publishComment(1,-1);"></span></div>
   					</form>
   				</div>
   			</div>
   			<br>
   		</div>
   	</div>
  </body>
</html>
