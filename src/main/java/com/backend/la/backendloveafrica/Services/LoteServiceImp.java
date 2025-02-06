package com.backend.la.backendloveafrica.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.Exception.IllegalStatusException;
import com.backend.la.backendloveafrica.Exception.ResourceNotFoundException;
import com.backend.la.backendloveafrica.Services.security.IUserService;
import com.backend.la.backendloveafrica.mapper.ControlSpreadSheetMapper;
import com.backend.la.backendloveafrica.mapper.LoteMapper;
import com.backend.la.backendloveafrica.mapper.PreparationSpreadSheetMapper;
import com.backend.la.backendloveafrica.mapper.ProductMapper;
import com.backend.la.backendloveafrica.mapper.WorkShopSpreadSheetMapper;
import com.backend.la.backendloveafrica.models.dtos.LoteCreationDTO;
// import com.backend.la.backendloveafrica.models.dtos.ProductDtoWithTotalAmountPerSize;
import com.backend.la.backendloveafrica.models.dtos.ProductDtoWithTotalAmountPerSizeProduced;
import com.backend.la.backendloveafrica.models.dtos.ProductNameModelImgType;
import com.backend.la.backendloveafrica.models.dtos.TotalAmountProduced;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.CutSpreadSheetForDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.LoteResponseDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ProductForLoteDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.WorkShopMapper;
import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;
import com.backend.la.backendloveafrica.models.entities.ControlSpreadSheet;
import com.backend.la.backendloveafrica.models.entities.CutSpreadSheet;
import com.backend.la.backendloveafrica.models.entities.Fabric;
import com.backend.la.backendloveafrica.models.entities.FabricLengthDetails;
import com.backend.la.backendloveafrica.models.entities.InputQuantityForSpreadSheet;
import com.backend.la.backendloveafrica.models.entities.Lote;
import com.backend.la.backendloveafrica.models.entities.PreparationSpreadSheet;
import com.backend.la.backendloveafrica.models.entities.Product;
import com.backend.la.backendloveafrica.models.entities.WorkShopSpreadSheet;
import com.backend.la.backendloveafrica.models.enums.AmountPerSizeForProductType;
import com.backend.la.backendloveafrica.models.enums.LoteState;
import com.backend.la.backendloveafrica.repositories.ILoteRepository;

@Service
public class LoteServiceImp implements ILoteService {

  @Autowired
  private ILoteRepository loteRepository;

  @Autowired
  private IProductService productService;

  @Autowired
  private PreparationSpreadSheetMapper preparationSpreadSheetMapper;

  @Autowired
  private WorkShopSpreadSheetMapper workShopSpreadSheetMapper;

  @Autowired
  private ControlSpreadSheetMapper controlSpreadSheetMapper;

  @Autowired
  private WorkShopMapper workShopMapper;

  @Autowired
  private LoteMapper loteMapper;

  @Autowired
  private IUserService userService;

  @Autowired
  private ProductMapper productMapper;

  @Override
  public List<Lote> findAll() {
    return loteRepository.findAll();
  }

  @Override
  public Optional<Lote> findById(Long id) {
    return loteRepository.findById(id);
  }

  @Transactional
  public Lote saveForUpdate(Lote lote) {
    return loteRepository.save(lote);
  }

