package com.backend.la.backendloveafrica.Services.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.entities.security.Permission;
import com.backend.la.backendloveafrica.repositories.security.IPermissionRepository;

@Service
public class PermissionServiceImp implements IPermissionService {

  @Autowired
  private IPermissionRepository permissionRepository;

  @Override
  public List<Permission> findAll() {
    return permissionRepository.findAll();
  }

  @Override
  public Optional<Permission> findById(Long id) {
    return permissionRepository.findById(id);
  }

  @Override
  public Permission save(Permission permission) {
    return permissionRepository.save(permission);
  }

  @Override
  public void deleteById(Long id) {
    permissionRepository.deleteById(id);
  }

  @Override
  public Permission update(Permission permission) {
    return this.save(permission);
  }

}
