<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>照片列表</title>
  </head>
  
  <body>
  	<!--[if IE]>
		<style type="text/css"> 
			#album_left ul{padding:0;} 
			#img_right ul li{padding:15px 20px 0 10px;}
		</style>
	<![endif]-->
		<script type="text/javascript" src="<s:url value='myjs/AlbumJs.js'/>"></script>
		<script type="text/javascript">$(function(){setClickBg();});</script>
  	<div class="content">
		<div id="main">
			<div id="albumshow2">
			<s:set name="log" value="#session.LOGIN"/>
				<div id="album_left">
					<div class="album_left_top">
						<div class="left"><a href="javascript:;"><b>相册列表</b></a></div>
						<div class="right"><s:if test="#log!=null"><a href="javascript:opAlbum(-1,'','',false);"><img src="myimages/ico_creatset.gif" align="absmiddle">创建新相册 </a></s:if></div>
					</div>
					<input type="hidden" id="hideType" value="${param.type}"/>
					<ul>
						<s:iterator id="EIT" value="#request.IMG_LIST_SHOW">
							<s:if test="#EIT.typeName!=null">
								<s:if test="#EIT.id!=1||#log!=null">
									<li><a href="imgListShow.htm?type=${EIT.id}" id="a${EIT.id}" class="ls"><img src="myimages/album_no_pic.gif"/></a><a href="#" class="aname">${EIT.typeName}(0)</a></li>
								</s:if>
							</s:if>
							<s:else>
								<s:if test="#EIT.imgType.id!=1||#log!=null">
									<li><a href="imgListShow.htm?type=${EIT.imgType.id}" id="a${EIT.imgType.id}" class="${EIT.imgtype?'ls':'pt'}"><img src="${EIT.imgurl}/s${EIT.imgname}"/></a><a href="#" class="aname">${EIT.imgType.typeName}(${fn:length(EIT.imgType.imgs)})</a></li>
								</s:if>
							</s:else>
						</s:iterator>
							<li><a href="albumList.htm">&lt;&lt;所有相册&gt;&gt;</a></li>
					</ul>
				</div>
				<div id="img_right">
					<div class="img_right_top">
						<s:set name="AINFO" value="#request.ALBUM_INFO"/>
						<div class="left"><b>当前相册:</b><a href="javascript:;">${AINFO.typeName}</a></div>
						<div class="right"><s:if test="#log!=null"><a href="imgUpload.htm?id=${AINFO.id}"><img src="myimages/ico_uploadpic.gif" align="absmiddle">上传新照片 </a></s:if></div>
						<div class="desc">简介:${AINFO.typeDesc}</div>
					</div>
					<br>
					<ul>
						<s:set name="img_list" value="#request.IMG_LIST_SHOW2"/>
							<s:if test="#img_list.size==0">
								<span class="no_pic">暂无相片<s:if test="#log!=null">,马上<a href="imgUpload.htm?id=${AINFO.id}">上传新照片</a></s:if></span>
							</s:if>
							<s:else>
								<s:iterator id="EIT2" value="img_list">
									<s:if test="#EIT2.imgType.id!=1||#log!=null">
										<li class="li${EIT2.id}"><a href="imgShow.htm?id=${EIT2.id}" class="aimg" target="blank"><img src="${EIT2.imgurl}/s${EIT2.imgname}"/></a><a href="#" class="name" id="name${EIT2.id}">${EIT2.imgdesc}</a><br><span class="size">${EIT2.imginfo}<s:if test="#log!=null">&nbsp;&nbsp;<a href="javascript:opImg(${EIT2.imgType.id},${EIT2.id},'${EIT2.imgurl}','${EIT2.imgname}','${EIT2.imgdesc}','${EIT2.imginfo}');" class="aop">√编辑</a>&nbsp;&nbsp;<a href="javascript:delImg(${EIT2.id})" class="aop">×删除</a></s:if></span></li>
									</s:if>
									<s:else>
										<span class="no_pic">对不起,此相册相片登陆后才能查看</span>
									</s:else>
								</s:iterator>
							</s:else>
					</ul>
					<br>
					<div class="page">
						<s:set name="page" value="#request.PAGE_LINK"/>
						${page}
					</div>
				</div>
				<br>
			</div>
			<br>
		</div>
	</div>
  </body>
</html>
