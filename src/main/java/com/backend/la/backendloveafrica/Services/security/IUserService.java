package com.backend.la.backendloveafrica.Services.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.backend.la.backendloveafrica.models.dtos.security.ChangePasswordRequest;
import com.backend.la.backendloveafrica.models.entities.security.UserSec;

public interface IUserService {

  List<UserSec> findAll();

  Optional<UserSec> findById(Long id);

  UserSec save(UserSec userSec);

  void deleteById(Long id);

  UserSec update(UserSec userSec);

  String encryptPassword(String password);

  String getUsernameFromAuthenticatedUser();

  boolean hasAnyRole(String role);

  boolean sameUsername(String username);

  void changePassword(String username, ChangePasswordRequest changePasswordRequest);

  // String encryptPassword(String password);
}
