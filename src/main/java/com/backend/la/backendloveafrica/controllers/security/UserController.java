package com.backend.la.backendloveafrica.controllers.security;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.security.IRoleService;
import com.backend.la.backendloveafrica.Services.security.IUserService;
import com.backend.la.backendloveafrica.models.dtos.security.ChangePasswordRequest;
import com.backend.la.backendloveafrica.models.entities.security.Role;
import com.backend.la.backendloveafrica.models.entities.security.UserSec;

@RestController
@RequestMapping("/users")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class UserController {

  @Autowired
  private IUserService userService;

  @Autowired
  private IRoleService roleService;

  @GetMapping
  public ResponseEntity<List<UserSec>> findAllUsers() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserSec> findUserById(@PathVariable Long id) {
    Optional<UserSec> oUser = userService.findById(id);

    return oUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<UserSec> createUser(@RequestBody UserSec userSec) {
    Set<Role> rolesList = new HashSet<>();
    Role readRole;

    userSec.setPassword(userService.encryptPassword(userSec.getPassword()));

    for (Role r : userSec.getRolesList()) {
      readRole = roleService.findById(r.getId()).orElse(null);
      if (readRole != null) {
        rolesList.add(readRole);
      }
    }

    // if (!rolesList.isEmpty()) {
    userSec.setRolesList(rolesList);
    UserSec newUser = userService.save(userSec);
    return ResponseEntity.ok(newUser);
  }

  @PutMapping
  public ResponseEntity<UserSec> updateUser(@RequestBody UserSec userSec, Principal principal) {

    Optional<UserSec> oUser = userService.findById(userSec.getId());
    if (oUser.isPresent()) {
      Set<Role> rolesList = new HashSet<>();
      Role readRole;

      for (Role r : userSec.getRolesList()) {
        readRole = roleService.findById(r.getId()).orElse(null);
        if (readRole != null) {
          rolesList.add(readRole);
        }
      }

      if (!rolesList.isEmpty()) {
        UserSec userDb = oUser.get();
        System.out.println(userDb.toString());
        userDb.setRolesList(rolesList);
        return ResponseEntity.ok(userService.save(userDb));
      }
      return ResponseEntity.badRequest().build();

    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/getUsername")
  public ResponseEntity<String> getUsernameFromContext() {
    return ResponseEntity.ok(userService.getUsernameFromAuthenticatedUser());
  }

  @PutMapping("/change-pass")
  public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
      Principal principal) {
    try {
      System.out.println("PRINCIPAL.GETNAME");
      System.out.println(principal.getName());
      userService.changePassword(principal.getName(), changePasswordRequest);
      return ResponseEntity.ok("Password changed successfully");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
