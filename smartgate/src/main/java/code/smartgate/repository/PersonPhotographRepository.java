package code.smartgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.smartgate.entity.PersonPhotograph;

public interface PersonPhotographRepository extends JpaRepository<PersonPhotograph, Long> {
	
}
