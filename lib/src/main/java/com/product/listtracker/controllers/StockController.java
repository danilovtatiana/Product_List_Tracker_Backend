package com.product.listtracker.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.product.listtracker.entities.Product;
import com.product.listtracker.entities.Stock;
import com.product.listtracker.service.AccountService;
import com.product.listtracker.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@GetMapping
	public ResponseEntity<List<Stock>> getStock(){
		List<Stock> stock = stockService.findStock();
		return  new ResponseEntity<>(stock, HttpStatus.OK);
	}
	
//	@GetMapping("/stock/{stockId}")
//	public ResponseEntity <Stock> getStocktById(@PathVariable("stockId") Long stockId){
//		Stock stock = stockService.findStockById(stockId);
//		return  new ResponseEntity<>(stock, HttpStatus.OK);
//	}
//	
	@PostMapping("/add")
	public ResponseEntity<Stock> addStock(@RequestBody Stock stock){
		Stock newStock = stockService.addNewStock(stock);
		return new ResponseEntity<>(newStock, HttpStatus.CREATED);
	}
//	
//	@PutMapping("/update")
//	public ResponseEntity<Stock> updateStock(@RequestBody Stock stock){
//		Stock updateStock = stockService.updateStock(stock);
//		return new ResponseEntity<>(updateStock, HttpStatus.OK);
//	}
//	
//
//	@DeleteMapping(value = "/delete/{stockId}")
//	public void deleteStock(@PathVariable(name = "stockId") Long stockId) {
//		stockService.deleteStock(stockId);
//	}
}
