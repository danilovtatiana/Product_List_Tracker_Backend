package com.product.listtracker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.listtracker.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{


	Optional<Product> findProductByPzn(String pzn);

}