  @Override
  @Transactional
  public LoteResponseDTO save(LoteCreationDTO loteCreationDTO) {
    Lote lote = new Lote();
    List<Product> completeProductsList = getAllCompleteProducts(loteCreationDTO.getProducts());
    lote.setProducts(getAllCompleteProducts(loteCreationDTO.getProducts()));
    lote.setWorkShop(loteCreationDTO.getWorkShop());
    lote.setAdditionalDetails(loteCreationDTO.getAdditionalDetails());
    lote.setFinished(false);
    lote.setCreationDate(LocalDate.now());
    lote.setStatus(LoteState.COLA);

    System.out.println("STEP 1");
    CutSpreadSheet cutSpreadSheet = new CutSpreadSheet();
    PreparationSpreadSheet preparationSpreadSheet = new PreparationSpreadSheet();
    WorkShopSpreadSheet workShopSpreadSheet = new WorkShopSpreadSheet();
    ControlSpreadSheet controlSpreadSheet = new ControlSpreadSheet();

    List<AmountPerSizeForProduct> amountPerSizeForProductsList = new ArrayList<>();

    amountPerSizeForProductsList = lote.getProducts().stream()
        .map(product -> {
          AmountPerSizeForProduct amountPerSizeForProduct = new AmountPerSizeForProduct();
          amountPerSizeForProduct.setProduct(product);
          amountPerSizeForProduct.setCollection(product.getFabric().getTemporada());
          amountPerSizeForProduct.setType(AmountPerSizeForProductType.CORTE);

          Map<String, Integer> amountPerSize = new HashMap<>();
          amountPerSize = amountPerSizeForProduct.getProduct().getModelAndStripsForProduct()
              .getModel().getTallesDisponibles().stream()
              .collect(Collectors.toMap(talle -> talle, talle -> 0));

          amountPerSizeForProduct.setAmountPerSize(amountPerSize);

          return amountPerSizeForProduct;
        }).collect(Collectors.toList());

    List<AmountPerSizeForProduct> preparationAmountPerSizeForProductsList = amountPerSizeForProductsList.stream()
        .map(cutAmountPerSizeForProduct -> {
          AmountPerSizeForProduct amountPerSizeForProduct = new AmountPerSizeForProduct();
          amountPerSizeForProduct.setCollection(cutAmountPerSizeForProduct.getProduct().getFabric().getTemporada());
          amountPerSizeForProduct.setType(AmountPerSizeForProductType.PREPARADO);
          amountPerSizeForProduct.setProduct(cutAmountPerSizeForProduct.getProduct());
          amountPerSizeForProduct.setAmountPerSize(new HashMap<>(cutAmountPerSizeForProduct.getAmountPerSize()));

          return amountPerSizeForProduct;
        }).collect(Collectors.toList());

    List<AmountPerSizeForProduct> workshopAmountPerSizeForProductList = amountPerSizeForProductsList.stream()
        .map(amountPerSize -> {
          AmountPerSizeForProduct amountPerSizeForProduct = new AmountPerSizeForProduct();
          amountPerSizeForProduct.setProduct(amountPerSize.getProduct());
          amountPerSizeForProduct.setCollection(amountPerSize.getProduct().getFabric().getTemporada());
          amountPerSizeForProduct.setType(AmountPerSizeForProductType.TALLER);
          amountPerSizeForProduct.setAmountPerSize(new HashMap<>(amountPerSize.getAmountPerSize()));
          return amountPerSizeForProduct;
        })
        .collect(Collectors.toList());
    List<AmountPerSizeForProduct> workshopAmountPerSizeDefectiveForProductList = amountPerSizeForProductsList.stream()
        .map(amountPerSize -> {
          AmountPerSizeForProduct amountPerSizeForProduct = new AmountPerSizeForProduct();
          amountPerSizeForProduct.setProduct(amountPerSize.getProduct());
          amountPerSizeForProduct.setCollection(amountPerSize.getProduct().getFabric().getTemporada());
          amountPerSizeForProduct.setType(AmountPerSizeForProductType.TALLER_ERRORES);
          amountPerSizeForProduct.setAmountPerSize(new HashMap<>(amountPerSize.getAmountPerSize()));
          return amountPerSizeForProduct;
        })
        .collect(Collectors.toList());

    List<AmountPerSizeForProduct> ControlAmountPerSizeDefectiveForProductList = amountPerSizeForProductsList.stream()
        .map(amountPerSize -> {
          AmountPerSizeForProduct amountPerSizeForProduct = new AmountPerSizeForProduct();
          amountPerSizeForProduct.setProduct(amountPerSize.getProduct());
          amountPerSizeForProduct.setCollection(amountPerSize.getProduct().getFabric().getTemporada());
          amountPerSizeForProduct.setType(AmountPerSizeForProductType.CONTROL);
          amountPerSizeForProduct.setAmountPerSize(new HashMap<>(amountPerSize.getAmountPerSize()));
          return amountPerSizeForProduct;
        })
        .collect(Collectors.toList());

    Set<Fabric> seenFabrics = new HashSet<>();
    List<FabricLengthDetails> fabricLengthDetailsList = new ArrayList<FabricLengthDetails>();
    fabricLengthDetailsList = completeProductsList.stream()
        .filter(p -> seenFabrics.add(p.getFabric()))
        .map(p -> {
          FabricLengthDetails fabricLengthDetails = new FabricLengthDetails();
          fabricLengthDetails.setNumberOfLayers(0);
          fabricLengthDetails.setFabric(p.getFabric());
          return fabricLengthDetails;
        })
        .collect(Collectors.toList());
    System.out.println("STEP 2");
    cutSpreadSheet.setAmountPerSizeForProducts(amountPerSizeForProductsList);
    cutSpreadSheet.setFabricLengthDetails(fabricLengthDetailsList);

    preparationSpreadSheet.setAmountPerSizeForProducts(preparationAmountPerSizeForProductsList);
    preparationSpreadSheet.setInputQuantityForSpreadSheet(prepareInputQuantityInitialState(completeProductsList));

    workShopSpreadSheet.setAmountPerSizeForProducts(workshopAmountPerSizeForProductList);
    workShopSpreadSheet.setAmountPerSizeDefectiveForProducts(workshopAmountPerSizeDefectiveForProductList);

    controlSpreadSheet.setAmountPerSizeReceivedForProducts(ControlAmountPerSizeDefectiveForProductList);

    System.out.println("CONTROL SPREADSHEET");
    System.out.println(controlSpreadSheet.toString());

    System.out.println("STEP 3");
    lote.setCutSpreadSheet(cutSpreadSheet);
    lote.setPreparationSpreadSheet(preparationSpreadSheet);
    lote.setWorkShopSpreadSheet(workShopSpreadSheet);
    lote.setControlSpreadSheet(controlSpreadSheet);
    System.out.println("STEP 4");
    Lote savedLote = new Lote();
    savedLote = loteRepository.save(lote);

    System.out.println("STEP 6");

    return loteMapper.toLoteResponseDTO(savedLote);
  }

