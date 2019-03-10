package com.tools;
//分页功能实现

public class DiviedPage {
	public static String getPageLink(int tp,int sp,int type,String url){
		String link="";
		String linkurl=null;
		String sl="<a href='";
		String sr="'>";
		String e="</a> ";
		String l="[ ";
		String r=" ]";
		
		if(type==0){
			linkurl=url+"?showPage=";
		}else{
			linkurl=url+"?type="+type+"&showPage=";
		}
		sl+=linkurl;
		if(tp>1){
			if(tp>10)
				link=sl+"1"+sr+l+"首页"+r+e;
			else
				link="页码: ";
			for(int i=1;i<=tp;i++){
				if(i!=sp){
					link+=sl+i+sr+l+i+r+e;
				}else{
					link+="<font style='font-size:14px;color:red;font-weight:bold'> "+i+" </font>";
				}
			}
			if(tp>10)
				link+=sl+tp+sr+l+"末页"+r+e;
		}
		return link;
	}
}
