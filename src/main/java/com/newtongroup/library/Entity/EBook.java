package com.newtongroup.library.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name="e_books")
@PrimaryKeyJoinColumn(name = "book_common_data_id")
public class EBook extends AbstractBook {

	@Column(name="download_link")
	private String downloadLink;
	
	@OneToOne(mappedBy = "ebook")
	private RemovedEBook removedEbook;


	public EBook() {
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public RemovedEBook getRemovedEbook() {
		return removedEbook;
	}

	public void setRemovedEbook(RemovedEBook removedEbook) {
		this.removedEbook = removedEbook;
	}
	
	

}
