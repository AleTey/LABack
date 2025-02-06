package com.backend.la.backendloveafrica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.IWorkShopService;
import com.backend.la.backendloveafrica.models.entities.WorkShop;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/workshops")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class WorkShopController {

  @Autowired
  private IWorkShopService workShopService;

  @GetMapping
  public ResponseEntity<List<WorkShop>> findAllWorkShops() {
    return ResponseEntity.ok(workShopService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<WorkShop> findWorkShopById(@PathVariable Long id) {
    Optional<WorkShop> o = workShopService.findById(id);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<WorkShop> saveWorkShop(@RequestBody @Valid WorkShop workShop) {
    return ResponseEntity.ok(workShopService.save(workShop));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteWorkShopById(@PathVariable Long id) {
    workShopService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<WorkShop> updateWorkShop(@RequestBody @Valid WorkShop workShop) {
    Optional<WorkShop> o = workShopService.update(workShop);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

}
