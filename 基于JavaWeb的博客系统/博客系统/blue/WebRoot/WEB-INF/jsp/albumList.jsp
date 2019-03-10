<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>相册列表</title>
  </head>
  
  <body>
	<!--[if IE]>
		<style type="text/css"> #albumcenter{margin:0;} </style>
	<![endif]-->
  	<div class="content">
		<div id="main">
			<div id="albumshow">
			<s:set name="log" value="#session.LOGIN"/>
				<div id="albumtop">
					<div class="left"><a href="albumList.htm"><b>相册列表</b></a></div>
					<div class="right">
						<s:if test="#log!=null">
						<a href="imgUpload.htm"><img src="myimages/ico_uploadpic.gif" align="absmiddle">上传新照片 </a>
						<a href="javascript:opAlbum(-1,'','',false);"><img src="myimages/ico_creatset.gif" align="absmiddle">创建新相册 </a>
						</s:if>
					</div>
					<br>
				</div>
				<div id="albumcenter">
					<ul>
						<s:iterator id="EIT" value="#request.OLDEST_IMG_EACH_TYPE">
							<s:if test="#EIT.typeName!=null">
								<s:if test="#EIT.id!=1||#log!=null">
									<li class="li${EIT.id}"><a href="imgListShow.htm?type=${EIT.id}" class="ls"><img src="myimages/album_no_pic.gif"/></a><a href="#" id="name${EIT.id}" class="aname">${EIT.typeName}(0)</a><s:if test="#log!=null"><s:if test="#EIT.id!=0"><br><a href="javascript:opAlbum(${EIT.id},'${EIT.typeName}','${EIT.typeDesc}',true);" class="aop">√编辑</a><a href="javascript:delAlbum(${EIT.id})" class="aop">×删除</a></s:if><s:else><br>&nbsp;</s:else></s:if></li>
								</s:if>
							</s:if>
							<s:else>
								<s:if test="#EIT.imgType.id!=1||#log!=null">
									<li class="li${EIT.imgType.id}"><a href="imgListShow.htm?type=${EIT.imgType.id}" class="${EIT.imgtype?'ls':'pt'}"><img src="${EIT.imgurl}/s${EIT.imgname}"/></a><a href="#" id="name${EIT.imgType.id}" class="aname">${EIT.imgType.typeName}(${fn:length(EIT.imgType.imgs)})</a><s:if test="#log!=null"><s:if test="#EIT.imgType.id!=0"><br><a href="javascript:opAlbum(${EIT.imgType.id},'${EIT.imgType.typeName}','${EIT.imgType.typeDesc}',true);" class="aop">√编辑</a><a href="javascript:delAlbum(${EIT.imgType.id})" class="aop">×删除</a></s:if><s:else><br>&nbsp;</s:else></s:if></li>
								</s:if>
							</s:else>
						</s:iterator>
					</ul>
				</div>
			</div>
			<br>
		</div>
		
	</div>
	<script type="text/javascript" src="<s:url value='myjs/AlbumJs.js'/>"></script>
  </body>
</html>
