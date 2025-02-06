package com.backend.la.backendloveafrica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.backend.la.backendloveafrica.Services.IModelService;
import com.backend.la.backendloveafrica.models.dtos.ModelWithDetalleInsumoDtoDTO;
import com.backend.la.backendloveafrica.models.entities.Model;

@RestController
@RequestMapping("/models")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class ModelController {

  @Autowired
  private IModelService modelService;

  @GetMapping("")
  public List<Model> findAllModels() {
    return modelService.findAllModels();
  }

  @GetMapping("{id}")
  public ResponseEntity<?> findModelById(@PathVariable Long id) {
    Optional<Model> o = modelService.findModelById(id);
    if (o.isPresent()) {
      return ResponseEntity.ok().body(o);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("")
  public ResponseEntity<?> saveModel(@RequestBody Model model) {
    return ResponseEntity.status(HttpStatus.CREATED).body(modelService.saveModel(model));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteModel(@PathVariable Long id) {
    Optional<Model> o = modelService.findModelById(id);
    if (o.isPresent()) {
      // try throw error Objeto en uso
      try {

      } catch (Exception e) {
        // TODO: handle exception
      }
      modelService.deleteModel(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<?> updateModel(@PathVariable Long id, @RequestBody Model model) {
    Optional<Model> o = modelService.updateModel(id, model);
    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("searchByString/{string}")
  public ResponseEntity<?> findModelByString(@PathVariable String string) {
    List<Model> modelsEncontrados = modelService.findModelByString(string);
    if (modelsEncontrados.size() > 0) {
      return ResponseEntity.status(HttpStatus.OK).body(modelsEncontrados);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/find-all/page")
  public ResponseEntity<Page<ModelWithDetalleInsumoDtoDTO>> findAllPage(@RequestParam int page,
      @RequestParam int size) {
    return ResponseEntity.ok(modelService.findAllPage(page, size));
  }

  @GetMapping("/by-string/page")
  public ResponseEntity<Page<ModelWithDetalleInsumoDtoDTO>> getPageModelByString(@RequestParam String string,
      @RequestParam int page, @RequestParam int size) {
    return ResponseEntity.ok(modelService.getPageByString(string, page, size));
  }

}
