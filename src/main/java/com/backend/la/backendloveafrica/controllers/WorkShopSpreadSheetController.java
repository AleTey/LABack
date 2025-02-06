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

import com.backend.la.backendloveafrica.Services.IWorkShopSpreadSheetService;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.WorkShopSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.WorkShopSpreadSheet;

@RestController
@RequestMapping("/workshop-spreadsheet")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class WorkShopSpreadSheetController {

  @Autowired
  private IWorkShopSpreadSheetService workShopSpreadSheetService;

  @GetMapping("/dto/{id}")
  public ResponseEntity<WorkShopSpreadSheetDTO> getWorkShopSpreadSheetDtoById(@PathVariable Long id) {
    Optional<WorkShopSpreadSheetDTO> o = workShopSpreadSheetService.getDtoById(id);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping
  public ResponseEntity<WorkShopSpreadSheetDTO> updateWorkShopSpreadSheet(
      @RequestBody WorkShopSpreadSheet workShopSpreadSheet) {
    Optional<WorkShopSpreadSheetDTO> o = workShopSpreadSheetService.update(workShopSpreadSheet);
    if (o.isPresent()) {
      return ResponseEntity.ok(o.get());
    }
    return ResponseEntity.notFound().build();
  }

}
