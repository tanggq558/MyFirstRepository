<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>写新文章</title>
  </head>
  
  <body>
  	<script type="text/javascript" src="<s:url value='myjs/ArticleJs.js'/>"></script> 
  <div class="content">
	  	<div id="main">
	  		<s:set name="EDIT" value="#request.ARTICLE_EDIT"/>
	  		<div class="maininput">
	  			<label>创建新文章</label>
				<form action="#" method="post" id="articleForm">
		  			<s:fielderror/>
					<s:actionerror/>
					<s:token/>
					<div class="tit">标题:
						<s:if test="#EDIT!=null">
							<input type="text" id="title" name="title" size="50" maxlength="20" value="${EDIT.title}"/>
							<input type="hidden" name="aid" value="${EDIT.id}"/>
						</s:if>
						<s:else><input type="text" name="title" id="title" name="title" size="50" maxlength="20" value=""/></s:else>  [文章标题最多不能超过20字符]
						<input type="hidden" name="author" value="${sessionScope.LOGIN.username}"/>
					</div>
				    <div class="con">
					    <FCK:editor id="content" basePath="fckeditor/" width="100%" height="400" toolbarSet="User" 
					            imageBrowserURL="/blueblog/fckeditor/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"  
					            linkBrowserURL="/blueblog/fckeditor/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"  
					            flashBrowserURL="/blueblog/fckeditor/editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"  
					            imageUploadURL="/blueblog/fckeditor/editor/filemanager/upload/simpleuploader?Type=Image"  
					            linkUploadURL="/blueblog/fckeditor/editor/filemanager/upload/simpleuploader?Type=File"  
					            flashUploadURL="/blueblog/fckeditor/editor/filemanager/upload/simpleuploader?Type=Flash">  
					            <s:if test="#EDIT!=null">${EDIT.content}</s:if>
						</FCK:editor> 
				    </div>
				    <div class="cat">分类:  <s:select name="articleType" emptyOption="true" list="#session.ALL_ARTICLE_TYPE" id="a_t" listKey="id" listValue="typeName"/> 
				    <a href="javascript:addType()">增加新分类</a>  <span id="error2"></span></div>
				    <div class="error"></div>
				    <div class="sub"><input type="submit" value="发表文章" onclick="return check(1);"/></div>
				</form>
				<s:if test="#EDIT!=null">
					<script type="text/javascript">
						initEdit(${EDIT.articleType.id});
					</script>
				</s:if>
				<s:else>
					<script type="text/javascript">
						var hid=${param.hid};
						var i=0;
						$("#a_t option").each(function(){
							if($(this).val()==hid){
								$("#a_t").get(0).selectedIndex=i;
							}
							i=i+1;
						});
					</script>
				</s:else>
			</div>
		</div>
	</div>
  </body>
</html>
