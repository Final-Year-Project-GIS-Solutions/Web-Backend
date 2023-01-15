package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.UUID;

@Repository
public interface ImagesDao extends JpaRepository<ImageEntity, UUID> {
    Page<ImageEntity> getImagesById(UUID id, Pageable pageable);

    @org.springframework.data.jpa.repository.Query(value = "SELECT img FROM ImageEntity img where img.userId.userId = :userId")
    Page<ImageEntity> getByUserId(@Param("userId") String userId, Pageable pageable);
}