  // @Override
  // @Transactional
  // public LoteResponseDTO save(LoteCreationDTO loteCreationDTO) {
  // // Creación inicial del objeto Lote
  // Lote lote = new Lote();
  // List<Product> completeProductsList =
  // getAllCompleteProducts(loteCreationDTO.getProducts());
  // lote.setProducts(completeProductsList);
  // lote.setWorkShop(loteCreationDTO.getWorkShop());
  // lote.setAdditionalDetails(loteCreationDTO.getAdditionalDetails());
  // lote.setFinished(false);
  // lote.setCreationDate(LocalDate.now());
  // lote.setStatus(LoteState.COLA);

  // // Creación de las hojas de cálculo correspondientes
  // CutSpreadSheet cutSpreadSheet = new CutSpreadSheet();
  // PreparationSpreadSheet preparationSpreadSheet = new PreparationSpreadSheet();
  // WorkShopSpreadSheet workShopSpreadSheet = new WorkShopSpreadSheet();
  // ControlSpreadSheet controlSpreadSheet = new ControlSpreadSheet();

  // // Creación y configuración de AmountPerSizeForProduct
  // List<AmountPerSizeForProduct> amountPerSizeForProductsList =
  // completeProductsList.stream()
  // .parallel() // Procesamiento en paralelo para mejorar el rendimiento
  // .map(product -> {
  // AmountPerSizeForProduct amountPerSizeForProduct = new
  // AmountPerSizeForProduct();
  // amountPerSizeForProduct.setProduct(product);
  // amountPerSizeForProduct.setCollection(product.getFabric().getTemporada());
  // amountPerSizeForProduct.setType(AmountPerSizeForProductType.CORTE);

