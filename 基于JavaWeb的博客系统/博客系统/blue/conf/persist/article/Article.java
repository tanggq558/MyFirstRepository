package persist.article;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"serial"})
public class Article  implements java.io.Serializable {  

    private Integer id=0;
    private ArticleType articleType;
    private String title;
    private String author;
    private String content;
    private int scan=0;
    private String time;

	@SuppressWarnings("unchecked")
	private Set articleComments = new HashSet(0);

    public Integer getId() {
        return this.id;
    }

    public ArticleType getArticleType() {
        return this.articleType;
    }
    
    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public int getScan() {
		return scan;
	}

	public void setScan(int scan) {
		this.scan = scan;
	}

	public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }

  
	@SuppressWarnings("unchecked")
	public Set getArticleComments() {
        return this.articleComments;
    }
    
    @SuppressWarnings("unchecked")
	public void setArticleComments( Set articleComments) {
        this.articleComments = articleComments;
    }
}