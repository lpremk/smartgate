package code.smartgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.smartgate.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
	
}
