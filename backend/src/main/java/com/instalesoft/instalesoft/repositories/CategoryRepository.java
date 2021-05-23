package com.instalesoft.instalesoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instalesoft.instalesoft.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	
}
