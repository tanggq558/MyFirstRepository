<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>登陆</title>
		<style>
			body,input {
				margin: 0px 0;
				padding: 0;
				font: 12px Arial, Sans-Serif;
				line-height: 1.4em;
			}
			form input{height:25px;}
			#log{margin:15px 55px;}
			form{padding:0;margin:0;}
			
			#log form .user,#log form .pass{width:200px;}
			#log form .code{width:60px;}
			
			.username,.password,.varcode,.codeimg,.sub{padding:8px 0;}
			.error{padding:4px 0;}
			.codeimg,.sub,.error{padding-left:55px;}
			.sub input{width:60px;}
			
			.error{color:red;}
			
			a{text-decoration:underline;}
			a:link,a:visited{color:#075181;}
			a:hover{color:red;}
		</style>

</head>

	<body>
		<script type="text/javascript" src="<s:url value='myjs/UserJs.js'/>"></script> 
		<script type="text/javascript" src="<s:url value='decorators/js/jquery/jquery-latest.js'/>"></script>
		<div id="log">
			<form action="#">
				<div class="username">
					用户名 :　<input type="text" id="user" class="user" maxlength="15">
				</div>
				<div class="password">
					密　码 :　<input type="password" id="pass" class="pass" maxlength="15">
				</div>
				<div class="varcode">
					验证码 :　<input type="text" id="code" class="code" maxlength="4">
					<span style="color:999;">[ 验证码不区分大小写  ]</span>
				</div>
				<div class="codeimg">
					<img id="randimg" src="RandImg" align="absbottom"/>&nbsp;<a href="javascript:refresh();">看不清?换一个</a>
				</div>
				<div class="error">
				</div>
				<div class="sub">
					<input type="submit" value="登陆" onclick="return check();"/>　　 <input type="reset" value="清空"/> 
				</div>
			</form>
		</div>
	</body>
</html>
