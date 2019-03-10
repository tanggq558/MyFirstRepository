package persist.user;

@SuppressWarnings("serial")
public class UserInfo  implements java.io.Serializable {

     private Integer id;
     private Login login;
     private String realName;
     private boolean sex;
     private String birthday;
     private String email;
     private String birthPlace;
     private String livePlace;
     private String registTime;
     private String lastLoginTime;
     private String introduce;

    public Integer getId() {
        return this.id;
    }
    
    public Login getLogin() {
        return this.login;
    }
    
    public void setLogin(Login login) {
        this.login = login;
    }

    public String getRealName() {
        return this.realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }

    public boolean getSex() {
        return this.sex;
    }
    
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

	public String getBirthPlace() {
        return this.birthPlace;
    }
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
    
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getLivePlace() {
        return this.livePlace;
    }
    
    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    public String getRegistTime() {
        return this.registTime;
    }
    
    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getLastLoginTime() {
        return this.lastLoginTime;
    }
    
    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getIntroduce() {
        return this.introduce;
    }
    
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}