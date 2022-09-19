package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idNews = 0;
	private String newsDate;
	private String title = "";
	private String brief = "";
	private String content = "";
	private int userId = 0;	
	
	public News() {
	}

	public News(int idNews, String newsDate, String title, String brief, String content, int userId) {
		super();

		this.idNews = idNews;
		this.newsDate = newsDate;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.userId = userId;
	}

	public News(int idNews, String newsDate, String title, String brief, String content) {
		super();

		this.idNews = idNews;
		this.newsDate = newsDate;
		this.title = title;
		this.brief = brief;
		this.content = content;
	}	
	
	public Integer getIdNews() {
		return idNews;
	}

	public void setIdNews(Integer idNews) {
		this.idNews = idNews;
	}

	public String getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "News{" + "idNews=" + idNews + ", newsDate='" + newsDate + '\'' + ", title='" + title + '\''
				+ ", brief='" + brief + '\'' + ", content='" + content + '\'' + ", userId=" + userId + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		News news = (News) o;
		return idNews == news.idNews && userId == news.userId && Objects.equals(newsDate, news.newsDate)
				&& Objects.equals(title, news.title) && Objects.equals(brief, news.brief)
				&& Objects.equals(content, news.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNews, newsDate, title, brief, content, userId);
	}

}
