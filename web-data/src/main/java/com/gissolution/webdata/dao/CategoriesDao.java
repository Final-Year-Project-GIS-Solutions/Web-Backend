package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.CategoriesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriesDao extends JpaRepository<CategoriesEntity, UUID> {

    CategoriesEntity getCategoryEntityById(UUID id);

    @org.springframework.data.jpa.repository.Query("SELECT cat FROM CategoriesEntity cat")
    Page<CategoriesEntity> getCategories(Pageable pageable);
}
