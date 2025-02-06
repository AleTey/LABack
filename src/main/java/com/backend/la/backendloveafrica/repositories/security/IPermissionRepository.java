package com.backend.la.backendloveafrica.repositories.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.la.backendloveafrica.models.entities.security.Permission;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {

}
