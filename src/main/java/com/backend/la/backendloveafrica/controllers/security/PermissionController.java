package com.backend.la.backendloveafrica.controllers.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.security.IPermissionService;
import com.backend.la.backendloveafrica.models.entities.security.Permission;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/permissions")
// @CrossOrigin(origins = {"https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class PermissionController {

  @Autowired
  private IPermissionService permissionService;

  @GetMapping
  public ResponseEntity<List<Permission>> findAllPermissions() {
    return ResponseEntity.ok(permissionService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Permission> findPermissionById(@PathVariable Long id) {
    Optional<Permission> oPermission = permissionService.findById(id);

    return oPermission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
    return ResponseEntity.ok(permissionService.save(permission));
  }

  @PostMapping("/array")
  public ResponseEntity<List<Permission>> createListPermission(@RequestBody List<Permission> permissionsList) {
    List<Permission> permissionsDb = permissionsList.stream()
        .map(permissionService::save)
        .collect(Collectors.toList());

    return ResponseEntity.ok(permissionsDb);
  }

  @PutMapping("path/{id}")
  public ResponseEntity<Permission> updatePermission(@PathVariable String id, @RequestBody Permission permission) {
    Optional<Permission> o = permissionService.findById(permission.getId());
    if (o.isPresent()) {
      Permission permissionDb = o.get();
      permissionDb.setPermissionName(permission.getPermissionName());
      return ResponseEntity.ok(permissionService.save(permissionDb));
    }

    return ResponseEntity.notFound().build();
  }

}
