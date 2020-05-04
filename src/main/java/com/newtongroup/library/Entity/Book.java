package com.newtongroup.library.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity
@Indexed
@Table(name = "books")
public class Book extends AbstractBook {

	//@JsonIgnore
	@JsonManagedReference
	@IndexedEmbedded
	@ManyToMany()
	@JoinTable(
			name="book_author",
			joinColumns = {@JoinColumn(name="idbook_author_book_id")},
			inverseJoinColumns = {@JoinColumn(name="idbook_author_author_id")})
	private List<Author> authorList;

	@JsonManagedReference
	@ManyToOne()
	@JoinColumn(name = "placement_id")
	private Placement placement;

	public Placement getPlacement() {
		return placement;
	}

	public void setPlacement(Placement placement) {
		this.placement = placement;
	}


	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

}
