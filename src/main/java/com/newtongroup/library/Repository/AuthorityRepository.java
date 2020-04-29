package com.newtongroup.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newtongroup.library.Entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
