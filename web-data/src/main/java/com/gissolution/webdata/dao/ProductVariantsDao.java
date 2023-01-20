package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.ProductVariantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductVariantsDao extends JpaRepository<ProductVariantsEntity, UUID> {

    ProductVariantsEntity getProductVariantsEntityById(UUID productVariantsId);
}
