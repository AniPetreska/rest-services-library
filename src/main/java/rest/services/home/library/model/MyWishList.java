package rest.services.home.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WishList")
public class MyWishList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column
	private String author;

	@Column
	private String language;

	@Column
	private String genre;

	@Column
	private Integer numPages;

	@Column
	private Boolean status;

	public MyWishList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyWishList(String name, String author, String language, String genre, Integer numPages, Boolean status) {
		super();
		this.name = name;
		this.author = author;
		this.language = language;
		this.genre = genre;
		this.numPages = numPages;
		this.status = status;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getNumPages() {
		return numPages;
	}

	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}

	@Override
	public String toString() {
		return "MyWishList [id=" + id + ", name=" + name + ", author=" + author + ", language=" + language + ", genre="
				+ genre + ", numPages=" + numPages + ", status=" + status + "]";
	}

}
