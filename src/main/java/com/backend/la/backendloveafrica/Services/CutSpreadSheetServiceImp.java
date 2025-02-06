package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.mapper.AmountPerSizeForProductMapper;
import com.backend.la.backendloveafrica.mapper.FabricLengthDetailsMapper;
import com.backend.la.backendloveafrica.models.dtos.FabricNombreCodigoTipoImgDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.AmountPerSizeForProductDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.CutSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.FabricLengthDetailsDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ProductForLoteDTO;
import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;
import com.backend.la.backendloveafrica.models.entities.CutSpreadSheet;
import com.backend.la.backendloveafrica.models.entities.Fabric;
import com.backend.la.backendloveafrica.models.entities.FabricLengthDetails;
import com.backend.la.backendloveafrica.models.entities.Product;
import com.backend.la.backendloveafrica.repositories.ICutSpreadSheetRepository;

@Service
public class CutSpreadSheetServiceImp implements ICutSpreadSheetService {

  @Autowired
  private ICutSpreadSheetRepository cutSpreadSheetRepository;

  @Autowired
  private AmountPerSizeForProductMapper amountPerSizeForProductMapper;

  @Autowired
  private FabricLengthDetailsMapper fabricLengthDetailsMapper;

  @Autowired
  private IAmountPerSizeForProductService amountPerSizeForProductService;

  @Override
  public List<CutSpreadSheet> findAll() {
    return cutSpreadSheetRepository.findAll();
  }

  @Override
  public Optional<CutSpreadSheet> findById(Long id) {
    return cutSpreadSheetRepository.findById(id);
  }

  @Override
  public CutSpreadSheet save(CutSpreadSheet cutSpreadSheet) {
    return cutSpreadSheetRepository.save(cutSpreadSheet);
  }

