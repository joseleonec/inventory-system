package com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myenterprise.inventory.infrastructure.adapters.output.persistence.entities.ProductTransactionEntity;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransactionEntity, Long> {

    List<ProductTransactionEntity> findByProductId(Long productId);

}
