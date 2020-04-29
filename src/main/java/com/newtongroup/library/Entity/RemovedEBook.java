package com.newtongroup.library.Entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "removed_e_books")
@PrimaryKeyJoinColumn(name = "removed_object_id")
public class RemovedEBook extends AbstractRemovedObject {

	@OneToOne
	@JoinColumn(name = "e_book_id")
	private EBook ebook;

	public EBook getEbook() {
		return ebook;
	}

	public void setEbook(EBook ebook) {
		this.ebook = ebook;
	}

}
