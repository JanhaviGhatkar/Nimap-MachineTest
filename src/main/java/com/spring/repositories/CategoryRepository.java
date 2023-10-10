package com.spring.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.spring.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Page<Category> findAll(Pageable pageable);
	Optional<Category> findByName(String name);

}
