package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.ProductBenefitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductBenefitsDao extends JpaRepository<ProductBenefitsEntity, UUID> {

    ProductBenefitsEntity getProductBenefitsEntityById(UUID productBenefitsId);
}
