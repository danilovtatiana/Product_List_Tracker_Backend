package com.product.listtracker.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.product.listtracker.dao.StockRepository;
import com.product.listtracker.dto.ProductDto;
import com.product.listtracker.dto.StockDto;
import com.product.listtracker.entities.Product;
import com.product.listtracker.entities.Stock;
import com.product.listtracker.exceptions.ProductNotFoundException;
import com.product.listtracker.exceptions.StockNotFoundException;


@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	
	public List<Stock> findStock(){
		return stockRepository.findAll();
	}
	
	
	public Stock updateStockById(Long id) {
		return stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException ("Stock by id " + id + "was not found" ));
		}
	
	public StockDto updateStock(StockDto stockDto) {
		
		Stock updatedStock = new Stock();
		BeanUtils.copyProperties(stockDto, updatedStock);
		
		updatedStock = stockRepository.save(updatedStock);
		
		stockDto = new StockDto(updatedStock);
	
		return stockDto;
	}
	
	public StockDto findStockByProductPzn(String pzn) {
		//nu e varianta corecta, dar e varianta pe care o stiu :)
		//facut cu query ca sa nu aducem toata lista de stock. 
		List<Stock> allStocks = stockRepository.findAll();
		Optional<Stock> foundedStock = allStocks.stream().filter(o -> o.getProduct().getPzn().equals(pzn)).findFirst();
		
		return new StockDto(foundedStock.orElseThrow(() -> new StockNotFoundException ("Stock with productPzn " + pzn + "was not found" )));
	}
	
	public Stock persistStock(StockDto stockDto) {
		Stock stock = new Stock();
		BeanUtils.copyProperties(stockDto, stock);
		return stockRepository.save(stock);
	}
	
	
//	public Stock addNewStock(Stock stock) {
//		return stockRepository.save(stock);
//	}
	
//	public Stock updateStock(Stock stock) {
//	return stockRepository.save(stock);
//}
	
//	public Stock findStockByProductPzn(String pzn) {
//		return stockRepository.findStockById(pzn).orElseThrow(() -> new ProductNotFoundException("Product by ID " + pzn + "was not found" ));
//				
//	}
	
//	public void deleteStock(Long stockId) {
//		stockRepository.deleteById(stockId);
//	}
	
}
