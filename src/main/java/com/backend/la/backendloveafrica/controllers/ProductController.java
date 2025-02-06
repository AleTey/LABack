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
import org.springframework.web.multipart.MultipartFile;

import com.backend.la.backendloveafrica.Services.IProductService;
import com.backend.la.backendloveafrica.models.dtos.ProductCardObjectDto;
import com.backend.la.backendloveafrica.models.dtos.ProductNameModelImgType;
import com.backend.la.backendloveafrica.models.entities.Fabric;
import com.backend.la.backendloveafrica.models.entities.ModelAndStripsForProduct;
import com.backend.la.backendloveafrica.models.entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/productos")
// @CrossOrigin(origins = { "https://www.myback.com.ar", "http://www.myback.com.ar.s3-website-sa-east-1.amazonaws.com/",
//     "http://la-app.s3-website-sa-east-1.amazonaws.com", "http://localhost:5173" })
public class ProductController {

  @Autowired
  private IProductService productService;

  @GetMapping("")
  public List<Product> findAllProducts() {
    return productService.findAllProducts();
  }

  @GetMapping("/dto/{string}")
  public ResponseEntity<List<ProductNameModelImgType>> searchByString(@PathVariable String string) {
    return ResponseEntity.ok(productService.findByName(string));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findProductById(@PathVariable Long id) {
    Optional<Product> o = productService.findProductById(id);
    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(o.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("")
  public ResponseEntity<?> saveProduct(@RequestBody Product product) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
  }

  @PostMapping("/producto")
  public ResponseEntity<?> recibirProducto(
      @RequestParam("nombre") String nombre,
      @RequestParam("colorForro") String colorForro,
      @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
      @RequestParam("fabricJson") String fabricJson,
      @RequestParam("modelAndStripsForProductJson") String modelAndStripsForProductJson) {

    System.out.println("STEP 1");

    ObjectMapper mapper = new ObjectMapper();
    Fabric fabric = null;
    ModelAndStripsForProduct modelAndStripsForProduct = null;
    try {
      fabric = mapper.readValue(fabricJson, Fabric.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    System.out.println("STEP 2");

    try {
      modelAndStripsForProduct = mapper.readValue(modelAndStripsForProductJson,
          ModelAndStripsForProduct.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    System.out.println("STEP 3");

    ProductCardObjectDto product = productService.saveProductWithImg(imageFile, nombre, colorForro, fabric,
        modelAndStripsForProduct);

    Optional<Product> o = productService.findProductById(product.getId());
    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(o.get());
    }
    return ResponseEntity.badRequest().build();
  }
  // Convertir JSON a objeto ModelAndStripsForProduct
  // ModelAndStripsForProduct modelAndStripsForProduct =
  // ObjectMapper.readValue(modelAndStripsForProductJson,
  // ModelAndStripsForProduct.class);

  // Guardar la imagen en la base de datos
  // Blob imgBlob = new Blob(img.getBytes());
  // producto.setImagen(imgBlob);

  // ... Guardar producto, modelAndStripsForProduct y sus relaciones en la base de
  // datos ...

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    Optional<Product> o = productService.findProductById(id);

    if (o.isPresent()) {
      productService.deleteProduct(id);
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.notFound().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<?> updateProduct(@PathVariable Long id,
      @RequestBody(required = false) Fabric fabric,
      @RequestBody(required = false) ModelAndStripsForProduct modelAndStripsForProduct) {

    Optional<Product> o = productService.updateProduct(
        id,
        Optional.ofNullable(fabric),
        Optional.ofNullable(modelAndStripsForProduct));

    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PutMapping("/update-product/{id}")
  public ResponseEntity<ProductCardObjectDto> updateProductWithImg(@PathVariable Long id,
      @RequestParam("nombre") String nombre,
      @RequestParam("colorForro") String colorForro,
      @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
      @RequestParam("fabricJson") String fabricJson,
      @RequestParam("modelAndStripsForProductJson") String modelAndStripsForProductJson) {

    ObjectMapper mapper = new ObjectMapper();
    Fabric fabric = null;
    ModelAndStripsForProduct modelAndStripsForProduct = null;

    try {
      fabric = mapper.readValue(fabricJson, Fabric.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    try {
      modelAndStripsForProduct = mapper.readValue(modelAndStripsForProductJson, ModelAndStripsForProduct.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok(productService.updateProductWithImg(id, nombre, colorForro, imageFile, fabric,
        modelAndStripsForProduct));
  }

  @PutMapping("/complete-product/{id}")
  public ResponseEntity<?> updateCompleteProduct(@PathVariable Long id, @RequestBody Product product) {

    Optional<Product> o = productService.updateCompleteProduct(id, product);

    if (o.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/find-by-string/{string}")
  public ResponseEntity<List<Product>> findProductByString(@PathVariable String string) {
    return ResponseEntity.ok(productService.findByString(string));
  }

  @GetMapping("/page/search")
  public ResponseEntity<Page<Product>> findPageProduct(@RequestParam String string, @RequestParam int page,
      @RequestParam int size) {
    return ResponseEntity.ok(productService.findPageByString(string, page, size));
  }

  @GetMapping("/page/card-dto/by-string")
  public ResponseEntity<Page<ProductCardObjectDto>> findProductsCardDtoPageByString(@RequestParam String string,
      @RequestParam int page, @RequestParam int size) {
    return ResponseEntity.ok(productService.findProductCardDtoPageByString(string, page, size));
  }

  @GetMapping("/page")
  public ResponseEntity<Page<Product>> findAllPages(@RequestParam int page, @RequestParam int size) {
    return ResponseEntity.ok(productService.findAll(page, size));
  }

  @GetMapping("/page/card-dto")
  public ResponseEntity<Page<ProductCardObjectDto>> findProductCardDtoPage(@RequestParam int page,
      @RequestParam int size) {
    return ResponseEntity.ok(productService.findAllProductCardDtoPage(page, size));
  }

  @GetMapping("/product-cost/{id}")
  public ResponseEntity<Double> getProductCost(@PathVariable Long id) {
    return ResponseEntity.ok(productService.productCostCalculator(id));
  }

}
