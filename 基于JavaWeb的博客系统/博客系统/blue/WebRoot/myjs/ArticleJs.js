function getUrl(status){
	var furl;
	var url=window.location.href;
	var i=url.lastIndexOf("#");
	
	if(status==1){
		if(i>0){
			furl=url.substring(0,i);
		}else
			furl=url;
	}
	else if(status==2){ 
		var j=url.lastIndexOf("?");
		if(j>0) furl="showArticleList.htm";
		else furl=url;
	}
	return furl;
}

function to(url){
	top.location.href=url;
}
function delArticle(aid){
   var pop=new Popup({ contentType:3,isReloadOnClose:true,width:340,height:80});
   pop.setContent("title","删除文章");
   pop.setContent("confirmCon","您确定要彻底删除这篇文章及其所有评论吗?");
   pop.setContent("callBack",delArticleCallBack);
   pop.setContent("parameter",{id:aid,obj:pop});
   pop.build();
   pop.show();
}

function delArticleCallBack(para){
    var did=para["id"];
    $.getJSON("delArticle.htm",{id:did},
		function(data){
			var str;
			if(data.tips=="ok")
				str="<div style='margin:30px 10px;text-align:center;width:300px;font-size:14px;color:black'>删除文章成功</div>"
			else if(data.tips=="no_login"){
				login();
			}
		   	else
				str="<div style='margin:30px 10px;text-align:center;width:300px;font-size:14px;color:black'>删除文章失败</div>"
		   		
		   	var pop = para["obj"];
		   	pop.config.contentType=2;
			pop.setContent("title","删除文章");
            pop.setContent("contentHtml",str);
			pop.reBuild();
			setTimeout("to('"+getUrl(2)+"')",1000);
 		}
 	);
}

function initHover(){
	$(".op").mouseover(
		function(){$(this).attr("class","op1");}
	);
	$(".op").mouseout(
		function(){$(this).attr("class","op");}
	);
}

function add(){
	var cat=$("#cat").val();
	var desc=$("#desc").val();
	
	if(cat==null||cat.length>8||cat.length<4){
		showError("类别长度不正确,长度在4-8 之间");return;
	}
	if(desc==null||desc.length>15||desc.length<8){
		showError("描述长度不正确,长度在8-15 之间");return;
	}
	$.getJSON("addNewArticleType.htm",{articleTypeName:cat,articleDesc:desc},
		function(data){
			if(data.tips=="no_login"){
				login();
			}
			if(data.tips=="ok"){
				showError("添加成功");
				insertIntoListByAjax(cat,data.myid);
			}else if(data.tips=="no_login"){
				login();
			}else{
				showError(data.tips);
				//clearTypeNameInput();
			}
		}
	);
}
function clearTypeNameInput(){
	$("#cat").val('');
	$("#desc").val('');
}
function insertIntoListByAjax(cat,index){
	clearTypeNameInput();
	var str="";
	str+='<div id="tips'+index+'" class="tips"></div>'
			+'<div id="op'+index+'" class="op">'
			+'<div id="name'+index+'" class="name">'+cat+'</div>'
			+'<div id="edit'+index+'" class="edit"><a href="javascript:renameATypeName('+index+');"><img alt="重命名" src="myimages/edit.gif"/></a></div>'
			+'<div id="del'+index+'" class="del"><a href="javascript:delArticleType('+index+')"><img alt="删除" src="myimages/del.gif"/></a></div></div>';
	$("#auto").append(str);
	
	initHover();
}

function showError(data){
	$(".error").text(data).css("color","red").fadeIn("fast").fadeOut(3000);
}

function renameATypeName(index){
	var name=$("#name"+index);
	var edit=$("#edit"+index);
	var del=$("#del"+index);
	var txt=name.text();
	name.html("<input type='text' id='typename"+index+"' name='typename' value='"+name.text()+"'/>");
	edit.html("<a href=javascript:confRename("+index+",'"+txt+"')>修改</a>");
	var typename=$("#typename"+index).val();
	del.html("<a href=javascript:cancleRename("+index+",'"+typename+"')>取消</a>");
}
function confRename(index,txt){
	var cat=$("#typename"+index).val();
	if(txt==cat){
		cancleRename(index,cat);
		showError2(index,"无修改");
		return;
	}
	if(cat==null||cat.length>8||cat.length<4){
		showError2(index,"类别长度不正确,长度在4-8 之间");return;
	}
	$.getJSON("renameArticleType.htm",{aid:index,articleTypeName:cat},
		function(data){
			if(data.tips=="ok"){
				cancleRename(index,cat);
				showError2(index,"修改成功");
			}else if(data.tips=="no_login"){
				login();
			}else{
				showError2(index,data.tips);
			}
		}
	);
}
function cancleRename(index,typename){
	var name=$("#name"+index);
	var edit=$("#edit"+index);
	var del=$("#del"+index);
	name.html(typename);
	edit.html('<a href="javascript:renameATypeName('+index+');"><img alt="重命名" src="myimages/edit.gif"/></a>');
	del.html('<a href="javascript:delArticleType('+index+');"><img alt="删除" src="myimages/del.gif"/></a>');
}
function delArticleType(id){
	$.getJSON("delArticleType.htm",{aid:id},
		function(data){
			if(data.tips=="ok"){
				removeInListByAjax(id);
			}else if(data.tips=="no_login"){
				login();
			}else{
				showError2(id,data.tips);
			}
		}
	);
}
function removeInListByAjax(index){
	$("#op"+index).hide();
	showError2(index,"删除成功");
}
function showError2(index,data){
	$("#tips"+index).text(data).fadeIn("fast").fadeOut(3000);
}

