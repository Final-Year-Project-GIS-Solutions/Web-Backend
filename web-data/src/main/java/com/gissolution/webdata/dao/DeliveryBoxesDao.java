package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.DeliveryBoxesEntity;
import com.gissolution.webdata.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryBoxesDao extends JpaRepository<DeliveryBoxesEntity, UUID> {

    @org.springframework.data.jpa.repository.Query("SELECT prod FROM DeliveryBoxesEntity prod where prod.boxesId = :boxId")
    DeliveryBoxesEntity getDeliveryBoxesById(@Param("boxId") UUID boxesId);

    @org.springframework.data.jpa.repository.Query("SELECT prod FROM DeliveryBoxesEntity prod")
    Page<DeliveryBoxesEntity> getBoxes(Pageable pageable);
}
