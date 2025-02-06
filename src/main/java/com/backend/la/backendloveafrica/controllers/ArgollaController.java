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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.IArgollaService;
import com.backend.la.backendloveafrica.models.entities.Argolla;

@RestController
@RequestMapping("argollas")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class ArgollaController {

  @Autowired
  private IArgollaService argollaService;

  @GetMapping("")
  public List<Argolla> findAllArgollas() {
    return argollaService.findAllArgollas();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findArgollaById(@PathVariable Long id) {
    Optional<Argolla> o = argollaService.findArgollaById(id);

    if (o.isPresent()) {
      return ResponseEntity.ok().body(o.get());
    }
    return ResponseEntity.notFound().build();

  }

  @PostMapping("")
  public ResponseEntity<?> saveArgolla(@RequestBody Argolla argolla) {
    return ResponseEntity.created(null).body(argollaService.saveArgolla(argolla));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateArgolla(@PathVariable Long id,
      @RequestParam(required = false) String nombre,
      @RequestParam(required = false) Long idProveedor,
      @RequestParam(required = false) String codigo,
      @RequestParam(required = false) String detalle,
      @RequestParam(required = false) String forma,
      @RequestParam(required = false) String circunferenciaInterna,
      @RequestParam(required = false) String circunferenciaExterna,
      @RequestParam(required = false) String material,
      @RequestParam(required = false) String color,
      @RequestParam(required = false) Integer cantPorPack,
      @RequestParam(required = false) Double precioPorPack,
      @RequestParam(required = false) Double precioUni,
      @RequestParam(required = false) Integer stockPacks) {

    Optional<Argolla> o = argollaService.updateArgolla(id, nombre, Optional.ofNullable(idProveedor),
        Optional.ofNullable(codigo), Optional.ofNullable(detalle), Optional.ofNullable(forma),
        Optional.ofNullable(circunferenciaInterna),
        Optional.ofNullable(circunferenciaExterna), Optional.ofNullable(material), Optional.ofNullable(color),
        Optional.ofNullable(cantPorPack), Optional.ofNullable(precioPorPack), Optional.ofNullable(precioUni),
        Optional.ofNullable(stockPacks));

    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteArgollaById(@PathVariable Long id) {
    Optional<Argolla> o = argollaService.findArgollaById(id);

    if (o.isPresent()) {
      argollaService.deleteArgollaById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
