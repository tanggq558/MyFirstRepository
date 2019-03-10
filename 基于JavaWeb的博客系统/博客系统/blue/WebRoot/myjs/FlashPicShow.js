function picshow(width,height,txt_height,txt_align,url,txt,link,time){
/*参数依次表示：图象[宽、高],文字[高、对齐方式],图片[位置],文字[内容],停留时间*/
    
    var focus_width=width 
    var focus_height=height
    var text_height=txt_height 
    var text_align=txt_align
    
    var swf_height = focus_height+text_height
    
    var pics=url
    var texts=txt
    var links=link
    var interval_time=time
    
    document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
    document.write('<param name="movie" value="myimages/flash/pixviewer.swf"><param name="quality" value="high"><param name="bgcolor" value="#ffffff">');
    document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
    document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'&text_align='+text_align+'&interval_time='+interval_time+'">');
    document.write('<embed src="myimages/flash/pixviewer.swf" wmode="opaque" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'&text_align='+text_align+'&interval_time='+interval_time+'" menu="false" bgcolor="#ffffff" quality="high" width="'+ focus_width +'" height="'+ swf_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');
    document.write('</object>');
}