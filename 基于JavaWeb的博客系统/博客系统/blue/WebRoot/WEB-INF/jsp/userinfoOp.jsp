<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>个人资料修改</title>
    <style type="text/css">
    	#userinfo{margin:10px 30px;padding:0;font-size:12px;color:#333;}
    	#userinfo form{margin:0;padding:0;}
    	#userinfo input,#userinfo textarea{font-size:12px;color:#666;}
    	#userinfo textarea{overflow:auto;}
    	#userinfo .info,#userinfo .userNum{padding:7px 0;}
    	#userinfo .inf{width:212px;border:none;border-bottom:1px solid #999;}
    	#userinfo .btn{width:60px;color:#000;}
    	#userinfo .userNum span{color:#075181;padding:0 10px;}
    	#userinfo .error,#userinfo .sub{padding-top:10px;}
    	#userinfo .error{color:red;}
    </style>
  </head>
  <body>
  	<script type="text/javascript" src="<s:url value='myjs/ProfileJs.js'/>"></script>
  	<script type="text/javascript" src="<s:url value='myjs/ReplyPart_public.js'/>"></script>
  	<script type="text/javascript" src="<s:url value='decorators/js/jquery/jquery-latest.js'/>"></script>
  	<div id="userinfo">
  		<form action="#" method="post">
  			<s:set name="INFO" value="#request.USER_INFO_OP"/>
  			<input type="hidden" value="${INFO.id}" id="HideId">
  			<div class="info">账　　号： ${INFO.username}</div>
  			<div class="info">姓　　名： <input type="text" id="realname" maxlength="10" value="${INFO.userInfo.realName}" class="inf"/></div>
  			<div class="info">密　　码： <input type="password" id="password" maxlength="10" value="iloveyou" class="inf"/></div>
  			<div class="info">确认密码： <input type="password" id="conf_password" maxlength="10" value="iloveyou" class="inf"/></div>
  			<div class="info">家　　乡：  <input type="text" id="birthplace" maxlength="10" value="${INFO.userInfo.birthPlace}" class="inf"/></div>
  			<div class="info">现　　居：  <input type="text" id="liveplace" maxlength="10" value="${INFO.userInfo.livePlace}" class="inf"/></div>
  			<div class="info">邮　　箱：  <input type="text" id="email" maxlength="30" value="${INFO.userInfo.email}" class="inf"/></div>
  			<div class="userNum">
		   		个人简介：&nbsp;已用<span>0</span> 未用<span>100</span>总共<span>100</span><br>
		   	</div>
  			<div class="txt">
  					<textarea id="reply" style="width:280px;height:70px;" onkeydown="checkword(100)" onkeyup="checkword(100)" onchange="checkword(100)">${INFO.userInfo.introduce}</textarea>
  			</div>
  			<div class="error"></div>
  			<div class="sub"><input type="submit" value="更改" class="btn" onclick="return editProfile();"/></div>
  		</form>
  	</div>
  	<script type="text/javascript">$(function(){checkword(100);initProfile();});</script>
  </body>
</html>