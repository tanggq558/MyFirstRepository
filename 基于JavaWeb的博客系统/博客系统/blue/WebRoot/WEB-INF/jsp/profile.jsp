<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>个人档案</title>
  </head>
  
  <body>
  	<script type="text/javascript" src="<s:url value='myjs/ProfileJs.js'/>"></script>
  	<script type="text/javascript" src="<s:url value='myjs/ReplyPart_public.js'/>"></script>
  	<script type="text/javascript" src="<s:url value='myjs/FlashPicShow.js'/>"></script>
    <div class="content">
		<div id="main">
		<s:set name="log" value="#session.LOGIN"/>
			<div id="profile">
				<div class="pro_left">
					<div class="one">
						<div class="top">
							<div class="top_left">我的资料</div>
							<div class="top_right"><s:if test="#log!=null"><a href="javascript:showProfile();"><img src="myimages/edit.gif" alt="编辑" align="absmiddle"/> 编辑</a></s:if>&nbsp;</div>
							<br>
						</div>
						<div class="referance">
							<s:set name="UI" value="#request.USER_INFO"/>
							<div class="re">姓　　名： <span>${UI.userInfo.realName}</span></div> 
							<div class="re">生　　日： <span>${UI.userInfo.birthday}</span></div> 
							<div class="re">家　　乡：  <span>${UI.userInfo.birthPlace}</span></div>
							<div class="re">现　　居： <span>${UI.userInfo.livePlace}</span></div>
							<div class="re">邮　　箱： <span>${UI.userInfo.email}</span></div>
							<div class="re">注册时间：  <span>${UI.userInfo.registTime}</span></div>
							<div class="re">最后登录：  <span>${UI.userInfo.lastLoginTime}</span></div>
							<div class="re">个人简介：  <span>${UI.userInfo.introduce}</span></div> 
						</div>
					</div>
					
					<div class="one">
						<div class="top">
							<div class="top_left">留言板</div><br>
						</div>
						<div class="leave_msg">
						<s:set name="leave_msg" value="#request.LATEST_LEAVEMSG"/>
						<s:if test="#leave_msg.size==0">
							暂无留言
						</s:if>
						<s:else>
							<s:iterator id="LM" value="leave_msg" status="st">
								<div class="info">
				   					<div class="num"><s:if test="#LM.id<10">0${LM.id}</s:if><s:else>${LM.id}</s:else></div>
				   					<div class="user">${LM.comName}</div>
				   					<div class="dc_time">${LM.comTime}</div>
				   					<div class="op"><a href="leavemsgShow.htm#${st.count}">查看</a><s:if test="#log!=null"> | <a href="#reply" onclick="reply(2,'${LM.id}')">回复</a> | <a href="javascript:delLeavemsg(${LM.id})">删除</a></s:if></div>
					   				<br>
				   				</div>
			   					<div id="msg${LM.id}" class="com_cont">${LM.comContent}</div>
							</s:iterator>
						</s:else>
						</div>
						<s:if test="#leave_msg.size>=3"><div class="more"><a href="leavemsgShow.htm#more">更多留言&gt;&gt;</a></div></s:if>
					</div>
					<a name="reply"></a>
					<div id="publish_comment">
		   				<form action="#">
			   				<input type="hidden" id="HideId" name="id" value="${AD.id}"/>
			   				<s:if test="#log!=null"><input type="hidden" id="HideUser" value="${log.username}"/></s:if>
			    			<div class="user">姓　名:  <s:if test="#log!=null"><input type="text" name="user" id="user" size="30" value="${log.username}" style="border:none;color:#075181;" readonly="readonly"/></s:if><s:else><input type="text" name="user" id="user" style="width:180px;" maxlength="15"/>[  姓名长度不能超过15个字符  ]</s:else></div>
		   					<div class="userNum">
	   						内　容:  [  留言长度不能超过<span>500</span>个字符  ]<br>
		   					</div>
		   					<div class="word">
		   						<FCK:editor id="reply"  basePath="fckeditor/" width="350" height="130" toolbarSet="Basic"
						            imageBrowserURL="/blueblog/fckeditor/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"  
						            linkBrowserURL="/blueblog/fckeditor/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"  
						            flashBrowserURL="/blueblog/fckeditor/editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"  
						            imageUploadURL="/blueblog/fckeditor/editor/filemanager/upload/simpleuploader?Type=Image"  
						            linkUploadURL="/blueblog/fckeditor/editor/filemanager/upload/simpleuploader?Type=File"  
						            flashUploadURL="/blueblog/fckeditor/editor/filemanager/upload/simpleuploader?Type=Flash"> </FCK:editor> 
		   					</div>
		   					<div class="varcodeinput">
		   						验证码:  <input type="text" id="code" name="code" onfocus="showCode();" onclick="showCode();" size="4" maxlength="4"/>
		   						　[  点击输入框获取验证码,不区分大小写  ]
		   					</div>
		   					<div class="varcode" style="display:none">
								<img src="RandImg" id="randimg" />
								<a href="javascript:refresh()" title="看不清左边的字符?点下换个!">看不清?换一个</a>
							</div>
		   					<div class="error"></div>
		   					<div class="sub"><span id="sub"><input type="submit" value="给我留言" onclick="return leaveMsg(1,-1);"></span></div>
	   					</form>
   					</div>
				</div>
				<div class="pro_right">
					<div class="two">
						<div class="top">
							<div class="top_left">关于我</div>
							<div class="top_right"><s:if test="#log!=null"><a href="imgUpload.htm?id=1"><img src="myimages/ico_uploadpic.gif" align="absmiddle">上传新照片 </a>&nbsp;<a href="imgListShow.htm?type=1"><img src="myimages/edit.gif" alt="" align="absmiddle"/> 编辑</a></s:if>&nbsp;</div>
							<br>
						</div>
						<div class="aboutme">
							<s:set name="status" value="#request.LATEST_IMG_STATUS"/>
							<s:if test="#status!=null">
								相片数目不够,目前只有${status}张,还需要上传${2-status}张
							</s:if>
							<s:else>
								<s:set name="urls" value="#request.LATEST_IMG_URLS"/>
								<s:set name="txts" value="#request.LATEST_IMG_TXTS"/>
								<s:set name="links" value="#request.LATEST_IMG_LINKS"/>
								<script language="javascript">
				                	w=270;h=178;txt_h=20;align='center';
				                	url='${urls}';
				                	txt='${txts}';
				                	link='${links}';
				                	time=3;
				                	picshow(w,h,txt_h,align,url,txt,link,time);
				                	/*参数依次表示：图象[宽、高],文字[高、对齐方式],图片[位置],文字[内容],停留时间*/
			                    </script>
							</s:else>
						</div>
					</div>
					<div class="two">
						<div class="top">
							<div class="top_left">最近发表文章</div><br>
						</div>
						<div class="latesta">
							<s:set name="latest_article" value="#request.LATEST_ARTICLE"/>
							<s:if test="#latest_article.size==0">
								暂无文章
							</s:if>
							<s:else>
								<s:iterator id="LA" value="latest_article">
									<div class="title"><span>☆</span>&nbsp;<a href="ArticleDetail.htm?id=${LA.id}">${LA.title}</a></div> 
									<div class="cont"><a href="ArticleDetail.htm?id=${LA.id}">${fn:substring(LA.content,0,40)}...</a></div>  
								</s:iterator>
							</s:else>
						</div>
						<br>
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
  </body>
</html>
