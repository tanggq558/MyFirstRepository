<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>相片展示</title>
  </head>
  
  <body>
  	  <script type="text/javascript" src="<s:url value='myjs/AlbumJs.js'/>"></script> 
	  <div class="content">
		<div id="main">
			<s:set name="IMG" value="#request.IMG"/>
			<s:set name="log" value="#session.LOGIN"/>
			<div id="imgshow">
				<div class="top">
					<div class="topleft"><a href="albumList.htm">相册列表</a>:&nbsp;<a href="imgListShow.htm?type=${IMG.imgType.id}" class="up">${IMG.imgType.typeName}</a></div>
					<div class="topright"><s:if test="#log!=null"><a href="imgUpload.htm"><img src="myimages/ico_uploadpic.gif" align="absmiddle">上传新照片 </a></s:if></div>
				</div>
				<br>
				
				<div class="show">
					<div class="showleft">
						<a id="link" href="${IMG.imgurl}/${IMG.imgname}"><img id="si" alt="${IMG.imgdesc}--${IMG.imginfo}" src="${IMG.imgurl}/${IMG.imgname}"></a>
					</div>
					<div class="showright">
						<div class="desc">描述:<br><span class="des">${IMG.imgdesc}</span></div>
						<div class="info">大小:<br><span class="inf">${IMG.imginfo}</span></div>
						<div class="time">时间:<br><span class="tim">${IMG.time}</span></div>
					</div>
					<br>
				</div>
				<div class="op">
					<a href="#" class="pre"><img src="myimages/pre.gif" align="absmiddle"/></a>
					&nbsp;&nbsp;(<span class="current">1</span>/${fn:length(IMG.imgType.imgs)})&nbsp;&nbsp;
					<a href="#" class="next"><img src="myimages/next.gif" align="absmiddle" /></a>
				</div>
				<script type="text/javascript">
					showBefore(${IMG.imgType.id},${IMG.id},"${IMG.imginfo}");
				</script>
			</div>
		</div>
	  </div>   
  </body>
</html>
