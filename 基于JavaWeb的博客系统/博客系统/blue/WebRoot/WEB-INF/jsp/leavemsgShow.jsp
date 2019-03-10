<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>留言板</title>
  </head>
  
  <body>
  	<script type="text/javascript" src="<s:url value='myjs/ProfileJs.js'/>"></script>
  	<script type="text/javascript" src="<s:url value='myjs/ReplyPart_public.js'/>"></script>
  	<script type="text/javascript" src="<s:url value='fckeditor/fckeditor.js'/>"></script>
	 <div class="content">
		<div id="main">
		<s:set name="log" value="#session.LOGIN"/>
			<div id="msgshow">
				<a name="leavemsg"></a>
				<div class="top"><span class="title">留言板</span>　[   认识或不认识我的朋友,留下你的脚印吧...  ]</div>
				<div id="publish_comment">
	   				<form action="#" method="post">
		   				<input type="hidden" id="HideId" name="id" value="${AD.id}"/>
		   				<s:if test="#log!=null"><input type="hidden" id="HideUser" value="${log.username}"/></s:if>
		    			<div class="user">姓　名:  <s:if test="#log!=null"><input type="text" name="user" id="user" size="30" value="${log.username}" style="border:none;color:#075181;" readonly="readonly"/></s:if><s:else><input type="text" name="user" id="user" style="width:180px;" maxlength="15"/>　[  姓名长度不能超过15个字符  ]</s:else></div>
	   					
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
	   						验证码:  <input type="text" id="code" name="code" onfocus="showCode();" onclick="showCode();" size="4" maxlength="4"/>
	   						　[  点击输入框获取验证码,不区分大小写  ]
	   					</div>
	   					<div class="varcode" style="display:none">
							<img src="RandImg" id="randimg" />
							<a href="javascript:refresh()" title="看不清左边的字符?点下换个!">看不清?换一个</a>
						</div>
	   					<div class="error"></div>
	   					<div class="sub"><span id="sub"><input type="submit" value="给我留言" onclick="return leaveMsg(1,-1);"></span>　注:请珍惜我的成果,文明留言</div>
   					</form>
  				</div>
  				<a name="more"></a>
  				<div class="bottom"><span class="title">网友留言</span>　[   我将定期回复你的留言  ]</div>
  				<div class="net_msg">
					<s:set name="leave_msg" value="#request.ALL_LEAVEMSG"/>
					<s:if test="#leave_msg.size==0">
						暂无留言
					</s:if>
					<s:else>
						<s:iterator id="LM" value="leave_msg" status="st">
							<a name="${st.count}"></a>
							<div class="info">
			   					<div class="num"><s:if test="#LM.id<10">0${LM.id}</s:if><s:else>${LM.id}</s:else></div>
			   					<div class="user">${LM.comName}</div>
			   					<div class="dc_time">${LM.comTime}</div>
			   					<div class="op"><s:if test="#log!=null"><a href="#leavemsg" onclick="reply(2,${LM.id})">回复</a> | <a href="javascript:delLeavemsg(${LM.id})">删除</a></s:if></div>
				   				<br>
			   				</div>
		   					<div id="msg${LM.id}" class="com_cont">${LM.comContent}</div>
						</s:iterator>
					</s:else>
					<div class="page">
						<s:set name="page" value="#request.PAGE_LINK"/>
						${page}
				   </div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
