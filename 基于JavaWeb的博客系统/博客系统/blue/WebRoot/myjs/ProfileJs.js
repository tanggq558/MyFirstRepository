function getUrl3(status){
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

function to3(url){
	top.location.href=url;
}
function delLeavemsg(id){
	var pop=new Popup({ contentType:3,isReloadOnClose:true,width:340,height:80});
	pop.setContent("title","删除留言");
	pop.setContent("confirmCon","您确定删除这条留言吗?");
	pop.setContent("callBack",delLeavemsgCallBack);
	pop.setContent("parameter",{id:id,obj:pop});
	pop.build();
	pop.show();
}
function delLeavemsgCallBack(para){
	var cid=para["id"];
	$.getJSON("msgdel.htm",{id:cid},
		function(data){
			var str;
			if(data.tips=="no_login"){
				login();
			}
			else if(data.tips=="ok")
				str="<div style='margin:30px 10px;text-align:center;width:300px;font-size:14px;color:black'>删除留言成功</div>";
		   	else
				str="<div style='margin:30px 10px;text-align:center;width:300px;font-size:14px;color:black'>删除留言失败</div>";
		   	var pop = para["obj"];
		   	pop.config.contentType=2;
			pop.setContent("title","删除留言");
            pop.setContent("contentHtml",str);
			pop.reBuild();
			setTimeout("to3('"+getUrl3(1)+"')",1000);
		}
	);
}

function showProfile(){
	g_pop=new Popup({ contentType:1,isReloadOnClose:true,width:'400',height:'425'});
	g_pop.setContent("title","修改信息");
	g_pop.setContent("contentUrl","userinfoOp.do");
	g_pop.build();
	g_pop.show();
}

/********************/
var realname,password,confpassword,birthplace,liveplace,email,introduce;
function getProfileValue(){
	realname=$("#realname").val();
	password=$("#password").val();
	confpassword=$("#conf_password").val();
	birthplace=$("#birthplace").val();
	liveplace=$("#liveplace").val();
	email=$("#email").val();
	introduce=$("#reply").val();
}

var r,p,cp,bp,lp,e,it;
function getConfProfileValue(){
	r=$("#realname").val();
	p=$("#password").val();
	cp=$("#conf_password").val();
	bp=$("#birthplace").val();
	lp=$("#liveplace").val();
	e=$("#email").val();
	it=$("#reply").val();
}


function initProfile(){
	getProfileValue();
}

function editProfile(){
	getConfProfileValue();
	if(r==realname&&p==password&&cp==confpassword&&bp==birthplace&&lp==liveplace&&e==email&&it==introduce){
		showError("无更改");
		return false;
	}
	if(r.length>10||r.length<2){
		showError("姓名长度不正确,2-10位");
		return false;
	}
	if(!/^\w{8,15}$/.test(p)){
		showError("密码长度为8-15位,且不能为中文和特殊符号");
		return false;
	}
	if(!/^\w{8,15}$/.test(cp)){
		showError("确认密码长度为8-15位,且不能为中文和特殊符号");
		return false;
	}
	if(p!=cp){
		showError("密码两次输入不一致");
		return false;
	}
	if(bp.length>10||bp.length<4){
		showError("家乡长度不正确,4-10位.如:湖北武汉");
		return false;
	}
	if(lp.length>10||lp.length<4){
		showError("现居地长度不正确,4-10位.如:湖北广水");
		return false;
	}
	if(!/^[0-9a-zA-Z_\-\.]+@[0-9a-zA-Z_\-]+(\.[0-9a-zA-Z_\-]+)*$/.test(e)){
		showError("邮箱格式不正确");return false;
	}
	if(it.length>100||it.length<10){
		showError("个人简介长度不正确,最少10个字");
		return false;
	}
	
	var id=$("#HideId").val();
	
	$.getJSON("editUserInfo.htm",{uid:id,realName:r,password:p,confPassword:cp,birthPlace:bp,livePlace:lp,email:e,introduce:it},
		function(data){
			if(data.tips=="no_login"){
				login();
			}
			else if(data.tips=="ok")
				showError("修改个人资料成功");
		   	else
		   		showError(data.tips);
		}
	);
	return false;
}

function showError(data){
	$(".error").text(data).fadeIn("fast").fadeOut(3000);
}