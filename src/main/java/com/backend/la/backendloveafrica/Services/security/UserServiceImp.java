package com.backend.la.backendloveafrica.Services.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.dtos.security.ChangePasswordRequest;
import com.backend.la.backendloveafrica.models.entities.security.UserSec;
import com.backend.la.backendloveafrica.repositories.security.IUserRepository;

@Service
public class UserServiceImp implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public List<UserSec> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<UserSec> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public UserSec save(UserSec userSec) {
    return userRepository.save(userSec);
  }

  @Override
  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public UserSec update(UserSec userSec) {
    return this.save(userSec);
  }

  @Override
  public String encryptPassword(String password) {
    return new BCryptPasswordEncoder().encode(password);
  }

  @Override
  public String getUsernameFromAuthenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.getPrincipal() instanceof String) {
      // System.out.println("AUTHENTICATION.GETPRINCIPAL == USERNAME");
      System.out.println(authentication.getPrincipal());
      return (String) authentication.getPrincipal();
    }
    if (authentication.getPrincipal() instanceof UserDetails) {
      return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    return "No se encontró";
  }

  @Override
  public boolean hasAnyRole(String role) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // boolean hasAnyRole =
    return authentication.getAuthorities().stream()
        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
  }

  @Override
  public boolean sameUsername(String username) {
    return this.getUsernameFromAuthenticatedUser().equals(username);
  }

  @Override
  public void changePassword(String username, ChangePasswordRequest changePasswordRequest) {

    UserSec user = userRepository.findUserEntityByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

    System.out.println("USER PASSWORD");
    System.out.println(user.getPassword());
    System.out.println("PASSWORD PASSWORD");
    System.out.println(changePasswordRequest.getPassword());

    // if (!passwordEncoder.matches(user.getPassword(),
    // changePasswordRequest.getPassword())) {
    if (!passwordEncoder.matches(changePasswordRequest.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("Incorrect password");
    }

    if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword())) {
      throw new IllegalArgumentException("New password and confirmation do not match");
    }

    user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
    userRepository.save(user);
  }

}
