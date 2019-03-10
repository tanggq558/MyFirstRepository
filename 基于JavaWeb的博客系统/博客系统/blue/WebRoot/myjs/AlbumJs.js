function setClickBg(){
	var type=$("#hideType").val();
	var mark=$("#a"+type).attr("class");
	$("#a"+type).css("background","url('myimages/frame"+mark+"_visited.jpg') no-repeat center");
}

function public_validate(val1,val2){
	if(val1==null||val1.length>10||val1.length<1){
		showError("相册名称长度不正确,1-10位");return false;
	}
	if(val2==null||val2.length>25||val2.length<1){
		showError("相册描述长度不正确,1-25位");return false;
	}
	return true;
}

function opAlbum(id,name,desc,mark){
	var str="<div id='typeError' style='margin:20px 20px 20px 20px;text-align:left;width:320px;font-size:12px;color:#000;'>"
		+"<div>名称: <input type='text' id='cat' size='30' value='"+name+"' maxlength='10'/> <span style='color:#999;'>[不超过10个字符]</span></div>"
		+"<div style='margin:5px 0 0px 0;'>描述: <span style='color:#999;'>[最多不能超过25个字符]</span></div>"
		+"<div style='margin:0px 30px;'><textarea id='desc' style='width:290px;height:80px;' >"+desc+"</textarea></div>"
		+"<div id='error' style='margin:5px 30px;color:red;'></div>"
		+"<div style='margin:5px 30px';>";
	if(mark){ 
		str+="<input type='button' value='修改' onclick='alterImgType("+id+")'/>";
		title="编辑相册";
	}else{
		str+="<input type='button' value='添加' onclick='addImgType()'/>";
		title="创建新相册";
	}
	str+="</div></div>";
	Show(str,title,340,80);
}
function Show(str,title,w,h){
    var pop=new Popup({contentType:2,isReloadOnClose:false,width:w,height:h});
    pop.setContent("title",title);
    pop.setContent("contentHtml",str);
    pop.build();
    pop.show();
}
function alterImgType(id){
	var name=$("#cat").val();
	var desc=$("#desc").val();
	if(public_validate(name,desc)){
		$.getJSON("albalterAlbum.htm",{id:id,typeName:name,typeDesc:desc},
			function(data){
				if(data.tips=="ok"){
					ShowAlert("编辑相册成功","编辑相册");
					$("#name"+id).html(name);
				}else if(data.tips=="no_login"){
					login();
				}else{
					showError(data.tips);
				}
	 		}
	 	);
	}
}
function addImgType(){
	var name=$("#cat").val();
	var desc=$("#desc").val();
	if(public_validate(name,desc)){
		$.getJSON("albaddAlbum.htm",{typeName:name,typeDesc:desc},
			function(data){
				if(data.tips=="ok"){
					ShowAlert("创建新相册成功","创建新相册");
					create_new_album(data.myid,name,desc);
				}else if(data.tips=="no_login"){
					login();
				}else{
					showError(data.tips);
				}
				o_pop.reBuild();
	 		}
	 	);
	}
}
function ShowAlert(str,title){
	var pop=new Popup({contentType:4,isReloadOnClose:false,width:340,height:70});
    pop.setContent("title",title);
    pop.setContent("alertCon",str);
    pop.build();
    pop.show();
}
function create_new_album(id,name,desc){
	str="<li class='li"+id+"'>"
	   +"<a href='imgListShow.htm?type="+id+"' class='ls'><img src='myimages/album_no_pic.gif'/></a>"
	   +"<a href='#' id='name"+id+"' class='aname'>"+name+"(0)</a>"
	   +"<s:if test='#log!=null'><br><a href=javascript:opAlbum("+id+",'"+name+"','"+desc+"',true); class='aop'>√编辑</a>"
	   +"<a href='javascript:delAlbum("+id+")' class='aop'>×删除</a></s:if></li>";
	$("#albumcenter ul").append(str);
	
	/***在上传时候添加*****/
	var sel=$("#typeid option");
	if(sel.length>0){
		window.location.reload();
	}
}
function showError(data){
	$("#error").text(data).fadeIn("fast").fadeOut(3000);
}

/*********************/
function delAlbum(id){
   var pop=new Popup({ contentType:3,isReloadOnClose:false,width:340,height:80});
   pop.setContent("title","删除相册");
   pop.setContent("confirmCon","您确定要删除这个相册吗?");
   pop.setContent("callBack",delAlbumCallBack);
   pop.setContent("parameter",{id:id,obj:pop});
   pop.build();
   pop.show();
}

