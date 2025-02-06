package com.backend.la.backendloveafrica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.backend.la.backendloveafrica.Services.ISupplierService;
import com.backend.la.backendloveafrica.mapper.SupplierMapper;
import com.backend.la.backendloveafrica.models.dtos.supplier.SupplierIdEmpresaDTO;
import com.backend.la.backendloveafrica.models.entities.Supplier;

@RestController
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
@RequestMapping("/suppliers")
public class SupplierController {

  @Autowired
  private ISupplierService suppService;

  @Autowired
  private SupplierMapper supplierMapper;

  @GetMapping("")
  public List<Supplier> findAllSuppliers() {
    return suppService.findAllSuppliers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findSupplierById(@PathVariable Long id) {
    Optional<Supplier> o = suppService.findOptionalSupplierById(id);

    if (o.isPresent()) {
      return ResponseEntity.ok().body(o.orElseThrow());
    }

    return ResponseEntity.notFound().build();

  }

  @GetMapping("/simplest")
  public ResponseEntity<List<SupplierIdEmpresaDTO>> findAllSimplestSuppliers() {
    return ResponseEntity.ok(supplierMapper.toSupplierIdEmpresaDTOs(this.findAllSuppliers()));
  }

  @PostMapping("/supplier")
  public ResponseEntity<?> saveSupplier(@RequestBody Supplier supplier) {
    return ResponseEntity.status(HttpStatus.CREATED).body(suppService.saveSupplier(supplier));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
    // try {
    // suppService.deleteSupplier(id);
    // return new ResponseEntity<>("Eliminado con éxito", HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>("Ocurrió un error al intentar eliminar",
    // HttpStatus.NOT_FOUND);
    // }
    Optional<Supplier> o = suppService.findOptionalSupplierById(id);
    if (o.isPresent()) {
      suppService.deleteSupplier(id);
      return ResponseEntity.noContent().build(); // 204
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateSupplier(@RequestBody Supplier supplier, @PathVariable Long id) {
    Optional<Supplier> o = suppService.updateSupplier(supplier, id);
    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

}
