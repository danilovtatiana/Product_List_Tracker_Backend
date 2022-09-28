package com.product.listtracker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


import com.product.listtracker.dto.ProductDto;
import com.product.listtracker.entities.Product;
import com.product.listtracker.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	
	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		return new ResponseEntity<> (productService.findAllProducts().stream().map(product -> new ProductDto(product)).collect(Collectors.toList()), HttpStatus.OK);
		
//		List<Product> products = productService.findAllProducts();
//		return  new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/{pzn}")
	public ResponseEntity <ProductDto> getProductByPzn(@PathVariable("pzn") String pzn) throws Exception {
		Product product = productService.findProductByPzn(pzn);
		ProductDto productDto = new ProductDto(product);
		return  new ResponseEntity<>(productDto, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto)  throws Exception {
		ProductDto newProduct = productService.addNewProductAndCreateStock(productDto);
		
		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){
		ProductDto updateProduct = productService.updateProduct(productDto);
		return new ResponseEntity<>(updateProduct, HttpStatus.OK);
	}
	

	@DeleteMapping(value = "/delete/{pzn}")
	public void deleteProduct(@PathVariable(name = "pzn") String pzn) {
		productService.deleteProduct(pzn);
	}

//	@GetMapping("/all")
//	public ResponseEntity<List<Product>> getAllProducts(){
//		List<Product> products = productService.findAllProducts();
//		return  new ResponseEntity<>(products, HttpStatus.OK);
//	}
	
	
//	@GetMapping("/find/{pzn}")
//	public ResponseEntity <Product> getProductByPzn(@PathVariable("pzn") String pzn){
//		Product product = productService.findProductByPzn(pzn);
//		return  new ResponseEntity<>(product, HttpStatus.OK);
//	}
	
//	@PostMapping("/add")
//	public ResponseEntity<Product> addProduct(@RequestBody Product product){
//		Product newProduct = productService.addNewProductAndCreateStock(product);
//		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
//	}
	
//	@PutMapping("/update")
//	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
//		Product updateProduct = productService.updateProduct(product);
//		return new ResponseEntity<>(updateProduct, HttpStatus.OK);
//	}
	

}