function delAlbumCallBack(para){
    var did=para["id"];
    if(did>1){
	   $.getJSON("albdelAlbum.htm",{id:did},
			function(data){
				var o_pop = para["obj"];
				o_pop.config.contentType=4;
				if(data.tips=="ok"){
				    o_pop.setContent("alertCon","删除成功,此相册下相片将会移动到\"默认相册\"");
				    $(".li"+did).hide();
				}else if(data.tips=="no_login"){
					login();
				}else{
					o_pop.setContent("alertCon",data.tips);
				}
				o_pop.reBuild();
	 		}
	 	);
    }
}

/********************/
function opImg(type_id,id,url,name,desc,info){
	/*alert(type_id+","+id+","+url+"/"+name+","+desc);
	var i=info.lastIndexOf("*");
	var j=info.lastIndexOf("|");
	var w=info.substring(0,i);
	var h=info.substring(i+1,j);
	alert(w+","+h);
	*/
	str="<div style='margin:20px 10px 20px 30px;text-align:left;width:450px;font-size:12px;color:#000;'>"
		+"<div style=''>预览: <span style='color:#999;'>"+info+"</span><div style='margin:0 0 5px 30px;'><img src='"+url+"/s"+name+"'/></div></div>"
		+"<div style='margin:5px 0 0px 0;'>描述: <span style='color:#999;'>[最多不能超过15个字符]</span></div>"
		+"<div style='margin:0px 30px;'><textarea id='desc' style='width:290px;height:60px;' >"+desc+"</textarea></div>"
		+"<div id='error' style='margin:5px 30px;color:red;'></div>"
		+"<div style='margin:5px 30px';><input type='button' width='120px' value='编辑' onclick='alterImg("+id+")'/></div></div>";

	Show(str,"编辑图片",500,300);
}
function alterImg(id){
	var d=$("#desc").val();
	if(d==null||d.length>15||d.length<1){
		showError("相片描述长度不正确,1-15位")
		return;
	}
	if(id>0){
		$.getJSON("albalterImg.htm",{id:id,imgDesc:d},
			function(data){
				if(data.tips=="ok"){
					ShowAlert("编辑相片成功","编辑相片");
					$("#name"+id).html(d);
				}else if(data.tips=="no_login"){
					login();
				}else{
					showError(data.tips);
				}
	 		}
	 	);
	}
}

function delImg(id){
	var pop=new Popup({ contentType:3,isReloadOnClose:false,width:340,height:60});
   pop.setContent("title","删除相片");
   pop.setContent("confirmCon","您确定要删除这张相片吗?");
   pop.setContent("callBack",delImgCallBack);
   pop.setContent("parameter",{id:id,obj:pop});
   pop.build();
   pop.show();
}
function delImgCallBack(para){
    var did=para["id"];
    if(did>0){
	   $.getJSON("albdelImg.htm",{id:did},
			function(data){
		    o_pop = para["obj"];
		    o_pop.config.contentType=4;
				if(data.tips=="ok"){
					o_pop.setContent("alertCon","删除相片成功");
				    $(".li"+did).hide();
				}else if(data.tips=="no_login"){
					login();
				}else{
					o_pop.setContent("alertCon","删除相片失败");
				}
				o_pop.reBuild();
	 		}
	 	);
    }
}

/*****************************/
var imglist;
var imgid;
var pos=0;
function setWidthAndHeight(info){
	var height=400;
	var width=550;
	var i=info.lastIndexOf("*");
	var j=info.lastIndexOf("|");
	var w=info.substring(0,i);
	var h=info.substring(i+1,j);
	var top;
	$("#si").css("margin-top","0");
	if(w>=width){
		w=width;
	}
	if(h>=height){
		h=height;
	}else{
		top=(height-h)/2;
		$("#si").css("margin-top",top+"px");
	}
	$("#si").css("width",w+"px");
	$("#si").css("height",h+"px");
}
function showBefore(typeid,id,info){
	setWidthAndHeight(info);
	getAllImg(typeid,id);
}
function getAllImg(did,iid){
	$.getJSON("imgShowAjax.htm",{id:did,iid:iid},
		function(data){
			list=data.mylist;
			id=data.myid;
			for(var i=0;i<list.length;i=i+1){
				if(list[i].id==id){
					pos=i+1;
					if(i==0){//first
						$(".pre").attr("href","javascript:;").attr("title","已经是第一张");
						$(".next").attr("href","javascript:showNext("+pos+")").attr("title","下一张");
					}else if(i==list.length-1){//last
						$(".next").attr("href","javascript:;").attr("title","已经是最后一张");
						$(".pre").attr("href","javascript:showPre("+pos+")").attr("title","上一张");
					}else{//middle
						$(".pre").attr("href","javascript:showPre("+pos+")").attr("title","上一张");
						$(".next").attr("href","javascript:showNext("+pos+")").attr("title","下一张");
					}
					$(".current").html(pos);
					break;
				}
				
			}
			getP(list,id);
		}
	);
}

