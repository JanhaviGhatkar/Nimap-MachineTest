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
import com.spring.models.Product;
import com.spring.repositories.CategoryRepository;
import com.spring.repositories.ProductRepository;
import com.spring.responsewrappers.AllResponseWrapper;


@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	AllResponseWrapper rw = new AllResponseWrapper();
	
	//fetch all products
//	public ResponseEntity<?> getAllProducts(){
//		Iterable<Product> data = productRepository.findAll();
//		Iterator<Product> allProduct = data.iterator();
//		if(allProduct.hasNext()) {
//			rw.setMessage("Following products are found");
//			rw.setData(allProduct);
//			return new ResponseEntity<>(rw, HttpStatus.FOUND);
//		}
//		else 
//		{
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There are no ptoduct found. \nPlease add few products");
//		}
//	}
	
	public Page<Product> getAllProducts(int page,int pagesize){
		Pageable pageable = PageRequest.of(page -1, pagesize);
		return productRepository.findAll(pageable);
	}
	
	//Add new product
//	public ResponseEntity<?> addNewProduct (Product product){
//		Product newProduct = productRepository.save(product);
//		if (newProduct == null) {
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There is some proble in server");
//		} else {
//			rw.setMessage("Following product is added");
//			rw.setData(newProduct);
//			return new ResponseEntity<>(rw, HttpStatus.FOUND);
//		}
//	}
	public ResponseEntity<?> addNewProduct (Product product){
		String founded_name= product.getCategory().getName();
		Category category= categoryRepository.findByName(founded_name).orElseThrow(
				()->
				{throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no category with name " + founded_name);}
				);
		product.setCategory(category);
		Product newProduct = productRepository.save(product);
		if (newProduct == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There is some proble in server");
		} 
		else {
			rw.setMessage("Following product is added");
			rw.setData(newProduct);
			return new ResponseEntity<>(rw, HttpStatus.FOUND);
		}
		
	}
	
	//Fetch specific product by its id
	public ResponseEntity<?> getProductById(int id) {
		Product foundProductById = productRepository.findById(id).orElseThrow(
				() -> {
			             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This " + id + " id is not exist");
			          }
				);
		rw.setMessage("Following product is added");
		rw.setData(foundProductById);
		return new ResponseEntity<>(rw, HttpStatus.FOUND);
	}
	
	//Update specific product by its id
	public ResponseEntity<?> updateProduct(Product product, int id) {
		getProductById(id);
		product.setProductId(id);
		String founded_name= product.getCategory().getName();
		Category category= categoryRepository.findByName(founded_name).orElseThrow(
				()->
				{throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no category with name " + founded_name);}
				);
		 product.setCategory(category);
		 Product updatedProduct = productRepository.save(product);
		 rw.setMessage("Product updated \nUpdated product: ");
		 rw.setData(updatedProduct);
		 return new ResponseEntity<>(rw, HttpStatus.OK);	 
	}
	
	public ResponseEntity<?>deleteProduct(int id) {
		 getProductById(id);
		 productRepository.deleteById(id);	
			rw.setMessage("Product with id " +id+ " is deleted");
			rw.setData(null);
			return new ResponseEntity<>(rw, HttpStatus.OK);	 
	}
	
	public ResponseEntity<?>addCategory(int productId, Category category){
		return null;	
	}
}
