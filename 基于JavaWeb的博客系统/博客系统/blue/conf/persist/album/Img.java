package persist.album;

@SuppressWarnings("serial")
public class Img  implements java.io.Serializable {

    private Integer id=0;
    private ImgType imgType;
    private String imgname;
    private String imgurl;
    private String imgdesc;
    private String imginfo;
    private boolean imgtype;
    private String time;

    public Integer getId() {
        return this.id;
    }

    public ImgType getImgType() {
        return this.imgType;
    }
    
    public void setImgType(ImgType imgType) {
        this.imgType = imgType;
    }

    public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

    public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getImgdesc() {
		return imgdesc;
	}

	public void setImgdesc(String imgdesc) {
		this.imgdesc = imgdesc;
	}

	public String getImginfo() {
		return imginfo;
	}

	public void setImginfo(String imginfo) {
		this.imginfo = imginfo;
	}

	public boolean isImgtype() {
		return imgtype;
	}

	public void setImgtype(boolean imgtype) {
		this.imgtype = imgtype;
	}

	public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
}