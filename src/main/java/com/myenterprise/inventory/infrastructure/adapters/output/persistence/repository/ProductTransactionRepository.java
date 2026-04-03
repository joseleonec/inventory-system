package com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myenterprise.inventory.infrastructure.adapters.output.persistence.entities.ProductTransactionEntity;

public interface ProductTransactionRepository extends JpaRepository<ProductTransactionEntity, Long> {

}
