package com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myenterprise.inventory.infrastructure.adapters.output.persistence.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
