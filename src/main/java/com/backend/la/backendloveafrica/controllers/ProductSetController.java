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

import com.backend.la.backendloveafrica.Services.IProductSetService;
import com.backend.la.backendloveafrica.models.dtos.ProductSetDTO;
import com.backend.la.backendloveafrica.models.entities.ProductSet;

@RestController
@RequestMapping("/product-set")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class ProductSetController {

  @Autowired
  private IProductSetService productSetService;

  @GetMapping
  public ResponseEntity<List<ProductSetDTO>> findAllProductSet() {
    return ResponseEntity.ok(productSetService.findAllDto());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductSet> findProductSetById(@PathVariable Long id) {
    Optional<ProductSet> o = productSetService.findById(id);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("dto/{id}")
  public ResponseEntity<ProductSetDTO> findProductSetDtoByiD(@PathVariable Long id) {
    Optional<ProductSetDTO> o = productSetService.findByIdDto(id);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<ProductSet> saveProductSet(@RequestBody ProductSet productSet) {
    return ResponseEntity.ok(productSetService.save(productSet));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProductSet(@PathVariable Long id) {
    productSetService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<ProductSet> updateProductSet(@RequestBody ProductSet productSet) {
    Optional<ProductSet> o = productSetService.update(productSet);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

}
