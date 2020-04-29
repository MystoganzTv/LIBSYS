package com.newtongroup.library.Entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "removed_books")
@PrimaryKeyJoinColumn(name = "removed_object_id")
public class RemovedBook extends AbstractRemovedObject {

	@OneToOne()
	@JoinColumn(name = "book_id")
	private Book book;

    }
