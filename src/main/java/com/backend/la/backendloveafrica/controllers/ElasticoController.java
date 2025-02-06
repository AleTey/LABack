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

import com.backend.la.backendloveafrica.Services.IElasticoService;
import com.backend.la.backendloveafrica.models.entities.Elastico;

@RestController
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
@RequestMapping("/elasticos")
public class ElasticoController {

  @Autowired
  private IElasticoService elasticoService;

  @GetMapping("")
  public List<Elastico> getAllElasticos() {
    return elasticoService.findAllElasticos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findElasticoById(@PathVariable Long id) {
    Optional<Elastico> o = elasticoService.findElasticoById(id);

    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }

    return ResponseEntity.notFound().build();

  }

  @PostMapping("")
  public ResponseEntity<?> saveElastico(@RequestBody Elastico elastico) {
    return ResponseEntity.status(HttpStatus.CREATED).body(elasticoService.saveElastico(elastico));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateElastico(@PathVariable Long id,
      @RequestParam(required = false) String nombre, @RequestParam(required = false) String codigo,
      @RequestParam(required = false) Long proveedor, @RequestParam(required = false) String detalle,
      @RequestParam(required = false) String ancho, @RequestParam(required = false) String material,
      @RequestParam(required = false) String color, @RequestParam(required = false) Double precioRollo,
      @RequestParam(required = false) Double mtsPorRollo, @RequestParam(required = false) Double precioMtr,
      @RequestParam(required = false) Integer stockEnRollos) {

    Optional<Elastico> o = elasticoService.updateElastico(id, Optional.ofNullable(nombre), Optional.ofNullable(codigo),
        Optional.ofNullable(proveedor), Optional.ofNullable(detalle), Optional.ofNullable(ancho),
        Optional.ofNullable(material), Optional.ofNullable(color), Optional.ofNullable(precioRollo),
        Optional.ofNullable(mtsPorRollo), Optional.ofNullable(precioMtr), Optional.ofNullable(stockEnRollos));

    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteElastico(@PathVariable Long id) {

    Optional<Elastico> o = elasticoService.findElasticoById(id);

    if (o.isPresent()) {
      elasticoService.deleteElastico(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

}
