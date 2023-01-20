package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.AddressDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressDetailDao extends JpaRepository<AddressDetailEntity, UUID> {
    AddressDetailEntity getAddressDetailEntityByAddressDetailId(UUID addressDetailId);
}
