package code.smartgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.smartgate.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
}
