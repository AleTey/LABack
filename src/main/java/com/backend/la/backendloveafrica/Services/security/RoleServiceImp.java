package com.backend.la.backendloveafrica.Services.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.entities.security.Role;
import com.backend.la.backendloveafrica.repositories.security.IRoleRepository;

@Service
public class RoleServiceImp implements IRoleService {

  @Autowired
  private IRoleRepository roleRepository;

  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  @Override
  public Optional<Role> findById(Long id) {
    return roleRepository.findById(id);
  }

  @Override
  public Role save(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public void deleteById(Long id) {
    roleRepository.deleteById(id);
  }

  @Override
  public Role update(Role role) {
    return this.save(role);
  }

}