  // Map<String, Integer> amountPerSize = product.getModelAndStripsForProduct()
  // .getModel().getTallesDisponibles().stream()
  // .collect(Collectors.toMap(talle -> talle, talle -> 0));

  // amountPerSizeForProduct.setAmountPerSize(amountPerSize);

  // return amountPerSizeForProduct;
  // }).collect(Collectors.toList());

  // // Mapeo para otras hojas de cálculo basadas en amountPerSizeForProductsList
  // List<AmountPerSizeForProduct> preparationAmountPerSizeForProductsList =
  // amountPerSizeForProductsList.stream()
  // .parallel()
  // .map(cutAmountPerSizeForProduct -> {
  // AmountPerSizeForProduct amountPerSizeForProduct = new
  // AmountPerSizeForProduct();
  // amountPerSizeForProduct.setProduct(cutAmountPerSizeForProduct.getProduct());
  // amountPerSizeForProduct.setCollection(cutAmountPerSizeForProduct.getProduct().getFabric().getTemporada());
  // amountPerSizeForProduct.setType(AmountPerSizeForProductType.PREPARADO);
  // amountPerSizeForProduct.setAmountPerSize(new
  // HashMap<>(cutAmountPerSizeForProduct.getAmountPerSize()));
  // return amountPerSizeForProduct;
  // }).collect(Collectors.toList());

  // List<AmountPerSizeForProduct> workshopAmountPerSizeForProductList =
  // amountPerSizeForProductsList.stream()
  // .parallel()
  // .map(amountPerSize -> {
  // AmountPerSizeForProduct amountPerSizeForProduct = new
  // AmountPerSizeForProduct();
  // amountPerSizeForProduct.setProduct(amountPerSize.getProduct());
  // amountPerSizeForProduct.setCollection(amountPerSize.getProduct().getFabric().getTemporada());
  // amountPerSizeForProduct.setType(AmountPerSizeForProductType.TALLER);
  // amountPerSizeForProduct.setAmountPerSize(new
  // HashMap<>(amountPerSize.getAmountPerSize()));
  // return amountPerSizeForProduct;
  // }).collect(Collectors.toList());

  // List<AmountPerSizeForProduct> workshopAmountPerSizeDefectiveForProductList =
  // amountPerSizeForProductsList.stream()
  // .parallel()
  // .map(amountPerSize -> {
  // AmountPerSizeForProduct amountPerSizeForProduct = new
  // AmountPerSizeForProduct();
  // amountPerSizeForProduct.setProduct(amountPerSize.getProduct());
  // amountPerSizeForProduct.setCollection(amountPerSize.getProduct().getFabric().getTemporada());
  // amountPerSizeForProduct.setType(AmountPerSizeForProductType.TALLER_ERRORES);
  // amountPerSizeForProduct.setAmountPerSize(new
  // HashMap<>(amountPerSize.getAmountPerSize()));
  // return amountPerSizeForProduct;
  // }).collect(Collectors.toList());

  // List<AmountPerSizeForProduct> controlAmountPerSizeDefectiveForProductList =
  // amountPerSizeForProductsList.stream()
  // .parallel()
  // .map(amountPerSize -> {
  // AmountPerSizeForProduct amountPerSizeForProduct = new
  // AmountPerSizeForProduct();
  // amountPerSizeForProduct.setProduct(amountPerSize.getProduct());
  // amountPerSizeForProduct.setCollection(amountPerSize.getProduct().getFabric().getTemporada());
  // amountPerSizeForProduct.setType(AmountPerSizeForProductType.CONTROL);
  // amountPerSizeForProduct.setAmountPerSize(new
  // HashMap<>(amountPerSize.getAmountPerSize()));
  // return amountPerSizeForProduct;
  // }).collect(Collectors.toList());

