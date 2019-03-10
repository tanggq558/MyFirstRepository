package persist.user;

@SuppressWarnings("serial")
public class Login  implements java.io.Serializable {

	private Integer id;
	private String username;
	private String password;
	private UserInfo userInfo;

    public Integer getId() {
        return this.id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}