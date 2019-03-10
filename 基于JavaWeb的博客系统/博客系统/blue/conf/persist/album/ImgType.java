package persist.album;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"serial"})
public class ImgType  implements java.io.Serializable {


    private Integer id=0;
    private String typeName;
    private String typeDesc;
	
	@SuppressWarnings("unchecked")
	private Set imgs = new HashSet(0);

    public Integer getId() {
        return this.id;
    }

    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}


	@SuppressWarnings("unchecked")
	public Set getImgs() {
        return this.imgs;
    }
    
    @SuppressWarnings("unchecked")
	public void setImgs( Set imgs) {
        this.imgs = imgs;
    }
}