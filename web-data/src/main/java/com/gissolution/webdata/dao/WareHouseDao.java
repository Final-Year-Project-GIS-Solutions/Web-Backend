package com.gissolution.webdata.dao;


import com.gissolution.webdata.entity.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WareHouseDao extends JpaRepository<WarehouseEntity, UUID> {
    WarehouseEntity getWareHouseEntityById(UUID warehouseId);

    @org.springframework.data.jpa.repository.Query(value = "SELECT war from WarehouseEntity war where war.userId = :userId")
    Page<WarehouseEntity> getWareHouseEntityByUser(@Param("userId") String userId, Pageable pageable);

    @org.springframework.data.jpa.repository.Query("SELECT war FROM WarehouseEntity war")
    Page<WarehouseEntity> getWareHouseEntity(Pageable pageable);


}
