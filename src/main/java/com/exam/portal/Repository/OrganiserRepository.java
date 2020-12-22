package com.exam.portal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exam.portal.Model.Organiser;

public interface OrganiserRepository extends JpaRepository<Organiser, Long> {

	@Query("Select u From organisers u WHERE u.email=?1")
	Organiser findByEmail(String email);
}
