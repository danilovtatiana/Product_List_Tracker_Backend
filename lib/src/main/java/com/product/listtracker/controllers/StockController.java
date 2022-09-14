package com.product.listtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.listtracker.dto.ProductDto;
import com.product.listtracker.dto.StockDto;
import com.product.listtracker.entities.Product;
import com.product.listtracker.entities.Stock;
import com.product.listtracker.service.AccountService;
import com.product.listtracker.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;

	
	
//	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Stock> updateStockById(@PathVariable("id") Long id, @RequestBody StockDto stockDto ){
//		
//		return new ResponseEntity<>(stockService.persistStock(stockDto), HttpStatus.OK);
//	}
	
	@PutMapping("/update") //save la modificari 
	public ResponseEntity<StockDto> updateStock(@RequestBody StockDto stockDto){
		StockDto updateStock = stockService.updateStock(stockDto);
		return new ResponseEntity<>(updateStock, HttpStatus.OK);
	}
	
	@GetMapping("/productPzn/{pzn}")
	public ResponseEntity<StockDto> getStockByProductPzn(@PathVariable("pzn") String pzn) {
		StockDto stock = stockService.findStockByProductPzn(pzn);
		return  new ResponseEntity<>(stock, HttpStatus.OK);
	}
	
//	@GetMapping("/{pzn}")
//	public ResponseEntity <ProductDto> getProductByPzn(@PathVariable("pzn") String pzn){
//		Product product = productService.findProductByPzn(pzn);
//		ProductDto productDto = new ProductDto(product);
//		return  new ResponseEntity<>(productDto, HttpStatus.OK);
//	}
	
	
	
	@GetMapping
	public ResponseEntity<List<Stock>> getStock(){
		List<Stock> stock = stockService.findStock();
		return  new ResponseEntity<>(stock, HttpStatus.OK);
	}
	
//	@GetMapping("/find/{pzn}")
//	public ResponseEntity <Stock> getStocktByProductPzn(@PathVariable("pzn") String pzn){
//		Stock stock = stockService.findStockByProductPzn(pzn);
//		return  new ResponseEntity<>(stock, HttpStatus.OK);
//	}
	
//	@PutMapping("/update")
//	public ResponseEntity<Stock> updateStock(@RequestBody Stock stock){
//		Stock updateStock = stockService.updateStock(stock);
//		return new ResponseEntity<>(updateStock, HttpStatus.OK);
//	}
	
	


}
