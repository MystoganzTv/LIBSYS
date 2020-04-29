package com.newtongroup.library.Entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "books")
@PrimaryKeyJoinColumn(name = "book_common_data_id")
public class Book extends AbstractBook {
	
	
	@ManyToOne()
	@JoinColumn(name = "placement_id")
	private Placement placement;
	
	@OneToOne(mappedBy = "book")
	private RemovedBook removedBook;

	public Placement getPlacement() {
		return placement;
	}

	public void setPlacement(Placement placement) {
		this.placement = placement;
	}

	public RemovedBook getRemovedBook() {
		return removedBook;
	}

	public void setRemovedBook(RemovedBook removedBook) {
		this.removedBook = removedBook;
	}
	
	


}
