package com.backend.la.backendloveafrica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.IWarehouseService;
import com.backend.la.backendloveafrica.models.entities.Warehouse;

@RestController
@RequestMapping("/warehouse")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class WarehouseController {

  @Autowired
  private IWarehouseService warehouseService;

  @GetMapping
  public ResponseEntity<List<Warehouse>> findAllWarehouses() {
    return ResponseEntity.ok(warehouseService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Warehouse> findById(@PathVariable Long id) {
    Optional<Warehouse> o = warehouseService.findById(id);

    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping
  public ResponseEntity<Warehouse> update(@RequestBody Warehouse warehouse) {
    Optional<Warehouse> o = warehouseService.findById(warehouse.getId());
    if (o.isPresent()) {
      return ResponseEntity.ok(warehouseService.update(warehouse).get());
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/page/search")
  public ResponseEntity<Page<Warehouse>> findWarehousePageByString(@RequestParam String string, @RequestParam int page,
      @RequestParam int size) {
    return ResponseEntity.ok(warehouseService.findPageByString(string, page, size));
  }

}
