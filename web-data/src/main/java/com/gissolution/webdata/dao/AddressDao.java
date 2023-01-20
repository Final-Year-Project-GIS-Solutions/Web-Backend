package com.gissolution.webdata.dao;

import java.util.UUID;

import com.gissolution.webdata.entity.AddressEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends JpaRepository<AddressEntity, UUID>{
    AddressEntity getAddressById(UUID addressId);

    @org.springframework.data.jpa.repository.Query("SELECT add FROM AddressEntity add where add.userId = :userId")
    Page<AddressEntity> getAddressByUser(@Param("userId") String userId, Pageable pageable);

    @org.springframework.data.jpa.repository.Query("SELECT add FROM AddressEntity add")
    Page<AddressEntity> getAddress(Pageable pageable);
}
