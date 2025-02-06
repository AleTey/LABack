package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.StripDetailsForProductDTO;
import com.backend.la.backendloveafrica.models.entities.StripDetailsForProduct;

@Component
public class StripDetailsForProductMapper {

  @Autowired
  private FabricMapper fabricMapper;

  public StripDetailsForProductDTO toStripDetailsForProductDTO(StripDetailsForProduct stripDetailsForProduct) {
    return new StripDetailsForProductDTO(stripDetailsForProduct.getId(),
        fabricMapper.convertFabricToFabricDto(stripDetailsForProduct.getFabric()),
        stripDetailsForProduct.getWidth(),
        stripDetailsForProduct.getQuantityPerSize());
  }

  public List<StripDetailsForProductDTO> toStripDetailsForProductDtoList(
      List<StripDetailsForProduct> stripDetailsForProducts) {
    return stripDetailsForProducts.stream()
        .map(this::toStripDetailsForProductDTO)
        .collect(Collectors.toList());
  }

}
