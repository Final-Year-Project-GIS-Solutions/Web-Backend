package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.CheckoutEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CheckoutDao extends JpaRepository<CheckoutEntity, UUID> {

    @org.springframework.data.jpa.repository.Query("SELECT check From CheckoutEntity check")
    Page<CheckoutEntity> getCheckoutEntity(Pageable pageable);
}
