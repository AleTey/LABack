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

import com.backend.la.backendloveafrica.Services.IEtiquetaService;
import com.backend.la.backendloveafrica.models.entities.Etiqueta;

@RestController
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
@RequestMapping("/etiquetas")
public class EtiquetaController {

  @Autowired
  private IEtiquetaService etiquetaService;

  @GetMapping("")
  public List<Etiqueta> findAllEtiquetas() {
    return etiquetaService.findAllEtiquetas();
  }

  @PostMapping("")
  public ResponseEntity<?> saveEtiqueta(@RequestBody Etiqueta etiqueta) {
    return ResponseEntity.status(HttpStatus.CREATED).body(etiquetaService.saveEtiqueta(etiqueta));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findEtiquetaById(@PathVariable Long id) {
    Optional<Etiqueta> o = etiquetaService.findEtiquetaById(id);
    if (o.isPresent()) {
      return ResponseEntity.ok().body(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteEtiquetaById(@PathVariable Long id) {
    Optional<Etiqueta> o = etiquetaService.findEtiquetaById(id);
    if (o.isPresent()) {
      etiquetaService.deleteEtiquetaById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateEtiqueta(@PathVariable Long id,
      @RequestParam(required = true) String nombre,
      @RequestParam(required = false) String codigo,
      @RequestParam(required = false) Long idProveedor,
      @RequestParam(required = false) String detalle,
      @RequestParam(required = false) String marca,
      @RequestParam(required = false) Double precioUnidad,
      @RequestParam(required = false) Integer stock) {
    Optional<Etiqueta> etiqueta = etiquetaService.updateEtiqueta(id, nombre, Optional.ofNullable(codigo),
        Optional.ofNullable(idProveedor),
        Optional.ofNullable(detalle), Optional.ofNullable(marca), Optional.ofNullable(precioUnidad),
        Optional.ofNullable(stock));

    if (etiqueta.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(etiqueta);
    }
    return ResponseEntity.notFound().build();
  }

}
