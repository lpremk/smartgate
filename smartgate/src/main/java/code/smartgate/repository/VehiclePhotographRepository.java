package code.smartgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.smartgate.entity.VehiclePhotograph;

public interface VehiclePhotographRepository extends JpaRepository<VehiclePhotograph, Long> {
	
}
