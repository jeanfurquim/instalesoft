package com.instalesoft.instalesoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instalesoft.instalesoft.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
	
	
}
