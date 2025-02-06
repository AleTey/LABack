package com.backend.la.backendloveafrica.Services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.backend.la.backendloveafrica.Exception.InsufficientInformationToCalculateCost;
import com.backend.la.backendloveafrica.Services.external.IS3Service;
import com.backend.la.backendloveafrica.mapper.ProductMapper;
import com.backend.la.backendloveafrica.models.dtos.ProductCardObjectDto;
import com.backend.la.backendloveafrica.models.dtos.ProductNameModelImgType;
import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;
import com.backend.la.backendloveafrica.models.entities.Aplique;
import com.backend.la.backendloveafrica.models.entities.Argolla;
import com.backend.la.backendloveafrica.models.entities.Corredera;
import com.backend.la.backendloveafrica.models.entities.DetalleInsumo;
import com.backend.la.backendloveafrica.models.entities.Elastico;
import com.backend.la.backendloveafrica.models.entities.Etiqueta;
import com.backend.la.backendloveafrica.models.entities.Fabric;
import com.backend.la.backendloveafrica.models.entities.FabricLengthDetails;
import com.backend.la.backendloveafrica.models.entities.Gancho;
import com.backend.la.backendloveafrica.models.entities.Lote;
import com.backend.la.backendloveafrica.models.entities.Model;
import com.backend.la.backendloveafrica.models.entities.ModelAndStripsForProduct;
import com.backend.la.backendloveafrica.models.entities.Product;
import com.backend.la.backendloveafrica.models.entities.StripDetailsForProduct;
import com.backend.la.backendloveafrica.models.enums.TipoPrenda;
import com.backend.la.backendloveafrica.repositories.ILoteRepository;
import com.backend.la.backendloveafrica.repositories.IProductRepository;

@Service
public class ProductServiceImp implements IProductService {

  private static Logger logger = LoggerFactory.getLogger(ProductServiceImp.class);

  @Autowired
  private IProductRepository productRepository;

  @Autowired
  private IWarehouseService warehouseService;

  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private IModelService modelService;

  @Autowired
  private ILoteRepository loteRepository;

  @Autowired
  private IFabricService fabricService;

  @Autowired
  private IS3Service s3service;

