package com.product.listtracker.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.product.listtracker.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

//	Optional<Stock> findStockById(String pzn);

}
