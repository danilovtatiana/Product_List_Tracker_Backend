package com.product.listtracker.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stock")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "stock_id")
	@NotNull
	private Long stockId;
	
	@NotNull(message = "Quantity cannot be empty")
	@Column(name = "quantity", nullable = true)
	private Long quantity;
	
	@NotNull(message = "Price cannot be blank")
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
	@OneToOne(optional = false)	
	@JoinColumn(name = "product", referencedColumnName = "pzn")
	@NotNull(message = "Product cannot be blank")
	private Product product;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Stock() {}

	public Stock(
				Long quantity,
				BigDecimal price,
				Product product) {
		
		this.quantity = quantity;
		this.price = price;
		this.product = product;
	}
}
