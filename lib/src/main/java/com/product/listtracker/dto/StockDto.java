package com.product.listtracker.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.product.listtracker.entities.Product;
import com.product.listtracker.entities.Stock;

public class StockDto {
	private Long stockId;
	private Long quantity;
	private BigDecimal price;
	private Product product;
	
	public StockDto() {
		
	}
	
	public StockDto(Stock stock) {
		BeanUtils.copyProperties(stock, this);
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

//	public String getPzn() {
//		return pzn;
//	}
//
//	public void setPzn(String pzn) {
//		this.pzn = pzn;
//	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	

}
