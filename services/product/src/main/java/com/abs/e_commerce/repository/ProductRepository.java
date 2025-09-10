package com.abs.e_commerce.repository;

import java.util.List;

import com.abs.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdInOrderById(List<Long> productIds);

}
