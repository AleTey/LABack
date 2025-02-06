package com.backend.la.backendloveafrica.controllers;

import java.util.List;
import java.util.Map;
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

import com.backend.la.backendloveafrica.Exception.IllegalStatusException;
import com.backend.la.backendloveafrica.Services.ILoteService;
import com.backend.la.backendloveafrica.models.dtos.LoteCreationDTO;
import com.backend.la.backendloveafrica.models.dtos.ProductNameModelImgType;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.LoteResponseDTO;
import com.backend.la.backendloveafrica.models.entities.Lote;
import com.backend.la.backendloveafrica.models.enums.LoteState;

import jakarta.validation.Valid;

@RestController
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
@RequestMapping("/lotes")
public class LoteController {

  @Autowired
  private ILoteService loteService;

  @GetMapping
  public ResponseEntity<List<Lote>> findAllLotes() {
    return ResponseEntity.ok(loteService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Lote> findLoteById(@PathVariable Long id) {
    Optional<Lote> o = loteService.findById(id);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<LoteResponseDTO> createLote(@RequestBody @Valid LoteCreationDTO loteCreationDTO) {
    System.out.println(loteCreationDTO.toString());
    LoteResponseDTO lote = loteService.save(loteCreationDTO);
    return ResponseEntity.ok(lote);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteLote(@PathVariable Long id) {
    Optional<Lote> o = loteService.findById(id);

    if (o.isPresent()) {
      loteService.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/lote-dto/{id}")
  public ResponseEntity<LoteResponseDTO> findByIdResponseDTO(@PathVariable Long id) {
    Optional<LoteResponseDTO> o = loteService.findByIdResponseDTO(id);
    if (o.isPresent()) {
      System.out.println(o.get().toString());
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/by-state/{status}")
  public ResponseEntity<List<LoteResponseDTO>> getLoteByStatus(@PathVariable LoteState status) {
    return ResponseEntity.ok(loteService.findLotesByStatus(status));
  }

  @PutMapping("/update-state/{id}/{status}")
  public ResponseEntity<?> updateStatusLote(@PathVariable Long id, @PathVariable LoteState status) {
    loteService.changeStatus(id, status);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/update-state-cutter/{id}/{status}")
  public ResponseEntity<?> updateStatusLoteCutter(@PathVariable Long id, @PathVariable LoteState status) {
    if (status != LoteState.COLA && status != LoteState.CORTE
        && status != LoteState.PREPARADO) {
      throw new IllegalStatusException("Status does exist but is denied for this user");
    }
    loteService.changeStatus(id, status);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/update-state-controller/{id}/{status}")
  public ResponseEntity<?> updateStatusLoteController(@PathVariable Long id, @PathVariable LoteState status) {
    if (status != LoteState.PREPARADO && status != LoteState.TALLER
        && status != LoteState.FINALIZADO) {
      throw new IllegalStatusException("Status does exist but is denied for this user");
    }
    Optional<Lote> lote = loteService.findById(id);
    if (lote.isPresent()) {
      Lote loteDb = lote.get();
      if (loteDb.getStatus() == LoteState.COLA || loteDb.getStatus() == LoteState.CORTE) {
        throw new IllegalStatusException("Status does exist but is denied for this user");
      }
    }
    loteService.changeStatus(id, status);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/update-state-workshop/{id}/{status}")
  public ResponseEntity<?> updateStatusLoteWorkshop(@PathVariable Long id, @PathVariable LoteState status) {
    if (status != LoteState.CONTROL) {
      throw new IllegalStatusException("Status does exist but is denied for this user");
    }
    Optional<Lote> lote = loteService.findById(id);
    if (lote.isPresent()) {
      Lote loteDb = lote.get();
      if (loteDb.getStatus() != LoteState.TALLER) {
        throw new IllegalStatusException("Status does exist but is denied for this user");
      }
    }
    loteService.changeStatusWorkshop(id, status);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/lotes-workshop")
  public ResponseEntity<List<LoteResponseDTO>> findLotesWorkshop() {
    return ResponseEntity.ok(loteService.findLoteByWorkshop());
  }

  @GetMapping("/lote-by-collection/{collection}")
  public ResponseEntity<List<LoteResponseDTO>> findLoteByFabricCollection(@PathVariable String collection) {
    return ResponseEntity.ok(loteService.findByFabricCollection(collection));
  }

  @GetMapping("/product/season-status/{season}/{status}")
  public ResponseEntity<List<ProductNameModelImgType>> findProductsBySeasonAndLoteState(@PathVariable String season,
      @PathVariable LoteState status) {
    return ResponseEntity.ok(loteService.findProductsByStatusLoteAndSeason(season, status));
  }

  @GetMapping("/product/season-ex-statuses/{season}")
  public ResponseEntity<List<ProductNameModelImgType>> findProductsBySeasonExcludingStatuses(
      @PathVariable String season,
      @RequestBody List<LoteState> statuses) {
    return ResponseEntity.ok(loteService.findProductsBySeasonExcludingStatuses(season, statuses));
  }

  @GetMapping("/general-season-state/{season}")
  public ResponseEntity<Map<String, List<ProductNameModelImgType>>> getGeneralSeasonState(@PathVariable String season) {
    return ResponseEntity.ok(loteService.undoPreparingFinishedProducts(season));
  }
}
