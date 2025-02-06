package com.backend.la.backendloveafrica.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.ProductNameModelImgType;
import com.backend.la.backendloveafrica.models.dtos.ProductSetDTO;
import com.backend.la.backendloveafrica.models.entities.ProductSet;

@Component
public class ProductSetMapper {

  @Autowired
  private ProductMapper productMapper;

  public ProductSetDTO toProductSetDTO(ProductSet productSet) {
    Set<ProductNameModelImgType> productsDto = productSet.getProducts().stream()
        .map(product -> productMapper.toProductNameModelImgType(product))
        .collect(Collectors.toSet());

    return new ProductSetDTO(productSet.getId(), productsDto);
  }

  public List<ProductSetDTO> toProductSetDTOsList(List<ProductSet> productsSet) {
    // Set<ProductSet> productsSet = new HashSet<>(productsSetList);
    return productsSet.stream()
        .map(productSet -> this.toProductSetDTO(productSet))
        .collect(Collectors.toList());
  }

}
