package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.ProductEntity;
import com.gissolution.webdata.entity.VehiclesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehiclesDao extends JpaRepository<VehiclesEntity, UUID> {

    @org.springframework.data.jpa.repository.Query("SELECT prod FROM VehiclesEntity prod where prod.vehicleId = :vehId")
    VehiclesEntity getVehiclesEntityById(@Param("vehId") UUID vehicleId);

    @org.springframework.data.jpa.repository.Query("SELECT prod FROM VehiclesEntity prod")
    Page<VehiclesEntity> getVehicles(Pageable pageable);
}