  // // Eliminación de duplicados usando Set y mapeo de detalles de longitud de
  // tela
  // Set<Fabric> seenFabrics = new HashSet<>();
  // List<FabricLengthDetails> fabricLengthDetailsList =
  // completeProductsList.stream()
  // .filter(p -> seenFabrics.add(p.getFabric())) // Agregar al set si no está
  // presente
  // .map(p -> {
  // FabricLengthDetails fabricLengthDetails = new FabricLengthDetails();
  // fabricLengthDetails.setNumberOfLayers(0);
  // fabricLengthDetails.setFabric(p.getFabric());
  // return fabricLengthDetails;
  // }).collect(Collectors.toList());

  // // Asignación de las listas creadas a las hojas de cálculo
  // cutSpreadSheet.setAmountPerSizeForProducts(amountPerSizeForProductsList);
  // cutSpreadSheet.setFabricLengthDetails(fabricLengthDetailsList);

  // preparationSpreadSheet.setAmountPerSizeForProducts(preparationAmountPerSizeForProductsList);
  // preparationSpreadSheet.setInputQuantityForSpreadSheet(prepareInputQuantityInitialState(completeProductsList));

  // workShopSpreadSheet.setAmountPerSizeForProducts(workshopAmountPerSizeForProductList);
  // workShopSpreadSheet.setAmountPerSizeDefectiveForProducts(workshopAmountPerSizeDefectiveForProductList);

  // controlSpreadSheet.setAmountPerSizeReceivedForProducts(controlAmountPerSizeDefectiveForProductList);

  // // Asignación de las hojas de cálculo al objeto Lote
  // lote.setCutSpreadSheet(cutSpreadSheet);
  // lote.setPreparationSpreadSheet(preparationSpreadSheet);
  // lote.setWorkShopSpreadSheet(workShopSpreadSheet);
  // lote.setControlSpreadSheet(controlSpreadSheet);

  // // Guardado del objeto Lote y mapeo a DTO de respuesta
  // Lote savedLote = loteRepository.save(lote);

  // return loteMapper.toLoteResponseDTO(savedLote);
  // }

  @Override
  public void deleteById(Long id) {
    loteRepository.deleteById(id);
  }

  @Override
  public Optional<Lote> update(Lote lote) {
    Optional<Lote> o = this.findById(lote.getId());

    if (o.isPresent()) {
      Lote loteDb = o.get();
      if (loteDb.getStatus() == LoteState.COLA) {
        loteDb.setProducts(lote.getProducts());
      }
    }

    return Optional.empty();

  }

  @Override
  public Optional<LoteResponseDTO> findByIdResponseDTO(Long id) {
    Optional<Lote> o = this.findById(id);
    if (o.isPresent()) {
      LoteResponseDTO loteResponseDTO = new LoteResponseDTO();
      loteResponseDTO.setId(o.get().getId());
      loteResponseDTO.setProductsForLoteDTO(transformToProductDTO(o.get().getProducts()));

      loteResponseDTO.setCutSpreadsheetForLoteDTO(convertCutSpreedSheetToDTO(o.get().getCutSpreadSheet()));
      loteResponseDTO.setPreparationSpreadSheetForDTO(
          preparationSpreadSheetMapper.toPreparationSpreadSheetForDto(o.get().getPreparationSpreadSheet()));
      loteResponseDTO.setWorkShopSpreadSheetForDTO(
          workShopSpreadSheetMapper.toWorkShopSpreadSheetForDTO(o.get().getWorkShopSpreadSheet()));
      loteResponseDTO.setWorkShopDto(workShopMapper.toWorkshopDto(o.get().getWorkShop()));
      loteResponseDTO.setStatus(o.get().getStatus());
      loteResponseDTO.setFinished(o.get().isFinished());
      loteResponseDTO.setAdditionalDetails(o.get().getAdditionalDetails());
      loteResponseDTO.setControlSpreadSheetForDTO(
          controlSpreadSheetMapper.toControlSpreadSheetForDTO(o.get().getControlSpreadSheet()));
      return Optional.of(loteResponseDTO);
    }
    return Optional.empty();
  }

