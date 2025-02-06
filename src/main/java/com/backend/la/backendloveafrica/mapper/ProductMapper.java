package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.ProductCardDTO;
import com.backend.la.backendloveafrica.models.dtos.ProductCardObjectDto;
import com.backend.la.backendloveafrica.models.dtos.ProductNameModelImgType;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ProductForLoteDTO;
import com.backend.la.backendloveafrica.models.entities.Product;

@Component
public class ProductMapper {

  @Autowired
  private FabricMapper fabricMapper;

  @Autowired
  private ModelAndStripsForProductMapper modelAndStripsForProductMapper;

  public List<ProductForLoteDTO> transformToProductDtoList(List<Product> productsList) {
    List<ProductForLoteDTO> productForLoteDtoList = productsList.stream()
        .map(product -> {
          ProductForLoteDTO productDto = convertProductToProductDTO(product);
          return productDto;
        }).collect(Collectors.toList());
    return productForLoteDtoList;
  }

  public ProductForLoteDTO convertProductToProductDTO(Product product) {
    return new ProductForLoteDTO(product.getId(), product.getNombre(), product.getImg(),
        product.getModelAndStripsForProduct().getModel().getTipoPrenda(), product.getUrlFile());
  }

  public ProductNameModelImgType toProductNameModelImgType(Product product) {
    return new ProductNameModelImgType(product.getId(), product.getNombre(),
        product.getModelAndStripsForProduct().getModel().getNombre(), product.getUrlFile(),
        product.getModelAndStripsForProduct().getModel().getTipoPrenda());
  }

  public List<ProductNameModelImgType> toProductNameModelImgTypesList(List<Product> products) {
    List<ProductNameModelImgType> productsDtoList = products.stream()
        .map(product -> {
          ProductNameModelImgType productDto = toProductNameModelImgType(product);
          return productDto;
        }).collect(Collectors.toList());
    return productsDtoList;
  }

  public ProductCardDTO toProductCardDTO(Product product) {
    return new ProductCardDTO(product.getId(), product.getNombre(), product.getCodeBarNumber(), product.getUrlFile(),
        product.getFechaRegistro(), product.getFabric().getNombre(),
        product.getModelAndStripsForProduct().getModel().getNombre(),
        product.getModelAndStripsForProduct().getModel().getTipoPrenda(), product.getFabric().getTemporada());
  }

  public List<ProductCardDTO> toListProductCardDto(List<Product> products) {
    return products.stream()
        .map(this::toProductCardDTO)
        .collect(Collectors.toList());
  }

  public Page<ProductCardDTO> toPageProductCardDto(Page<Product> productPage) {
    List<ProductCardDTO> products = toListProductCardDto(productPage.getContent());
    Pageable pageable = productPage.getPageable();
    return new PageImpl<>(products, pageable, productPage.getTotalElements());
  }

  public ProductCardObjectDto toProductCardObjectDTO(Product product) {
    return new ProductCardObjectDto(
        product.getId(),
        product.getNombre(),
        product.getCodeBarNumber(),
        product.getColorForro(),
        product.getUrlFile(),
        product.getFechaRegistro(),
        fabricMapper.convertFabricToFabricDto(product.getFabric()),
        modelAndStripsForProductMapper.toModelAndStripsForProductDTO(product.getModelAndStripsForProduct()));
  }

  public List<ProductCardObjectDto> toListProductCardObjectDto(List<Product> products) {
    return products.stream()
        .map(this::toProductCardObjectDTO)
        .collect(Collectors.toList());
  }

  public Page<ProductCardObjectDto> toPageProductCardObjectDto(Page<Product> productPage) {
    List<ProductCardObjectDto> products = toListProductCardObjectDto(productPage.getContent());
    Pageable pageable = productPage.getPageable();
    return new PageImpl<>(products, pageable, productPage.getTotalElements());
  }

}
