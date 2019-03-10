package persist.leavemsg;

@SuppressWarnings("serial")
public class Leavemsg  implements java.io.Serializable {

    private Integer id;
    private String comName;
    private String comContent;
    private String comTime;


    public Integer getId() {
        return this.id;
    }

    public String getComName() {
        return this.comName;
    }
    
    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComContent() {
        return this.comContent;
    }
    
    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public String getComTime() {
        return this.comTime;
    }
    
    public void setComTime(String comTime) {
        this.comTime = comTime;
    }
}