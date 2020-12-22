package com.exam.portal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
