package rest.services.home.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BorrowedBooks")
public class BorrowedBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column
	private String person;

	@Column
	private String date;

	public BorrowedBook() {
		super();
	}

	public BorrowedBook(Long id, String name, String person, String date) {
		super();
		this.id = id;
		this.name = name;
		this.person = person;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BorrowedBook [id=" + id + ", name=" + name + ", person=" + person + ", date=" + date + "]";
	}
}
