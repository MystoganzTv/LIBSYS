package com.newtongroup.library.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Seminary")
public class Seminary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seminary_id")
    private Long seminary_id;

    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "occurrence")
    private java.util.Date occurrence;

    public Seminary() {
    }

    public Long getSeminary_id() {
        return seminary_id;
    }

    public void setSeminary_id(Long seminary_id) {
        this.seminary_id = seminary_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Date occurrence) {
        this.occurrence = occurrence;
    }
}
