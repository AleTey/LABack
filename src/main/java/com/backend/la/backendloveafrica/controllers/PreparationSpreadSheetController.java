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
import org.springframework.web.multipart.MultipartFile;

import com.backend.la.backendloveafrica.Services.IPreparationSpreadSheetService;
import com.backend.la.backendloveafrica.models.dtos.ImageDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.PreparationSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.PreparationSpreadSheet;

@RestController
@RequestMapping("/preparation-spreadsheet")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class PreparationSpreadSheetController {

  @Autowired
  private IPreparationSpreadSheetService preparationSpreadSheetService;

  @GetMapping("/{id}")
  public ResponseEntity<PreparationSpreadSheet> FindPreparationSpreadSheetById(@PathVariable Long id) {
    Optional<PreparationSpreadSheet> o = preparationSpreadSheetService.findById(id);

    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/dto/{id}")
  public ResponseEntity<PreparationSpreadSheetDTO> FindPreparationSpreadSheetDtoById(@PathVariable Long id) {
    Optional<PreparationSpreadSheetDTO> o = preparationSpreadSheetService.findDtoById(id);

    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping
  public ResponseEntity<PreparationSpreadSheetDTO> updatePreparationSpreadSheet(
      @RequestBody PreparationSpreadSheet preparationSpreadSheet) {
    Optional<PreparationSpreadSheetDTO> o = preparationSpreadSheetService.update(preparationSpreadSheet);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/put-img/{id}")
  public ResponseEntity<ImageDTO> updateImage(@PathVariable Long id, MultipartFile imageFile) {
    try {
      ImageDTO img = preparationSpreadSheetService.updateImage(id, imageFile);
      return ResponseEntity.ok(img);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

}
