package com.backend.la.backendloveafrica.Services.security;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.security.Role;

public interface IRoleService {
  List<Role> findAll();

  Optional<Role> findById(Long id);

  Role save(Role role);

  void deleteById(Long id);

  Role update(Role role);
}
