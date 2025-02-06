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

import com.backend.la.backendloveafrica.Services.ICorrederaService;
import com.backend.la.backendloveafrica.models.entities.Corredera;

@RestController
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
@RequestMapping("/correderas")
public class CorrederaController {

  @Autowired
  private ICorrederaService correderaService;

  @GetMapping("")
  public List<Corredera> findAllCorrederas() {
    return correderaService.findAllCorrederas();
  };

  @GetMapping("/{id}")
  public ResponseEntity<?> findCorrederaById(@PathVariable Long id) {
    Optional<Corredera> o = correderaService.findCorrederaById(id);

    if (o.isPresent()) {
      return ResponseEntity.ok().body(o.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("")
  public ResponseEntity<?> saveCorredera(@RequestBody Corredera corredera) {

    return ResponseEntity.status(HttpStatus.CREATED).body(correderaService.saveCorredera(corredera));

  }

  // @PostMapping("")
  // public ResponseEntity<?> saveCorredera(@RequestParam String codigo,
  // @RequestParam Double stock, @RequestParam String unitOfMeasurement,
  // @RequestParam Double precio,
  // @RequestParam Double precioEnDolares, @RequestParam MultipartFile imageFile,
  // @RequestParam Supplier proveedor,
  // @RequestParam String forma, String material, @RequestParam String medida,
  // @RequestParam String color,
  // @RequestParam Integer cantPorPack, @RequestParam String detalle) throws
  // IOException {

  // return
  // ResponseEntity.status(HttpStatus.CREATED).body(correderaService.saveCorrederaWithImage(codigo,
  // stock,
  // unitOfMeasurement, precio, precioEnDolares, imageFile, proveedor, forma,
  // material, medida, color, cantPorPack,
  // detalle));

  // }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCorredera(@PathVariable Long id) {

    Optional<Corredera> o = correderaService.findCorrederaById(id);

    if (o.isPresent()) {
      correderaService.deleteCorrederaById(id);
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateCorrederaWithImage(@PathVariable Long id,

      @RequestParam(required = false) String nombre,
      @RequestParam(required = false) String codigo,
      @RequestParam(required = false) Long proveedor,
      @RequestParam(required = false) String detalle,
      @RequestParam(required = false) String forma,
      @RequestParam(required = false) String material,
      @RequestParam(required = false) String medida,
      @RequestParam(required = false) String color,
      @RequestParam(required = false) Integer cantPorPack,
      @RequestParam(required = false) Double precioPorPack,
      @RequestParam(required = false) Double precioUni,
      @RequestParam(required = false) Integer stockPacks) {

    try {
      Optional<Corredera> o = correderaService.updateCorredera(id, Optional.ofNullable(nombre),
          Optional.ofNullable(codigo), Optional.ofNullable(proveedor), Optional.ofNullable(detalle),
          Optional.ofNullable(forma), Optional.ofNullable(material), Optional.ofNullable(medida),
          Optional.ofNullable(color), Optional.ofNullable(cantPorPack), Optional.ofNullable(precioPorPack),
          Optional.ofNullable(precioUni), Optional.ofNullable(stockPacks));
      // Optional<Corredera> o = correderaService.updateCorredera(id, codigo, stock,
      // unitOfMeasurement, precio,
      // precioEnDolares, idProveedor, forma, material, medida, color, cantPorPack,
      // detalle);
      return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());

      // } catch (IOException e) {
      // // Maneja errores de IO, por ejemplo, problemas al leer la imagen
      // return new ResponseEntity<>("Error al procesar la imagen: " + e.getMessage(),
      // HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      // Maneja otros errores
      return new ResponseEntity<>("Error al procesar la solicitud: " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // @PutMapping("/{id}")
  // public ResponseEntity<?> updateCorrederaWithImage(@PathVariable Long id,
  // @RequestParam String codigo,
  // @RequestParam Double stock, @RequestParam String unitOfMeasurement,
  // @RequestParam Double precio, @RequestParam Double precioEnDolares,
  // @RequestParam(required = false) MultipartFile imageFile,
  // @RequestParam Long idProveedor, @RequestParam String forma, @RequestParam
  // String material,
  // @RequestParam String medida, @RequestParam String color, @RequestParam
  // Integer cantPorPack,
  // @RequestParam String detalle) {

  // try {
  // Optional<Corredera> o = correderaService.updateCorrederaWithImage(id, codigo,
  // stock, unitOfMeasurement, precio,
  // precioEnDolares, imageFile, idProveedor, forma, material, medida, color,
  // cantPorPack, detalle);
  // return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());

  // } catch (IOException e) {
  // // Maneja errores de IO, por ejemplo, problemas al leer la imagen
  // return new ResponseEntity<>("Error al procesar la imagen: " + e.getMessage(),
  // HttpStatus.BAD_REQUEST);
  // } catch (Exception e) {
  // // Maneja otros errores
  // return new ResponseEntity<>("Error al procesar la solicitud: " +
  // e.getMessage(),
  // HttpStatus.INTERNAL_SERVER_ERROR);
  // }
  // }

  // @PutMapping()

}
