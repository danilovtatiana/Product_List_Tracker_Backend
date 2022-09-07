package com.product.listtracker.service;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.product.listtracker.dao.StockRepository;
import com.product.listtracker.entities.Stock;


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
	
//	public Stock findStockById(Long stockId) {
//		return stockRepository.findStockById(stockId).orElseThrow(() -> new StockNotFoundException("Stock by ID " + stockId + "was not found" ));
//	
//	}
	
//	public void deleteStock(Long stockId) {
//		stockRepository.deleteById(stockId);
//	}
	
}