  @Override
  public void deleteById(Long id) {
    cutSpreadSheetRepository.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<CutSpreadSheet> update(CutSpreadSheet cutSpreadSheet) {
    System.out.println(cutSpreadSheet.toString());
    Optional<CutSpreadSheet> o = this.findById(cutSpreadSheet.getId());
    System.out.println("ENTRANDO UPDATE SERVICE");

    if (o.isPresent()) {
      System.out.println("COMIENZO CAMBIOS");
      CutSpreadSheet cutSpreadSheetDb = o.get();
      // cutSpreadSheetDb.setAmountPerSizeForProducts(cutSpreadSheet.getAmountPerSizeForProducts());
      // cutSpreadSheetDb.setFabricLengthDetails(cutSpreadSheet.getFabricLengthDetails());

      cutSpreadSheetDb.getAmountPerSizeForProducts().clear();
      cutSpreadSheetDb.getAmountPerSizeForProducts().addAll(cutSpreadSheet.getAmountPerSizeForProducts());
      System.out.println("1");
      cutSpreadSheetDb.getFabricLengthDetails().clear();
      cutSpreadSheetDb.getFabricLengthDetails().addAll(cutSpreadSheet.getFabricLengthDetails());
      System.out.println("2");
      cutSpreadSheetDb.setTableLength(cutSpreadSheet.getTableLength());
      System.out.println("3");
      cutSpreadSheetDb.setDetails(cutSpreadSheet.getDetails());
      // System.out.println("4");
      // cutSpreadSheet.setPublicationDate(cutSpreadSheet.getPublicationDate());
      // System.out.println("5");
      // cutSpreadSheet.setFinished(cutSpreadSheet.isFinished());
      System.out.println("TERMINANDO CAMBIOS");
      return Optional.of(this.save(cutSpreadSheetDb));
    }
    return Optional.empty();
  }

  @Override
  public Optional<CutSpreadSheetDTO> findDtoById(Long id) {
    Optional<CutSpreadSheet> o = this.findById(id);
    if (o.isPresent()) {
      return Optional.of(convertCutSpreadSheetToDTO(o));
    }
    return Optional.empty();
  }

  public CutSpreadSheetDTO convertCutSpreadSheetToDTO(Optional<CutSpreadSheet> o) {
    CutSpreadSheetDTO cutSpreadSheetDTO = new CutSpreadSheetDTO();
    cutSpreadSheetDTO.setId(o.get().getId());
    cutSpreadSheetDTO.setAmountPerSizeForProductDTO(convertListAmountToListDTO(o.get().getAmountPerSizeForProducts()));
    cutSpreadSheetDTO.setFabricLengthDetailsDTOs(convertFabricLengthDetailsToListDto(o.get().getFabricLengthDetails()));
    cutSpreadSheetDTO.setDetails(o.get().getDetails());
    cutSpreadSheetDTO.setPublicationDate(o.get().getPublicationDate());
    cutSpreadSheetDTO.setFinished(o.get().isFinished());
    // cutSpreadSheetDTO.setTableLength(o.get().getTableLength());
    cutSpreadSheetDTO.setTableLength(o.get().getTableLength());
    return cutSpreadSheetDTO;
  }

  public List<FabricLengthDetailsDTO> convertFabricLengthDetailsToListDto(
      List<FabricLengthDetails> fabricLengthDetailsList) {
    List<FabricLengthDetailsDTO> fabricLengthDetailsDTOList = fabricLengthDetailsList.stream()
        .map(detail -> {

          return new FabricLengthDetailsDTO(detail.getId(), convertFabricToFabricDto(detail.getFabric()),

              detail.getNumberOfLayers());
        }).collect(Collectors.toList());

    return fabricLengthDetailsDTOList;
  }

  public FabricNombreCodigoTipoImgDTO convertFabricToFabricDto(Fabric fabric) {
    return new FabricNombreCodigoTipoImgDTO(fabric.getId(), fabric.getNombre(), fabric.getCodeBarNumb(),
        fabric.getTipo(), fabric.getImg(), fabric.getUrlFile());
  }

  public List<AmountPerSizeForProductDTO> convertListAmountToListDTO(
      List<AmountPerSizeForProduct> amountPerSizeForProductsList) {
    List<AmountPerSizeForProductDTO> amountDtoList = amountPerSizeForProductsList.stream()
        .map(amount -> {
          return convertAmountPerSizeToDTO(amount);
        }).collect(Collectors.toList());
    return amountDtoList;
  }

  public AmountPerSizeForProductDTO convertAmountPerSizeToDTO(AmountPerSizeForProduct amountPerSizeForProduct) {
    AmountPerSizeForProductDTO amountPerSizeDTO = new AmountPerSizeForProductDTO(amountPerSizeForProduct.getId(),
        convertProductToProductDTO(amountPerSizeForProduct.getProduct()), amountPerSizeForProduct.getAmountPerSize());
    return amountPerSizeDTO;
  }

  private ProductForLoteDTO convertProductToProductDTO(Product product) {
    return new ProductForLoteDTO(product.getId(), product.getNombre(), product.getImg(),
        product.getModelAndStripsForProduct().getModel().getTipoPrenda(), product.getUrlFile());
  }

  @Override
  public Optional<CutSpreadSheet> updateWithDto(CutSpreadSheetDTO cutSpreadSheet) {
    Optional<CutSpreadSheet> o = this.findById(cutSpreadSheet.getId());
    System.out.println("ENTRANDO UPDATE SERVICE");

    if (o.isPresent()) {
      System.out.println("COMIENZO CAMBIOS");
      CutSpreadSheet cutSpreadSheetDb = o.get();

      List<AmountPerSizeForProduct> amountPerSizeForProduct = cutSpreadSheet.getAmountPerSizeForProductDTO().stream()
          .map(a -> {
            AmountPerSizeForProduct newAmount = amountPerSizeForProductService
                .update(amountPerSizeForProductMapper.fromDtoToAmountPerSizeForProductClass(a)).orElseThrow();

            return newAmount;
          }).collect(Collectors.toList());

      cutSpreadSheetDb.getAmountPerSizeForProducts().clear();
      cutSpreadSheetDb.getAmountPerSizeForProducts()
          .addAll(amountPerSizeForProduct);
      System.out.println("CAMBIO 1");
      // cutSpreadSheetDb.getFabricLengthDetails().clear();
      // cutSpreadSheetDb.getFabricLengthDetails().addAll(fabricLengthDetailsMapper
      //     .listFabricLengthDetailsDtoToFabricLengthDetailsClass(cutSpreadSheet.getFabricLengthDetailsDTOs()));
      System.out.println("CAMBIO 2");
      cutSpreadSheetDb.setTableLength(cutSpreadSheet.getTableLength());
      System.out.println("CAMBIO 3");

      cutSpreadSheetDb.setDetails(cutSpreadSheet.getDetails());
      System.out.println("CAMBIO 4");

      cutSpreadSheet.setPublicationDate(cutSpreadSheet.getPublicationDate());
      System.out.println("CAMBIO 5");

      cutSpreadSheet.setFinished(cutSpreadSheet.isFinished());
      System.out.println("TERMINANDO CAMBIOS");
      return Optional.of(this.save(cutSpreadSheetDb));
    }
    return Optional.empty();
  }

}
