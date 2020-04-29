package com.newtongroup.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newtongroup.library.Entity.RemovedEBook;

public interface RemovedEBookRepository  extends JpaRepository<RemovedEBook, Long> {
}
