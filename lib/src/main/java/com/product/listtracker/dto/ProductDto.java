package com.product.listtracker.dto;

import org.springframework.beans.BeanUtils;

import com.product.listtracker.entities.Product;

public class ProductDto {
	private String pzn;
	private String supplier;
	private String productName;
	private String strength;
	private String packageSize;
	private String unit;
	
	public ProductDto() {
		
	}
	
	public ProductDto(Product product) {
		BeanUtils.copyProperties(product, this);
	}

	public String getPzn() {
		return pzn;
	}

	public void setPzn(String pzn) {
		this.pzn = pzn;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(String size) {
		this.packageSize = size;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
	
}
