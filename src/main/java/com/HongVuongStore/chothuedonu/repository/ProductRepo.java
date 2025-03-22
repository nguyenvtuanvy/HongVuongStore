package com.HongVuongStore.chothuedonu.repository;

import com.HongVuongStore.chothuedonu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    Set<Product> findAllProductsByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p")
    Set<Product> findAllProducts();
}
