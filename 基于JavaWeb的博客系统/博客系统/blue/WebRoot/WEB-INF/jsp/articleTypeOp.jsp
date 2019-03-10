<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>文章类别管理</title>
    <style>
    	img{border:none;}
    	input{font-size:12px;height:20px;}
    	#articleTypeOp{font-size:12px;margin:15px;width:400px;}
    	#articleTypeOp .h{margin:5px 0;display: block;color:#075181;font-weight:bold;}
    	#articleTypeOp .h b{font-weight:normal;color:#666;}
    	#articleTypeOp .tips{color:red;padding:5px 0 0 5px;display:none;}
    	#addNewType{padding-bottom:10px;}
    	#addNewType .error{color:red;}
		#addNewType .error,#addNewType .typename,#addNewType .typedesc{padding:6px 0;}
		#TypeOp{}
		#auto{margin:0;padding:0;width:400px;height:148px;overflow:auto;}
		#TypeOp .opp,#TypeOp .op,#TypeOp .op1{text-align:center;padding:8px 0 20px 5px;}
		#TypeOp .opp{background:#eaf7ff;}
		#TypeOp .op{background:#fff;}
		#TypeOp .op1{background:lightyellow;}
		#TypeOp .name{float:left;width:220px;text-align:left;}
		#TypeOp .edit{float:left;width:78px;}
		#TypeOp .del{float:left;width:78px;}
    </style>
  </head>
  
  <body>
  	 <div id="articleTypeOp">
  		<div id="addNewType">
  			<span class="h">添加新的文章类别　<b>[不能添加已经存在的文章分类]</b></span>
  				<div class="error"></div>
  				<div class="typename">类别名称 <input type="text" id="cat" name="cat"/><input type="button" value="添加" onclick="add()"/><input type="button" value="清空" onclick="clearTypeNameInput()"/></div>
  				<div class="typedesc">描　　述 <input type="text" id="desc" name="desc" size="27"/></div>
  		</div>
  		<div id="TypeOp">
  			<span class="h">管理已有分类　<b>[不能删除已经存在文章的分类]</b></span>
  			<div class="opp">
  				<div class="name">分类名称</div>
  				<div class="edit">重命名</div>
  				<div class="del">删除</div>
  			</div>
  			<div id="auto">
  			<s:iterator id="ATP" value="#session.ALL_ARTICLE_TYPE" status="st">
  				<div id="tips${ATP.id}" class="tips"></div>
	  			<div id="op${ATP.id}" class="op">
	  				<div id="name${ATP.id}" class="name">${ATP.typeName}</div>
	  				<div id="edit${ATP.id}" class="edit"><a href="javascript:renameATypeName(${ATP.id});"><img alt="重命名" src="myimages/edit.gif"/></a></div>
	  				<div id="del${ATP.id}" class="del"><a href="javascript:delArticleType(${ATP.id})"><img alt="删除" src="myimages/del.gif"/></a></div>
	  			</div>
  			</s:iterator>
  			</div>
  		</div>
  		<br>
  	</div>
  	<script type="text/javascript" src="<s:url value='decorators/js/jquery/jquery-latest.js'/>"></script>
  	<script type="text/javascript" src="<s:url value='myjs/ArticleJs.js'/>"></script> 
  	<script type="text/javascript">$(function(){initHover();});</script>
  </body>
</html>
