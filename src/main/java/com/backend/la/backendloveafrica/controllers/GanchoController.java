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

import com.backend.la.backendloveafrica.Services.IGanchoService;
import com.backend.la.backendloveafrica.models.entities.Gancho;
import com.backend.la.backendloveafrica.models.enums.TipoGancho;

@RestController
@RequestMapping("ganchos")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class GanchoController {

  @Autowired
  private IGanchoService ganchoService;

  @GetMapping("")
  public List<Gancho> findAllGanchos() {
    return ganchoService.findAllGanchos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findGanchoById(@PathVariable Long id) {
    Optional<Gancho> o = ganchoService.findGanchoById(id);

    if (o.isPresent()) {
      return ResponseEntity.ok().body(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("")
  public ResponseEntity<?> saveGancho(@RequestBody Gancho gancho) {
    return ResponseEntity.status(HttpStatus.CREATED).body(ganchoService.saveGancho(gancho));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteGancho(@PathVariable Long id) {
    Optional<Gancho> o = ganchoService.findGanchoById(id);
    if (o.isPresent()) {
      ganchoService.deleteGanchoById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateGancho(@PathVariable Long id, @RequestParam String nombre,
      @RequestParam(required = false) String codigo,
      @RequestParam(required = false) String detalle,
      @RequestParam(required = false) Long idProveedor,
      @RequestParam(required = false) TipoGancho tipoGancho,
      @RequestParam(required = false) String medida,
      @RequestParam(required = false) String material,
      @RequestParam(required = false) String color,
      @RequestParam(required = false) Integer cantPorPack,
      @RequestParam(required = false) Double precioPorPack,
      @RequestParam(required = false) Double precioUni,
      @RequestParam(required = false) Integer stockPacks) {

    Optional<Gancho> o = ganchoService.updateGancho(id, nombre, Optional.ofNullable(codigo),
        Optional.ofNullable(detalle),
        Optional.ofNullable(idProveedor), Optional.ofNullable(tipoGancho), Optional.ofNullable(medida),
        Optional.ofNullable(material), Optional.ofNullable(color), Optional.ofNullable(cantPorPack),
        Optional.ofNullable(precioPorPack), Optional.ofNullable(precioUni), Optional.ofNullable(stockPacks));

    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
    }

    return ResponseEntity.notFound().build();
  }

}
