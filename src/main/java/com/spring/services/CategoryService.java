package com.spring.services;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.spring.models.Category;
import com.spring.repositories.CategoryRepository;
import com.spring.responsewrappers.AllResponseWrapper;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	AllResponseWrapper rw = new AllResponseWrapper();
	//fetch all products
//	public ResponseEntity<?> getAllCategory(){
//		Iterable<Category> data = categoryRepository.findAll();
//		Iterator<Category> allProduct = data.iterator();
//		if(allProduct.hasNext()) {
//			rw.setMessage("Following products are found");
//			rw.setData(allProduct);
//			return new ResponseEntity<>(rw, HttpStatus.FOUND);
//		}
//		else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There are no ptoduct found. \n Please add few products");
//		}
//	}
	public Page<Category> getAllCategory( int page,int pagesize){
		Pageable pageable = PageRequest.of(page -1 , pagesize);
	    return categoryRepository.findAll(pageable);
	}
	
	//Add new product
	public ResponseEntity<?> addNewCategory (Category category){
		Category newProduct = categoryRepository.save(category);
		if (newProduct == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There is some proble in server");
		} else {
			rw.setMessage("Following product is added");
			rw.setData(newProduct);
			return new ResponseEntity<>(rw, HttpStatus.FOUND);
		}
	}
	
	//Fetch specific product by its id
	public ResponseEntity<?> getCategoryById(int id) {
		Category foundProductById = categoryRepository.findById(id).orElseThrow(
				() -> {
			             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This " + id + " id is not exist");
			          }
				);
		rw.setMessage("Following product is added");
		rw.setData(foundProductById);
		return new ResponseEntity<>(rw, HttpStatus.FOUND);
	}
	
	//Update specific product by its id
	public ResponseEntity<?> updateCategory(Category category, int id) {
		Category foundCategory = categoryRepository.findById(id).orElseThrow(
				  ()-> {
					     throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no product with id " + id);
				       }
				  );
		category.setCategoryId(foundCategory.getCategoryId()); 
		 Category updatedProduct = categoryRepository.save(category);
		 rw.setMessage("Product updated \nUpdated product: ");
		 rw.setData(updatedProduct);
		 return new ResponseEntity<>(rw, HttpStatus.OK);	 
	}
	
	public ResponseEntity<?>deleteCategory(int id) {
		getCategoryById(id);
		 categoryRepository.deleteById(id);	
			rw.setMessage("Product with id " +id+ " is deleted");
			rw.setData(null);
			return new ResponseEntity<>(rw, HttpStatus.OK);	 
	}
}