function getP(list,id){
	imglist=list
	imgid=id;
}

function setAllInfo(pos){
	pos=pos-1;
	var url=imglist[pos].imgurl;
	var name=imglist[pos].imgname;
	var desc=imglist[pos].imgdesc;
	var info=imglist[pos].imginfo;
	var time=imglist[pos].time;
	setWidthAndHeight(info);
	$(".current").html(pos+1);
	$("#link").attr("href",url+"/"+name);
	$("#si").attr("src",url+"/"+name);
	$("#si").attr("alt",desc+"--"+info);
	$(".des").html(desc);
	$(".inf").html(info);
	$(".tim").html(time);
}

function showPre(i){
	if(i!=1){
		pos=i-1;
		$(".pre").attr("href","javascript:showPre("+pos+")").attr("title","上一张");
		$(".next").attr("title","下一张");
		setAllInfo(pos);
	}else{
		pos=i;
		$(".pre").attr("href","javascript:;").attr("title","已经是第一张");
	}
	$(".next").attr("href","javascript:showNext("+pos+")");
}
function showNext(i){
	if(i!=imglist.length){
		pos=i+1;
		$(".pre").attr("title","上一张");
		$(".next").attr("href","javascript:showNext("+pos+")").attr("title","下一张");
		setAllInfo(pos);
	}else{
		$(".next").attr("href","javascript:;").attr("title","已经是最后一张");
	}
	$(".pre").attr("href","javascript:showPre("+pos+")");
}

/***upload.jsp***************/

/*****set*****/
var defaultpic="myimages/img_preview.gif";
var item=0;
var tid;

function appendFile(ind){
	var str='';
	for(var i=0;i<ind;i=i+1){
		str+='<div class="onefile">'
			   +'<div class="file">'
			      +'<input type="file" name="upload" id="file'+item+'" onchange="return imgpreview('+item+');" /><br>'
			      +'<span id="error'+item+'" class="error"></span>'
			   +'</div>'
			   +'<div class="preview"><img id="pre'+item+'" src="'+defaultpic+'"/></div>'
			   +'<div class="desc">描述:<br><textarea name="desc" id="desc'+item+'"></textarea></div>'
		   +'</div>';
		item=item+1;
	}
	$("#uploadarea").append(str);
}
function addmore(ind){
	appendFile(ind);
	$(".addmore").hide();
}
function initTypeId(){
	var si=0;
	tid=$("#hideTypeid").val();
	$("#typeid option").each(function(){
		if($(this).val()==tid){
			$("#typeid").get(0).selectedIndex=si;
		}
		si=si+1;
	});
}
function imgpreview(id){
	var val=$("#file"+id).val();
	if(val.length>0){
		if(checkImgType(val)){
			document.getElementById("pre"+id).src=val;
			setDesc(id,val);
		}else{
			showUploadError(id,"图片类型不正确");
			$("#file"+id).val("");
			document.getElementById("pre"+id).src=defaultpic;
			$("#desc"+id).val("");
		}
	}else{
		document.getElementById("pre"+id).src=defaultpic;
	}
}
function checkImgType(val){
	var i=val.lastIndexOf(".");
	var tt=val.substring(i+1);
	if(tt=="png"||tt=="gif"||tt=="bmp"||tt=="jpg"||tt=="jpeg")
		return true;
	else
		return false;
}
function setDesc(id,val){
	var i=val.lastIndexOf("\\");
	if(i>-1){//for ie
		val=val.substring(i+1,val.length);
		$("#desc"+id).val(val);
	}else{//for firefox
		$("#desc"+id).val(val);
	}
}
function showUploadError(index,data){
	$("#error"+index).text(data).fadeIn("fast").fadeOut(3000);
}
/***check*******/
function checkUpload(){
	var status;
	for(var s=0;s<item;s=s+1){
		fileval=$("#file"+s).val();
		if(fileval==""||fileval==null){
			status=false;
		}else{
			status=true;
			break;
		}
	}
	var typeid=$("#typeid").val();
	if(typeid==""||typeid==null){
		showUploadError2("请选择相片类别,如果没有可自行创建");
		return false;
	}
	if(!status) showUploadError2("你还没有选择上传的相片,请选择");
	
	if(typeid==1){
		$("#uploadform").attr("action","uploadImg2.htm");
	}
	
	return status;
}
function showUploadError2(data){
	$(".error").text(data).fadeIn("fast").fadeOut(3000);
}
