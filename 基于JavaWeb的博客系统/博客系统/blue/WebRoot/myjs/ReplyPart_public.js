function getUrl2(status){
	var furl;
	var url=window.location.href;
	var i=url.lastIndexOf("#");
	
	if(status==1){
		if(i>0){
			furl=url.substring(0,i);
		}else
			furl=url;
	}
	return furl;
}

function to2(url){
	top.location.href=url;
}
/*
function checkword(num){
	var reply=$("#reply").val();
	var used=reply.length;
	var rem=num-used;
	if(used>num){
		$("#reply").val($("#reply").val().substring(0,num));
		alert("最多只能输入 "+num+" 个字符");
		return;
	}
	$(".userNum span:eq(0)").html(""+used);
	$(".userNum span:eq(1)").html(""+rem);
}
*/
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}
function showCode(){
	$(".varcode").show();
}

var oEditor;
var user,content,code;
function checkInput(){
	user=$("#user").val();
	if(navigator.userAgent.indexOf("MSIE")>0||navigator.userAgent.indexOf("Firefox")>0) { 
		oEditor = FCKeditorAPI.GetInstance("reply"); 
		content= oEditor.GetXHTML(true); 
	}else{
		content=document.getElementsByTagName("textarea")[0].value;
	}
	if(user==null||user.length>15||user.length<2){
		showError("姓名长度不正确[2-15]");
		return false;
	}
	user=trim(user);
	content=trim(content);
	if(content==null||content.length>500||content.length<5){
		showError("内容长度不正确[5-500]");
		return false;
	}
	return true;
}

function publishComment(index,id){
	code=$("#code").val();
	if(!checkInput()){
		return false;
	}
	if(index>0){
		var aid=$("#HideId").val();
		$.getJSON("ArticleComment.htm",{id:aid,user:user,comment:content,code:code},
			function(data){
				if(data.tips=="ok"){
					ShowAlert("发表评论","发表评论成功");
					setTimeout("to2('"+getUrl2(1)+"')",1000);
				}else{
					showError(data.tips);
				}
			}
		);
	}else{
		$.getJSON("CommentReply.htm",{id:id,comment:content,code:code},
			function(data){
				if(data.tips=="ok"){
					ShowAlert("回复评论","回复评论成功");
					setTimeout("to2('"+getUrl2(1)+"')",1000);
				}else{
					showError(data.tips);
				}
			}
		);
	}
	return false;
}

function reply(index,id){
	if(index==1){//回复文章评论
		$("#sub").html('<input type="submit" value="回复评论" onclick="return publishComment(-1,'+id+');">');
	}else if(index==2){
		$("#sub").html('<input type="submit" value="回复留言" onclick="return leaveMsg(-1,'+id+');">');
	}
  	var user=$("#HideUser").val();
	$("#user").val(user);
	var rmsg=$("#msg"+id).html();
	oEditor = FCKeditorAPI.GetInstance("reply");
	oEditor.SetHTML(rmsg+"<p><font color='red'>博主("+user+")回复</font>:");
}

function leaveMsg(index,id){
	code=$("#code").val();
	if(!checkInput()){
		return false;
	}
	if(index>0){
		$.getJSON("msgleave.htm",{name:user,content:content,code:code},
			function(data){
				if(data.tips=="ok"){
					ShowAlert("给我留言","给我留言成功");
					setTimeout("to2('"+getUrl2(1)+"')",1000);
				}else{
					showError(data.tips);
				}
			}
		);
	}else{
		$.getJSON("msgreply.htm",{id:id,content:content,code:code},
			function(data){
				if(data.tips=="ok"){
					ShowAlert("回复留言","回复留言成功");
					setTimeout("to2('"+getUrl2(1)+"')",1000);
				}else{
					showError(data.tips);
				}
			}
		);

	}
	return false;
}

function ShowAlert(title,str){
  str="<div style='margin:30px 10px;text-align:center;width:300px;font-size:14px;color:black'>"+str+"</div>"
  pop=new Popup({ contentType:2,isReloadOnClose:false,width:340,height:80});
  pop.setContent("title",title);
  pop.setContent("contentHtml",str);
      pop.build();
      pop.show();
}

function showError(data){
	$(".error").text(data).css("color","red").fadeIn("fast").fadeOut(3000);
}