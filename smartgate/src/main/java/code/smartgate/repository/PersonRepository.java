package code.smartgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.smartgate.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
}
