package com.newtongroup.library.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "books_common_data")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractBook extends AbstractRental {

	@Column
	@Field
	private String isbn;
	
	@ManyToMany
	@JoinTable(
			name="book_author",
			joinColumns = {@JoinColumn(name="book_common_data_id")},
			inverseJoinColumns = {@JoinColumn(name="author_id")}
			)
	private List<Author> authorList;

	public AbstractBook() {

	}

	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

}
