package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.ProductsDemoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDemoDao extends JpaRepository<ProductsDemoEntity, UUID> {

    @org.springframework.data.jpa.repository.Query("SELECT prod From ProductsDemoEntity prod")
    Page<ProductsDemoEntity> getProducts(Pageable pageable);
}
