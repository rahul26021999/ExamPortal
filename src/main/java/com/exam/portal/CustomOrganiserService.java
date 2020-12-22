package com.exam.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.exam.portal.Model.Organiser;
import com.exam.portal.Repository.OrganiserRepository;

public class CustomOrganiserService implements UserDetailsService {

	@Autowired
	OrganiserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Organiser org=repo.findByEmail(email);
		if(org==null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new CustomOrganiserDetails(org);
	}
}
