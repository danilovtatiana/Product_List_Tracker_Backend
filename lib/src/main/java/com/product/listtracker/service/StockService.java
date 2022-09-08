package com.product.listtracker.service;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.product.listtracker.dao.StockRepository;
import com.product.listtracker.entities.Stock;
import com.product.listtracker.exceptions.ProductNotFoundException;
import com.product.listtracker.exceptions.StockNotFoundException;


@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	
//	public Stock addNewStock(Stock stock) {
//		return stockRepository.save(stock);
//	}
	
	public List<Stock> findStock(){
		return stockRepository.findAll();
	}
	
	public Stock updateStock(Stock stock) {
		return stockRepository.save(stock);
	}
	
//	public Stock findStockByProductPzn(String pzn) {
//		return stockRepository.findStockById(pzn).orElseThrow(() -> new ProductNotFoundException("Product by ID " + pzn + "was not found" ));
//				
//	}
	
//	public void deleteStock(Long stockId) {
//		stockRepository.deleteById(stockId);
//	}
	
}
