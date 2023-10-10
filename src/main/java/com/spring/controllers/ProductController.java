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

import com.spring.models.Product;
import com.spring.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping(" ")
	public Page<Product> getAllProducts(@RequestParam (defaultValue = "1") int page,@RequestParam(defaultValue = "5")  int pagesize){
		return productService.getAllProducts(page , pagesize);
	}
	@PostMapping(" ")
	public ResponseEntity<?> addNewProduct (@RequestBody @Valid Product product){
		return productService.addNewProduct(product);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody @Valid Product product,@PathVariable int id) {
		return productService.updateProduct(product, id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}
}
