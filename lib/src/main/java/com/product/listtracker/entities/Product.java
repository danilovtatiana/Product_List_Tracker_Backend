package com.product.listtracker.entities;

import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.product.listtracker.validators.PznConstraint;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name = "pzn", unique = true, nullable = false, length = 8)
	@Size(min = 8, max = 8)
	@NotNull
	private String pzn;

	@Column(name = "supplier", length = 100)
	@Size(max = 100)
	private String supplier;
	
	@NotBlank (message = "Product Name cannot be blank!")
	@Column(name = "product_name", length = 100, nullable = false)
	@Size(max = 100)
	private String productName;
	
	@NotBlank (message = "Strength  cannot be blank!")
	@Column(name = "strength", length = 100)
	@Size(max = 100)
	private String strength;

	@Column(name = "package_size", length = 20)
	@Size(max = 20)
	@NotEmpty(message = "Package Size cannot be empty!")
	private String packageSize;

	@Column(name = "unit", length = 2)
	@Size(max = 2)
	@NotEmpty(message = "Unit cannot be empty!")
	private String unit;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
	private Stock stock;

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

	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


}

