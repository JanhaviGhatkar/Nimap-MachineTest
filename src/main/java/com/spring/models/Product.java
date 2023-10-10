package com.spring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	@Column(nullable = false)
	@Size(min = 4, message = "Minimum 4 characters are requried")
	private String productName;
	
	@Column(nullable = false)
	@Min(value = 50, message = "Minimum product price sholud be 50")
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "Category_Id", referencedColumnName ="categoryId" )
	private Category category;

	
}
