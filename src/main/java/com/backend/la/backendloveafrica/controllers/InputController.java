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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.IInputService;
import com.backend.la.backendloveafrica.models.entities.Input;

@RestController
@RequestMapping("/inputs")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class InputController {

  @Autowired
  private IInputService inputService;

  @GetMapping()
  public List<Input> findAllInputs() {
    return inputService.findAllInputs();
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getInputById(@PathVariable Long id) {
    Optional<Input> o = inputService.findInputById(id);

    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  // @PostMapping("")
  // public Input saveInput(@RequestBody Object object) {
  // return inputService.saveInput(object);
  // }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteInput(@PathVariable Long id) {
    Optional<Input> o = inputService.findInputById(id);

    if (o.isPresent()) {
      inputService.deleteInput(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<?> updateInput(@PathVariable Long id,

      @RequestParam(required = false) String codigo,
      @RequestParam(required = false) String nombre,
      @RequestParam(required = false) Long idSupplier,
      @RequestParam(required = false) String detalle,
      @RequestParam(required = false) String forma,
      @RequestParam(required = false) String medida,
      @RequestParam(required = false) String material,
      @RequestParam(required = false) String color,
      @RequestParam(required = false) Integer cantPorPack,
      @RequestParam(required = false) Double precioPorPack,
      @RequestParam(required = false) Double precioUni,
      @RequestParam(required = false) Integer stockPacks,
      @RequestParam(required = false) String ancho,
      @RequestParam(required = false) Double precioRollo,
      @RequestParam(required = false) Double mtsPorRollo,
      @RequestParam(required = false) Double precioMtr,
      @RequestParam(required = false) Integer stockEnRollos) {

    Optional<?> o = inputService.updateInput(id, Optional.ofNullable(nombre), Optional.ofNullable(codigo),
        Optional.ofNullable(idSupplier), Optional.ofNullable(detalle), Optional.ofNullable(forma),
        Optional.ofNullable(medida), Optional.ofNullable(material), Optional.ofNullable(color),
        Optional.ofNullable(cantPorPack), Optional.ofNullable(precioPorPack), Optional.ofNullable(precioUni),
        Optional.ofNullable(stockPacks), Optional.ofNullable(ancho), Optional.ofNullable(precioRollo),
        Optional.ofNullable(mtsPorRollo), Optional.ofNullable(precioMtr), Optional.ofNullable(stockEnRollos));

    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
    }

    return ResponseEntity.notFound().build();
  }

}
