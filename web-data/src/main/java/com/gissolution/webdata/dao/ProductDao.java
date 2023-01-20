package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, UUID> {

    ProductEntity getProductEntityById(UUID productId);

    @org.springframework.data.jpa.repository.Query("SELECT prod FROM ProductEntity prod")
    Page<ProductEntity> getProducts(Pageable pageable);
}
