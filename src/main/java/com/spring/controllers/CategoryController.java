package com.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.models.Category;
import com.spring.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(" ")
	public Page<Category> getAllCategory(@RequestParam (defaultValue = "1")int page,@RequestParam (defaultValue = "3") int pagesize){
		return categoryService.getAllCategory(page, pagesize);
	}
	@PostMapping(" ")
	public ResponseEntity<?> addNewCategory (@RequestBody @Valid Category category){
		return categoryService.addNewCategory(category);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable int id) {
		return categoryService.getCategoryById(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody @Valid Category category, @PathVariable int id) {
		return categoryService.updateCategory(category, id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteCategory(@PathVariable int id) {
		return categoryService.deleteCategory(id);
	}
	

}
