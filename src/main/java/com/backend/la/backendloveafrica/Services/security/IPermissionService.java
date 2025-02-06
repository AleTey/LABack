package com.backend.la.backendloveafrica.Services.security;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.security.Permission;

public interface IPermissionService {

  List<Permission> findAll();

  Optional<Permission> findById(Long id);

  Permission save(Permission permission);

  void deleteById(Long id);

  Permission update(Permission permission);

}
