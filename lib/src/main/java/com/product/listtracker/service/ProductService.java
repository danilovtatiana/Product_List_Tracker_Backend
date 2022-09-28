package com.product.listtracker.service;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.product.listtracker.dao.ProductRepository;
import com.product.listtracker.dao.StockRepository;
import com.product.listtracker.dto.ProductDto;
import com.product.listtracker.entities.Product;
import com.product.listtracker.entities.Stock;
import com.product.listtracker.exceptions.ProductNotFoundException;
import com.product.listtracker.exceptions.PznAlreadyExistsException;

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StockRepository stockRepository;

	@PostConstruct
	private void populateProductList() {
		try {
			if (productRepository.count() == 0) {
				Resource resource = new ClassPathResource("products.csv");
				CsvSchema schema = CsvSchema.emptySchema().withHeader();
				CsvMapper mapper = new CsvMapper();
				MappingIterator<Product> iterator = mapper.readerFor(Product.class).with(schema)
						.readValues(resource.getInputStream());
				productRepository.saveAll(iterator.readAll());
			}
		} catch (IOException e) {
			logger.error("An error occurred while populating products.", e);
		}
	}
	
//	private void updateStock() {
//		List<Product> allProducts = productRepository.findAll();
//		List<Stock> allStock = stockRepository.findAll();
//		for (int i=0; i< allProducts.size(); i++) 
//		{ 
//		    Product productFromDB = allProducts.get(i);
//		    //check if exists stock with this product
//		    if (!allStock.stream().filter(o -> o.getProduct().getPzn().equals(productFromDB.getPzn())).findFirst().isPresent()) {
//		    	//add new stock with productFromDB like product model. 
//		    	Stock newStock = new Stock(0L, new BigDecimal("0.0"), productFromDB);
//				stockRepository.save(newStock);
//		    }
//		    
//		} 
//	}
	
	public Product addNewProduct(Product product) {
		return productRepository.save(product);
	}
	
	public ProductDto addNewProductAndCreateStock(ProductDto productDto) throws Exception {
		
		Product savedProduct = new Product();
		BeanUtils.copyProperties(productDto, savedProduct);
		
		if (productRepository.existsById(productDto.getPzn())) {
			throw new PznAlreadyExistsException(" " + productDto.getPzn() + " ");
		}
		
		savedProduct = productRepository.save(savedProduct);
		Stock newStock = new Stock(0L, new BigDecimal("0.0"), savedProduct);
		stockRepository.save(newStock);
		
		productDto = new ProductDto(savedProduct);
		return productDto;
	}
	
	public List<Product> findAllProducts(){
		return productRepository.findAll();
	}
	
	public ProductDto updateProduct(ProductDto productDto) {
		
		Product updatedProduct = new Product();
		BeanUtils.copyProperties(productDto, updatedProduct);
		
		updatedProduct = productRepository.save(updatedProduct);
		
		productDto = new ProductDto(updatedProduct);
	
		return productDto;
	}
	
	
	public Product findProductByPzn(String pzn) throws ProductNotFoundException {
		return productRepository.findProductByPzn(pzn).orElseThrow(() -> new ProductNotFoundException("Product by pzn " + pzn + "was not found" ));
	
	}
	
	public void deleteProduct(String pzn) {
		productRepository.deleteById(pzn);
	}

}
