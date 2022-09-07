package com.product.listtracker.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.product.listtracker.dao.ProductRepository;
import com.product.listtracker.dao.StockRepository;
import com.product.listtracker.entities.Product;
import com.product.listtracker.entities.Stock;
import com.product.listtracker.exceptions.ProductNotFoundException;

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
	
	public Product addNewProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product addNewProductAndCreateStock(Product product) {
		Product savedProduct = productRepository.save(product);
		Stock newStock = new Stock(0L, new BigDecimal("0.0"), savedProduct);
		stockRepository.save(newStock);
		
		return savedProduct;
	}
	
	public List<Product> findAllProducts(){
		return productRepository.findAll();
	}
	
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product findProductByPzn(String pzn) {
		return productRepository.findProductByPzn(pzn).orElseThrow(() -> new ProductNotFoundException("Product by pzn " + pzn + "was not found" ));
	
	}
	
	public void deleteProduct(String pzn) {
		productRepository.deleteById(pzn);
	}

}
