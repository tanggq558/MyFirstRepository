package persist.article;

@SuppressWarnings("serial")
public class ArticleComment  implements java.io.Serializable {
	
     private Integer id;
     private Article article;
     private String user;
     private String comment;
     private String time;

    public Integer getId() {
        return this.id;
    }

    public Article getArticle() {
        return this.article;
    }
    
    public void setArticle(Article article) {
        this.article = article;
    }

    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}