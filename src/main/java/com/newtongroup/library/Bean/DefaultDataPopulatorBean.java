package com.newtongroup.library.Bean;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.newtongroup.library.Entity.Admin;
import com.newtongroup.library.Entity.Authority;
import com.newtongroup.library.Entity.User;
import com.newtongroup.library.Repository.AdminRepository;
import com.newtongroup.library.Repository.AuthorityRepository;
import com.newtongroup.library.Repository.UserRepository;

@Transactional
public class DefaultDataPopulatorBean {
	
	@Autowired
	private AdminRepository repo;
	@Autowired
	private UserRepository uRepo;

	
	@Autowired
	private AuthorityRepository aRepo;
    
    @PostConstruct
    public void createDefaultAdmin() {
    	try {
    	Admin admin = new Admin();
    	admin.setEmail("db@gmail.com");
    	admin.setCity("Sthlm");
    	admin.setFirstName("Databas");
    	admin.setLastName("Bosse");
    	admin.setPersonalNumber("19900621-1234");
    	admin.setPhone("07012343344");
    	admin.setPostalCode("11234");
    	admin.setStreet("Alis 32");
    	repo.save(admin);
    	
    	Authority a = new Authority();
    	a.setAuthorityName("ADMIN");
    	aRepo.save(a);
    	
    	User user = new User();
    	user.setAuthority(a);
    	user.setEnabled(true);
    	user.setPassword("pw123");
    	user.setUsername("db@gmail.com");
    	
    	uRepo.save(user);
    	}catch(Exception e) {
    		System.out.println("skipped creating default user");
    	}
    }
}
