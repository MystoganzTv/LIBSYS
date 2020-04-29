package com.newtongroup.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newtongroup.library.Entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
