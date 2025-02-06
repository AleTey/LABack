package com.backend.la.backendloveafrica.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.la.backendloveafrica.Services.ICutSpreadSheetService;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.CutSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.CutSpreadSheet;

@RestController
@RequestMapping("/cut-spreadsheets")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
    // "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class CutSpreadSheetController {

  @Autowired
  private ICutSpreadSheetService cutSpreadSheetService;

  @GetMapping("/{id}")
  public ResponseEntity<CutSpreadSheetDTO> findDtoById(@PathVariable Long id) {
    Optional<CutSpreadSheetDTO> o = cutSpreadSheetService.findDtoById(id);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<CutSpreadSheetDTO> updateCutSpreadSheet(@PathVariable Long id,
      @RequestBody CutSpreadSheet cutSpreadSheet) {
    // System.out.println(cutSpreadSheet.toString());
    // System.out.println(cutSpreadSheet.getAmountPerSizeForProducts().toString());
    // System.out.println(cutSpreadSheet.getFabricLengthDetails().toString());
    System.out.println("ENTRANDO CONTROLLER");
    Optional<CutSpreadSheet> o = cutSpreadSheetService.update(cutSpreadSheet);
    if (o.isPresent()) {
      System.out.println("POR CONVERTIR A DTO Y FINALIZAR");
      return ResponseEntity.ok(cutSpreadSheetService.convertCutSpreadSheetToDTO(o));
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/dto/{id}")
  public ResponseEntity<CutSpreadSheetDTO> updateCutSpreadSheetWithDto(@PathVariable Long id,
      @RequestBody CutSpreadSheetDTO cutSpreadSheet) {
    System.out.println("COMIENZO CONTROLLER");
    // System.out.println(cutSpreadSheet.toString());
    // System.out.println(cutSpreadSheet.getAmountPerSizeForProducts().toString());
    // System.out.println(cutSpreadSheet.getFabricLengthDetails().toString());
    System.out.println("ENTRANDO CONTROLLER");
    Optional<CutSpreadSheet> o = cutSpreadSheetService.updateWithDto(cutSpreadSheet);
    if (o.isPresent()) {
      System.out.println("POR CONVERTIR A DTO Y FINALIZAR");
      return ResponseEntity.ok(cutSpreadSheetService.convertCutSpreadSheetToDTO(o));
    }
    return ResponseEntity.notFound().build();
  }

  // @PutMapping("/{id}")

}
