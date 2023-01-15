package com.gissolution.webdata.dao;

import com.gissolution.webdata.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<UserEntity, UUID> {
    UserEntity getByKeycloakSubjectId(String keycloakSubjectId);

    UserEntity getByUsername(String username);
}
