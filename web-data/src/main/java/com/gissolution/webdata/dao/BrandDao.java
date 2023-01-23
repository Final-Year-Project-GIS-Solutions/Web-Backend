package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BrandDao extends JpaRepository<BrandEntity, UUID> {
    BrandEntity getBrandByBrandId(UUID brandId);

    @org.springframework.data.jpa.repository.Query(value = "SELECT brd from BrandEntity brd")
    Page<BrandEntity> getBrand(Pageable pageable);

}
