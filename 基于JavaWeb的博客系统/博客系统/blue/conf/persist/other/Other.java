package persist.other;

@SuppressWarnings("serial")
public class Other  implements java.io.Serializable {

    private Integer id;
    private Integer access;

    public Integer getId() {
        return this.id;
    }

    public Integer getAccess() {
        return this.access;
    }
    
    public void setAccess(Integer access) {
        this.access = access;
    }

}