  public CutSpreadSheetForDTO convertCutSpreedSheetToDTO(CutSpreadSheet cutSpreadSheet) {
    CutSpreadSheetForDTO cutSpreadSheetDTO = new CutSpreadSheetForDTO();
    cutSpreadSheetDTO.setId(cutSpreadSheet.getId());
    cutSpreadSheetDTO.setFinished(cutSpreadSheet.isFinished());

    return cutSpreadSheetDTO;
  }

  public List<ProductForLoteDTO> transformToProductDTO(List<Product> products) {

    List<ProductForLoteDTO> productsDTO = products.stream()
        .map(p -> {
          ProductForLoteDTO productDto = new ProductForLoteDTO();
          productDto.setId(p.getId());
          productDto.setNombre(p.getNombre());
          productDto.setImg(p.getImg());
          productDto.setTipoPrenda(p.getModelAndStripsForProduct().getModel().getTipoPrenda());

          return productDto;
        }).collect(Collectors.toList());

    return productsDTO;
  }

  private List<Product> getAllCompleteProducts(List<Product> idProducts) {

    List<Product> productList = new ArrayList<>();
    productList = idProducts.stream()
        .map(p -> productService.findProductById(p.getId()))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());

    return productList;
  }

  private List<InputQuantityForSpreadSheet> prepareInputQuantityInitialState(List<Product> products) {

    Map<Long, InputQuantityForSpreadSheet> inputQuantityMap = products.stream()
        .flatMap(product -> product.getModelAndStripsForProduct().getModel().getDetalleInsumos().stream()

            .map(detail -> {
              InputQuantityForSpreadSheet input = new InputQuantityForSpreadSheet();
              input.setInput(detail.getInput());
              input.setQuantity(0.0);
              return input;
            }))
        .collect(Collectors.toMap(
            input -> input.getInput().getId(),
            input -> input,
            (existing, replacement) -> existing));
    System.out.println("INPUT QUANTITIES LIST:");
    System.out.println(inputQuantityMap.values().toString());
    return new ArrayList<>(inputQuantityMap.values());
  }

  @Override
  @Transactional(readOnly = true)
  public List<LoteResponseDTO> findLotesByStatus(LoteState state) {
    List<Lote> lotes = loteRepository.findByState(state);
    List<LoteResponseDTO> loteResponseDtoList = loteMapper.toLoteResponseDtoList(lotes);
    return loteResponseDtoList;
  }

  @Override
  @Transactional
  public void changeStatus(Long id, LoteState state) {

    if (state == LoteState.CORTE && loteRepository.existsByStatus(state)) {
      throw new IllegalStatusException("Solo puede haber un lote en corte");
    }

    Optional<Lote> o = this.findById(id);
    Lote loteDb = o.orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado"));

    loteDb.setStatus(state);
    this.saveForUpdate(loteDb);
  }

  @Override
  @Transactional
  public void changeStatusWorkshop(Long id, LoteState state) {

    if (state == LoteState.CORTE && loteRepository.existsByStatus(state)) {
      throw new IllegalStatusException("Solo puede haber un lote en corte");
    }

    Optional<Lote> o = this.findById(id);
    Lote loteDb = o.orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado"));

    String username = userService.getUsernameFromAuthenticatedUser();
    if (!loteDb.getWorkShop().getUser().getUsername().equals(username)) {
      throw new IllegalStatusException("Not the same workshop");
    }

    loteDb.setStatus(state);
    this.saveForUpdate(loteDb);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Lote> findByProduct(Long id) {
    return loteRepository.findLotesByProductId(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<LoteResponseDTO> findLoteByWorkshop() {
    String user = userService.getUsernameFromAuthenticatedUser();
    return loteMapper.toLoteResponseDtoList(loteRepository.findLotesByWorkshopsUsername(user));
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsByStatus(LoteState state) {
    return loteRepository.existsByStatus(state);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Lote> findLoteByCutSpreadSheetId(Long id) {
    return loteRepository.findLoteByCutSpreadSheetId(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<LoteResponseDTO> findByFabricCollection(String collection) {
    return loteMapper.toLoteResponseDtoList(loteRepository.findByFabricCollection(collection));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ProductNameModelImgType> findProductsByStatusLoteAndSeason(String season, LoteState status) {
    return productMapper
        .toProductNameModelImgTypesList(loteRepository.findProductsByStatusLoteAndSeason(season, status));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ProductNameModelImgType> findProductsBySeasonExcludingStatuses(String season, List<LoteState> statuses) {
    return productMapper
        .toProductNameModelImgTypesList(loteRepository.findProductsBySeasonExcludingStatuses(season, statuses));
  }

  @Override
  public Map<String, List<ProductNameModelImgType>> undoPreparingFinishedProducts(String season) {
    List<ProductNameModelImgType> allProductsSeason = productService.findBySeasonDto(season);
    List<ProductNameModelImgType> finishedProducts = this.findProductsByStatusLoteAndSeason(season,
        LoteState.FINALIZADO);

    List<LoteState> excludedStatuses = List.of(LoteState.COLA, LoteState.FINALIZADO);
    List<ProductNameModelImgType> productsBySeasonExcludingFinishedAndQueue = this
        .findProductsBySeasonExcludingStatuses(season, excludedStatuses);
    Set<ProductNameModelImgType> setFinishedProduct = new HashSet<>(finishedProducts);
    Set<ProductNameModelImgType> setPreparingProducts = productsBySeasonExcludingFinishedAndQueue.stream()
        .filter(p -> !setFinishedProduct.contains(p))
        .collect(Collectors.toSet());
    List<ProductNameModelImgType> undoProducts = allProductsSeason.stream()
        .filter(p -> !setFinishedProduct.contains(p))
        .filter(p -> !setPreparingProducts.contains(p))
        .collect(Collectors.toList());
    Map<String, List<ProductNameModelImgType>> generalState = new HashMap<>();
    // generalState.entrySet("undo", undoProducts);
    List<ProductNameModelImgType> preparingProducts = new ArrayList<>(setPreparingProducts);
    generalState.put("undo", undoProducts);
    generalState.put("preparing", preparingProducts);
    generalState.put("finished", finishedProducts);
    return generalState;
  }

  // private List<ProductDtoWithTotalAmountPerSizeProduced> calculateTotalAmountProduced(List<ProductNameModelImgType> productNameModelImgType) {
  //   List<ProductDtoWithTotalAmountPerSizeProduced> list = productNameModelImgType.stream()
  //   .map(p->{
  //     List<Lote> lotes = new ArrayList<>();
  //     lotes = this.findByProduct(p.getId());
  //     TotalAmountProduced amountPerSize = new TotalAmountProduced();
  //       lotes.stream().forEach(lote->
  //       lote.getWorkShopSpreadSheet().getAmountPerSizeDefectiveForProducts().stream()
  //         .forEach(amount->{
  //           if (amount.getProduct().getId() == p.getId()) {
  //             amount.getAmountPerSize().forEach((key, value)->{
  //               if (condition) {
                  
  //               }
  //           });
  //           }
  //         })
  //       );
  // })
  // }

}
