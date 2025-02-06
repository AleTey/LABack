package com.backend.la.backendloveafrica.controllers.security;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
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

import com.backend.la.backendloveafrica.Services.security.IPermissionService;
import com.backend.la.backendloveafrica.Services.security.IRoleService;
import com.backend.la.backendloveafrica.models.entities.security.Permission;
import com.backend.la.backendloveafrica.models.entities.security.Role;

@RestController
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
@RequestMapping("/roles")
public class RoleController {

  @Autowired
  private IRoleService roleService;

  @Autowired
  private IPermissionService permissionService;

  @GetMapping
  public ResponseEntity<List<Role>> findAllRoles() {
    return ResponseEntity.ok(roleService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Role> findRoleById(@PathVariable Long id) {
    Optional<Role> oRole = roleService.findById(id);

    return oRole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Role> createRole(@RequestBody Role role) {
    Set<Permission> permissionsList = new HashSet<>();
    Permission readPermission;

    for (Permission p : role.getPermissionsList()) {
      readPermission = permissionService.findById(p.getId()).orElse(null);
      if (readPermission != null) {
        permissionsList.add(readPermission);
      }
    }

    role.setPermissionsList(permissionsList);
    Role newRole = roleService.save(role);

    return ResponseEntity.ok(newRole);
  }

  @PutMapping("/add-one/{id}")
  public ResponseEntity<Role> addPermission(@PathVariable Long id, @RequestBody Permission permission) {
    Optional<Role> oRole = roleService.findById(id);
    Optional<Permission> oPermission = permissionService.findById(permission.getId());
    if (oRole.isPresent() && oPermission.isPresent()) {
      Role role = oRole.get();
      Set<Permission> permissions = new HashSet<>(role.getPermissionsList());
      permissions.add(oPermission.get());
      role.setPermissionsList(permissions);
      return ResponseEntity.ok(roleService.save(role));
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/remove-one/{id}")
  public ResponseEntity<Role> removePermission(@PathVariable Long id, @RequestBody Permission permission) {
    Optional<Role> oRole = roleService.findById(id);
    Optional<Permission> oPermission = permissionService.findById(permission.getId());
    if (oRole.isPresent() && oPermission.isPresent()) {
      Role role = oRole.get();
      Set<Permission> permissions = role.getPermissionsList().stream()
          .filter(p -> p.getId() != permission.getId())
          .collect(Collectors.toSet());
      role.setPermissionsList(permissions);
      return ResponseEntity.ok(roleService.save(role));
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping
  public ResponseEntity<Role> updateRole(@RequestBody Role role) {
    Optional<Role> oRole = roleService.findById(role.getId());
    if (oRole.isPresent()) {
      Set<Permission> permissionsList = new HashSet<>();
      Permission readPermission;

      for (Permission p : role.getPermissionsList()) {
        readPermission = permissionService.findById(p.getId()).orElse(null);
        if (readPermission != null) {
          permissionsList.add(readPermission);
        }
      }

      Role roleDb = oRole.get();
      roleDb.setPermissionsList(permissionsList);

      return ResponseEntity.ok(roleService.save(roleDb));
    }
    return ResponseEntity.notFound().build();
  }

}
