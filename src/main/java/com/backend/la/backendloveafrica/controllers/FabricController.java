package com.backend.la.backendloveafrica.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.la.backendloveafrica.Services.IFabricService;
import com.backend.la.backendloveafrica.Services.external.IS3Service;
import com.backend.la.backendloveafrica.models.dtos.FabricNoPriceNoSupp;
import com.backend.la.backendloveafrica.models.dtos.FabricNombreCodigoTipoImgDTO;
import com.backend.la.backendloveafrica.models.entities.Fabric;

@RestController
// @CrossOrigin(origins = { "https://www.myback.com.ar",
// "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
// "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173"
// })
@RequestMapping("/fabrics")
public class FabricController {

  @Autowired
  private IFabricService fabricService;

  @Autowired
  private IS3Service s3Service;

  @GetMapping
  public List<Fabric> findAll() {
    return fabricService.findAll();
  }

  @GetMapping("/page/{page}/{cant}")
  public Page<Fabric> findAll(@PathVariable Integer page, @PathVariable Integer cant) {
    Pageable pageable = PageRequest.of(page, cant);
    return fabricService.findAll(pageable);
  }

  @GetMapping("/page-dto/{page}/{cant}")
  public Page<FabricNoPriceNoSupp> findFabricDtoPage(@PathVariable Integer page, @PathVariable Integer cant) {
    Pageable pageable = PageRequest.of(page, cant);
    return fabricService.findPageDto(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findFabricById(@PathVariable Long id) {
    Optional<Fabric> fabricOptional = fabricService.findFabricById(id);
    if (fabricOptional.isPresent()) {
      return ResponseEntity.ok(fabricOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("")
  public ResponseEntity<?> saveFabric(@RequestBody Fabric fabric) {
    Fabric fabricSaved = fabricService.saveFabric(fabric);
    System.out.println("=================saveFabricController=========================");
    System.out.println(fabricSaved.getUrlFile());
    System.out.println("==========================================");
    return ResponseEntity.status(HttpStatus.CREATED).body(fabric);
  }

  @PostMapping("/addimg/{id}")
  public ResponseEntity<?> createFabricWithImage(@PathVariable Long id,
      @RequestPart("imageFile") MultipartFile imageFile) {
        System.out.println("ENTRANDO CONTROLLER / ADD IMAGE");
    try {
      System.out.println(imageFile);
      Optional<Fabric> o = fabricService.addImg(id, imageFile);
      return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
    } catch (IOException e) {
      // Maneja errores de IO, por ejemplo, problemas al leer la imagen
      return new ResponseEntity<>("Error al procesar la imagen: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      // Maneja otros errores
      return new ResponseEntity<>("Error al procesar la solicitud: " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // @CrossOrigin(origins = "http://localhost:5173")
  // @CrossOrigin(origins = "*")
  @PutMapping("/addimg/{id}")
  public ResponseEntity<?> addImgToFabric(@PathVariable Long id,
      @RequestPart("imageFile") MultipartFile imageFile) {
        System.out.println("ENTRANDO PUT CONTROLLER / ADD IMAGE");
    try {

      Optional<Fabric> o = fabricService.addImg(id, imageFile);
      System.out.println("TRY CONTROLLER ADD IMAGE");
      System.out.println(o.get().getUrlFile());
      return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
    } catch (IOException e) {
      // Maneja errores de IO, por ejemplo, problemas al leer la imagen
      return new ResponseEntity<>("Error al procesar la imagen: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      // Maneja otros errores
      return new ResponseEntity<>("Error al procesar la solicitud: " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> DeleteFabric(@PathVariable Long id) {
    Optional<Fabric> o = fabricService.findFabricById(id);
    if (o.isPresent()) {
      fabricService.deleteFabric(id);
      return ResponseEntity.noContent().build(); // 204
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateFabric(@RequestBody Fabric fabric, @PathVariable Long id) {
    System.out.println("PUT ENTRANDO CONTROLLER / id");
    Optional<Fabric> o = fabricService.updateFabric(fabric, id);
    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  // @CrossOrigin(origins = "http://localhost:5173/")
  @GetMapping("/searchByString")
  public ResponseEntity<?> findByString(@RequestParam String string, @RequestParam int page, int size) {
    Page<Fabric> fabricsEncontrados = fabricService.findByString(string, page, size);
    return ResponseEntity.status(HttpStatus.OK).body(fabricsEncontrados);
  }

  @GetMapping("/search-dto-ByString")
  public ResponseEntity<Page<FabricNoPriceNoSupp>> findFabricDtoByString(@RequestParam String string,
      @RequestParam int page, int size) {
    Page<FabricNoPriceNoSupp> fabricsEncontrados = fabricService.findPageDtoByString(string, page, size);
    return ResponseEntity.ok(fabricsEncontrados);
  }

  @GetMapping("/seasons")
  public ResponseEntity<List<String>> findDistinctTemporadas() {
    return ResponseEntity.ok(fabricService.findDistinctSeasonFromFabric());
  }

  @GetMapping("/by-season/{season}")
  public ResponseEntity<List<FabricNombreCodigoTipoImgDTO>> findFabricsBySeason(@PathVariable String season) {
    System.out.println("SEASON: " + season);
    System.out.println("SIZE---------------------------------");
    System.out.println("SIZE" + fabricService.findFabricsBySeason(season).size());
    return ResponseEntity.ok(fabricService.findFabricsBySeason(season));
  }

  @PostMapping("/upload-img/{key}")
  public ResponseEntity<String> uploadImg(@PathVariable String key, @RequestPart("imageFile") MultipartFile imageFile)
      throws Exception {
    try {
      return ResponseEntity.ok(s3Service.uploadFile(key, imageFile));
    } catch (Exception e) {
      throw new Exception("Error uploading image");
    }
  }

}
