<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>照片上传</title>
  </head>
  
  <body>
  	<!--[if IE]>
		<style type="text/css"> 
			#album_left ul{padding:0;} 
			#img_right ul li{padding:15px 20px 0 10px;}
		</style>
	<![endif]-->
  	<div class="content">
		<div id="main">
			<div id="imgupload">
			<s:set name="log" value="#session.LOGIN"/>
				<input type="hidden" value="${param.id}" id="hideTypeid"/>
				 <div class="uptop"><b>相片上传</b>:一次最多可上传5张，每张大小在200kb以内。您可以上传JPG、GIF、BMP和PNG格式的照片</div>
				 <form id="uploadform" action="uploadImg.htm" method="post" enctype="multipart/form-data">
				    <s:fielderror/>
				    <s:actionerror/>
				    <s:token/>
					 <div id="uploadarea">
					 	
					 </div>
					 <div class="uphide">
					 	<a class="album" href="albumList.htm"><img src="myimages/album.gif"/>返回相册</a><a class="addmore" href="javascript:addmore(2);"><img src="myimages/ico_uploadpic.gif" align="absmiddle"/>上传更多相片</a>
					 </div>
					 <div class="cat">
					 	相册类别:
					 	<s:select name="typeid" id="typeid" emptyOption="true" list="#session.ALL_IMG_TYPE" listKey="id" listValue="typeName"/>
					 	<s:if test="#log!=null"><a href="javascript:opAlbum(-1,'','',false);"><img src="myimages/ico_creatset.gif" align="absmiddle">创建新相册 </a></s:if>
					 </div>
					 <div class="error">
					 </div>
					 <div class="sub">
					 	<input type="submit" value="上传" onclick="return checkUpload();"/>
					 </div>
				 </form>
			</div>
			<br>
		</div>
	</div>
	<script type="text/javascript" src="<s:url value='myjs/AlbumJs.js'/>"></script> 
	<script type="text/javascript">$(function(){appendFile(3);initTypeId();});</script>
  </body>
</html>