function editTypeName(){
	g_pop=new Popup({ contentType:1,scrollType:'no',isReloadOnClose:true,width:'460',height:'350'});
	g_pop.setContent("title","编辑分类");
	g_pop.setContent("contentUrl","goarticleTypeOp.do");
	g_pop.build();
	g_pop.show();
}

/****edit*********/
var arr;
var pop;
function initEdit(id){
	var i=0;
	$("#a_t option").each(function(){
		if($(this).val()==id){
			$("#a_t").get(0).selectedIndex=i;
		}
		i=i+1;
	});
	$(".sub").html('<input type="submit" value="确认修改" onclick="return check(-1);"/>');
}

function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function check(index){
	var t=$("#title").val();
    var oEditor = FCKeditorAPI.GetInstance("content"); 
    var c=oEditor.GetXHTML(true);

	var tp=$("#a_t").val();
	
	t=trim(t);
	c=trim(c);
	
	if(t==null||t.length>20||t.length<1){
		showError("标题长度不正确");return false;
	}
	if(c==null||c.length<1){
		showError("正文内容不能为空");return false;
	}
	if(tp==null||tp==""){
		showError("请选择文章类别");return false;
	}
	if(index>0){
		$("#articleForm").attr("action","addNewArticle.htm");
	}
	if(index<0){
		$("#articleForm").attr("action","confEditArticle.htm");
	}
	$("#articleForm").submit();
	return false;
}

function addType(){
	var str="<div id='typeError' style='margin:20px 70px;text-align:left;width:260px;font-size:12px;color:#000;'>"
			+"<span id='error3'></span><p><p>"
			+"<div>类别名称 <input type='text' id='cat'/><input type='button' value='添加' onclick='add3()'/></div>"
			+"<div style='margin:5px 0 10px 0;'>描　　述 <input type='text' id='desc' size='27'/></div>"
			+"</div>"
	ShowAlert(str);
}
function add3(){
	var desc=$("#desc").val();
	var cat=$("#cat").val();
	if(cat==null||cat.length>8||cat.length<4){
		showError3("类别长度不正确,长度在4-8 之间");return;
	}
	if(desc==null||desc.length>15||desc.length<8){
		showError3("描述长度不正确,长度在8-15 之间");return;
	}
	$.getJSON("addNewArticleType.htm",{articleTypeName:cat,articleDesc:desc},
		function(data){
			if(data.tips=="no_login"){
				login();
			}
			else if(data.tips=="ok"){
				$("#typeError").text("添加新类别成功");
				setTimeout("to('"+getUrl(1)+"')",1000);
			}else{
				showError3(data.tips);
			}
		}
	);
}
function showError3(data){
	$("#error3").text(data).fadeIn("fast").fadeOut(3000);
}
 function ShowAlert(str){
    pop=new Popup({ contentType:2,isReloadOnClose:true,width:340,height:80});
    pop.setContent("title","创建新文章/修改文章");
    pop.setContent("contentHtml",str);
    pop.build();
    pop.show();
}
 /****detial************/
 function delComment(sid){
   var pop=new Popup({ contentType:3,isReloadOnClose:true,width:340,height:80});
   pop.setContent("title","删除评论");
   pop.setContent("confirmCon","您确定删除这条评论吗?");
   pop.setContent("callBack",delCommentCallBack);
   pop.setContent("parameter",{id:sid,obj:pop});
   pop.build();
   pop.show();
}

function delCommentCallBack(para){
	var cid=para["id"];
	$.getJSON("delComment.htm",{id:cid},
		function(data){
			var str;
			if(data.tips=="no_login"){
				login();
			}
			else if(data.tips=="ok")
				str="<div style='margin:30px 10px;text-align:center;width:300px;font-size:14px;color:black'>删除评论成功</div>";
			else
				str="<div style='margin:30px 10px;text-align:center;width:300px;font-size:14px;color:black'>删除评论失败</div>";
		   	var pop = para["obj"];
		   	pop.config.contentType=2;
			pop.setContent("title","删除评论");
            pop.setContent("contentHtml",str);
			pop.reBuild();
			setTimeout("to('"+getUrl(1)+"')",1000);
		}
	);
}