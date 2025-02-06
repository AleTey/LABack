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

import com.backend.la.backendloveafrica.Services.IApliqueService;
import com.backend.la.backendloveafrica.models.entities.Aplique;

@RestController
@RequestMapping("/apliques")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class ApliqueController {

  @Autowired
  private IApliqueService apliqueService;

  @GetMapping("")
  public List<Aplique> findAllApliques() {
    return apliqueService.findAllApliques();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findApliqueById(@PathVariable Long id) {
    Optional<Aplique> o = apliqueService.findApliqueById(id);

    if (o.isPresent()) {
      return ResponseEntity.ok().body(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("")
  public ResponseEntity<?> saveAplique(@RequestBody Aplique aplique) {
    return ResponseEntity.status(HttpStatus.OK).body(apliqueService.saveAplique(aplique));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAplique(@PathVariable Long id) {
    Optional<Aplique> o = apliqueService.findApliqueById(id);
    if (o.isPresent()) {
      apliqueService.deleteAplique(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<?> updateAplique(@PathVariable Long id,
      @RequestParam(required = false) String nombre,
      @RequestParam(required = false) String codigo,
      @RequestParam(required = false) Long idProveedor,
      @RequestParam(required = false) String detalle,
      @RequestParam(required = false) String color,
      @RequestParam(required = false) Integer cantPorPack,
      @RequestParam(required = false) Double precioPorPack,
      @RequestParam(required = false) Double precioUnidad,
      @RequestParam(required = false) Integer stockPacks) {

    Optional<Aplique> o = apliqueService.updateAplique(id, Optional.ofNullable(nombre),
        Optional.ofNullable(codigo), Optional.ofNullable(idProveedor), Optional.ofNullable(detalle),
        Optional.ofNullable(color), Optional.ofNullable(cantPorPack), Optional.ofNullable(precioPorPack),
        Optional.ofNullable(precioUnidad), Optional.ofNullable(stockPacks));
    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