  @Override
  @Transactional(readOnly = true)
  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public List<ProductNameModelImgType> findByName(String string) {
    List<Product> productsFound = productRepository.findByName(string);
    return productMapper.toProductNameModelImgTypesList(productsFound);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Product> findProductById(Long id) {
    return productRepository.findById(id);
  }

  @Override
  @Transactional
  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  @Override
  @Transactional
  public ProductCardObjectDto saveProductWithImg(MultipartFile imgFile, String nombre, String colorForro, Fabric fabric,
      ModelAndStripsForProduct modelAndStripsForProduct) {

    Optional<Model> model = modelService.findModelById(modelAndStripsForProduct.getModel().getId());
    modelAndStripsForProduct.setModel(model.orElseThrow(() -> new RuntimeException("Modelo no encontrado")));
    Optional<Fabric> oFabric = fabricService.findFabricById(fabric.getId());
    // if (imgFile == null) {
    Product newProduct = new Product();
    newProduct.setNombre(nombre);
    newProduct.setColorForro(colorForro);
    newProduct.setFabric(oFabric.orElseThrow(() -> new RuntimeException("Tela no encontrada en la base de datos")));
    newProduct.setModelAndStripsForProduct(modelAndStripsForProduct);

    Product product = productRepository.save(newProduct);
    System.out.println("POR ENTRAR AL !IMGFILE.ISEMPTY");
    if (imgFile != null) {
      System.out.println("ENTRANDO AL IF !imgFile.isEmpty() EN SAVEPRODUCTWITHIMG");
      try {
        System.out.println("ENTRADO AL TRY PRODUCT ");
        product.setUrlFile(
            s3service.uploadFile(s3service.keyGenerator(product.getNombre(), product.getId()), imgFile));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    product.setCodeBarNumber(createCodeBarNumber(product.getId()));
    Optional<Model> oModel = modelService.findModelById(modelAndStripsForProduct.getModel().getId());
    warehouseService.saveFromProduct(product, oModel.get());
    return productMapper.toProductCardObjectDTO(productRepository.save(product));
  }

  private String createCodeBarNumber(Long id) {
    return String.format("%012d", id);
  }

  @Override
  @Transactional
  public void deleteProduct(Long id) {

    Optional<Product> op = this.findProductById(id);
    if (op.isPresent()) {
      Product productDb = op.get();
      try {
        String awsKey = productDb.getUrlFile().substring(productDb.getUrlFile().lastIndexOf("/") + 1);
        s3service.deleteFile(awsKey);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }

    productRepository.deleteById(id);
    warehouseService.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<Product> updateCompleteProduct(Long id, Product product) {
    Optional<Product> o = this.findProductById(id);

    if (o.isPresent()) {
      Product productDb = o.get();
      productDb.setFabric(product.getFabric());
      productDb.setModelAndStripsForProduct(product.getModelAndStripsForProduct());

      return Optional.of(this.saveProduct(productDb));
    }
    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<Product> updateProduct(Long id, Optional<Fabric> fabric,
      Optional<ModelAndStripsForProduct> modelAndStripsForProduct) {
    Optional<Product> o = this.findProductById(id);

    logger.info("Fabric ES: " + fabric.toString());

    if (o.isPresent()) {
      Product productDb = o.get();
      fabric.ifPresent(op -> productDb.setFabric(op));
      modelAndStripsForProduct.ifPresent(op -> productDb.setModelAndStripsForProduct(op));
      return Optional.of(this.saveProduct(productDb));
    }

    return Optional.empty();
  }

  @Override
  public ProductCardObjectDto updateProductWithImg(Long id, String nombre, String colorForro, MultipartFile imageFile,
      Fabric fabric,
      ModelAndStripsForProduct modelAndStripsForProduct) {

    Optional<Product> o = this.findProductById(id);
    Product productDb = o.orElseThrow();
    if (o.isPresent()) {
      System.out.println("--------------------------------");
      System.out.println("EN EL IF DE O.ISPRESENT");
      System.out.println("--------------------------------");
      productDb.setNombre(nombre);
      productDb.setColorForro(colorForro);
      productDb.setFabric(fabric);
      productDb.setModelAndStripsForProduct(modelAndStripsForProduct);
      if (imageFile != null) {
        if (productDb.getUrlFile() != null) {
          String key = productDb.getUrlFile().substring(productDb.getUrlFile().lastIndexOf("/") + 1);
          try {
            productDb.setUrlFile(s3service.uploadFile(key, imageFile));
            ;
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else {
          try {
            productDb.setUrlFile(s3service.uploadFile(s3service.keyGenerator(productDb.getNombre(), id), imageFile));
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }

      }
    }

    return productMapper.toProductCardObjectDTO(this.saveProduct(productDb));
  }

  @Override
  public List<Product> findByString(String string) {
    List<Product> products = productRepository.findByName(string.toLowerCase());
    return products;
  }

  @Override
  public Page<Product> findPageByString(String string, int page, int size) {
    if (string == null || string.isEmpty()) {
      return Page.empty();
    }
    TipoPrenda tipoPrenda = null;
    try {
      tipoPrenda = TipoPrenda.valueOf(string.toUpperCase());
    } catch (Exception e) {
      // TODO: handle exception
    }

    Pageable pageable = PageRequest.of(page, size);

    if (tipoPrenda != null) {
      return productRepository.findByTipoPrenda(tipoPrenda, pageable);
    }

    return productRepository.findPageByName(string, pageable);
  }

  @Override
  public Page<Product> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return productRepository.findAll(pageable);
  }

  @Override
  public Double productCostCalculator(Long id) {
    Optional<Product> o = this.findProductById(id);
    if (!o.isPresent()) {
      throw new ObjectNotFoundException(id, null);
    }
    Product product = o.get();
    List<Lote> lotesWithXProduct = loteRepository.findLotesByProductId(id);
    if (lotesWithXProduct.size() == 0) {
      throw new InsufficientInformationToCalculateCost(
          "Insufficient information: it should be al least one Lote information to do cost");

    }

    // BASE COST
    Integer totalLayers = 0;
    Integer totalAmount = 0;

    for (Lote lote : lotesWithXProduct) {
      Integer layers = lote.getCutSpreadSheet().getFabricLengthDetails().stream()
          .filter(fDetails -> fDetails.getFabric().getId() == product.getFabric().getId())
          .map(FabricLengthDetails::getNumberOfLayers)
          .findFirst()
          .orElse(null);

      int amount = lote.getCutSpreadSheet().getAmountPerSizeForProducts().stream()
          .flatMap(amountPerSizeForProduct -> amountPerSizeForProduct.getAmountPerSize().values().stream())
          .mapToInt(Integer::intValue)
          .sum();

      totalLayers = totalLayers + layers;
      totalAmount = totalAmount + amount;
    }

    // System.out.println("TOTAL LAYERS");
    System.out.println(totalLayers);
    // System.out.println("TOTAL AMOUNTS");
    System.out.println(totalAmount);
    Double mtsPerProduct = (totalLayers * lotesWithXProduct.get(0).getCutSpreadSheet().getTableLength()) / totalAmount;
    // System.out.println("MTS PER PRODUCT");
    System.out.println(mtsPerProduct);

    // BASE COST RESULT
    Double BaseCostPerProduct = mtsPerProduct * product.getFabric().getPrecio();

    // STRIP COST
    Map<String, Integer> aMap = new HashMap<>();
    for (AmountPerSizeForProduct amount : lotesWithXProduct.get(0).getCutSpreadSheet().getAmountPerSizeForProducts()) {
      if (amount.getProduct().getId() == product.getId()) {
        for (Map.Entry<String, Integer> entry : amount.getAmountPerSize().entrySet()) {
          aMap.put(entry.getKey(), entry.getValue());
        }
      }
    }

    Integer totalProductsOnAMap = aMap.values().stream()
        .mapToInt(Integer::intValue)
        .sum();

    Double totalCotsOnStrips = 0.0;
    for (StripDetailsForProduct stripDetail : product.getModelAndStripsForProduct().getStripDetailsForProducts()) {
      Double mtsAccumulator = 0.0;
      // System.out.println("FIRST FOR");
      for (Map.Entry<String, Double> entryDetails : stripDetail.getQuantityPerSize().entrySet()) {
        // System.out.println("SECOND FOR");
        for (Map.Entry<String, Integer> entryAMap : aMap.entrySet()) {
          // System.out.println("THIRD FOR");
          if (entryDetails.getKey().equalsIgnoreCase(entryAMap.getKey())) {
            // System.out.println("ENTER IF");
            mtsAccumulator = mtsAccumulator + (entryDetails.getValue() * entryAMap.getValue()) * stripDetail.getWidth();
          }
        }
      }
      // TOTAL COST STRIPS RESULT
      totalCotsOnStrips = totalCotsOnStrips
          + ((mtsAccumulator / 100) * (stripDetail.getFabric().getPrecio() / totalProductsOnAMap));
    }

    // System.out.println("MAP DE TALLES CANTIDADES");
    // System.out.println(aMap);

    // INPUTS COST

    Double totalInputsCost = 0.0;
    // System.out.println("CANTIDAD DETALLE INSUMOS");
    // System.out.println(product.getModelAndStripsForProduct().getModel().getDetalleInsumos().size());
    for (DetalleInsumo detalleInsumo : product.getModelAndStripsForProduct().getModel().getDetalleInsumos()) {
      // System.out.println(detalleInsumo.toString());
      if (detalleInsumo.getCantidad() != null) {
        if (detalleInsumo.getInput() instanceof Corredera) {
          // System.out.println("instance of" +
          // detalleInsumo.getInput().getClass().toString());
          Corredera corredera = (Corredera) detalleInsumo.getInput();
          totalInputsCost = totalInputsCost + (corredera.getPrecioUni() * detalleInsumo.getCantidad());
        } else if (detalleInsumo.getInput() instanceof Elastico) {
          // Elastico elastico = (Elastico) detalleInsumo.getInput();
          // totalInputsCost = totalInputsCost + (elastico.getPrecioMtr() *
          // (detalleInsumo.getCantidad() * 100));
        } else if (detalleInsumo.getInput() instanceof Argolla) {
          System.out.println("instance of" + detalleInsumo.getInput().getClass().toString());

          Argolla argolla = (Argolla) detalleInsumo.getInput();
          totalInputsCost = totalInputsCost + (argolla.getPrecioUni() * detalleInsumo.getCantidad());
        } else if (detalleInsumo.getInput() instanceof Gancho) {
          // System.out.println("instance of" +
          // detalleInsumo.getInput().getClass().toString());

          Gancho gancho = (Gancho) detalleInsumo.getInput();
          totalInputsCost = totalInputsCost + (gancho.getPrecioUni() * detalleInsumo.getCantidad());
        } else if (detalleInsumo.getInput() instanceof Etiqueta) {
          System.out.println("instance of" + detalleInsumo.getInput().getClass().toString());

          Etiqueta etiqueta = (Etiqueta) detalleInsumo.getInput();
          totalInputsCost = totalInputsCost + (etiqueta.getPrecioUnidad() * detalleInsumo.getCantidad());
        } else if (detalleInsumo.getInput() instanceof Aplique) {
          System.out.println("instance of" + detalleInsumo.getInput().getClass().toString());

          Aplique aplique = (Aplique) detalleInsumo.getInput();
          totalInputsCost = totalInputsCost + (aplique.getPrecioUnidad() * detalleInsumo.getCantidad());
        }
      } else {
        if (detalleInsumo.getInput() instanceof Elastico) {
          Double totalCostElasticoMap = 0.0;

          System.out.println("instance of" + detalleInsumo.getInput().getClass().toString());

          Elastico elastico = (Elastico) detalleInsumo.getInput();

          for (Map.Entry<String, Integer> entry : aMap.entrySet()) {
            System.out.println(entry);
            for (Map.Entry<String, Integer> entryDetails : detalleInsumo.getCantidadPorTalle().entrySet()) {
              if (entry.getKey().equalsIgnoreCase(entryDetails.getKey())) {
                // System.out.println(totalCostElasticoMap);
                // System.out.println("ENTRY DETAILS");
                // System.out.println(entryDetails.getValue());
                // System.out.println("ENTRY");
                // System.out.println(entry.getValue());
                // System.out.println(entryDetails.getValue() / 100);
                totalCostElasticoMap = totalCostElasticoMap
                    + ((entryDetails.getValue() / 100.0) * entry.getValue() * elastico.getPrecioMtr());
                // totalCost
              }
            }
          }
          // totalCostElasticoMap = totalCostElasticoMap + (((entryDetails.getValue() /
          // 100) * entry.getValue()) * elastico.getPrecioMtr());
          totalInputsCost = totalInputsCost + (totalCostElasticoMap / totalProductsOnAMap);
        }
      }
    }

    // System.out.println();

    return totalInputsCost + totalCotsOnStrips + BaseCostPerProduct;
    // return totalCotsOnStrips;
  }

  @Override
  public List<ProductNameModelImgType> findBySeasonDto(String season) {
    return productMapper.toProductNameModelImgTypesList(productRepository.findBySeason(season));
  }

  @Override
  public Page<ProductCardObjectDto> findAllProductCardDtoPage(int page, int size) {
    // return productMapper.toPageProductCardDto(this.findAll(page, size));
    return productMapper.toPageProductCardObjectDto(this.findAll(page, size));
  }

  @Override
  public Page<ProductCardObjectDto> findProductCardDtoPageByString(String string, int page, int size) {
    return productMapper.toPageProductCardObjectDto(this.findPageByString(string, page, size));
  }

}
