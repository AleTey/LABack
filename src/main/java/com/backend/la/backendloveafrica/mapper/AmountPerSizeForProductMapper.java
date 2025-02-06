package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.AmountPerSizeForProductDTO;
import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;
import com.backend.la.backendloveafrica.models.entities.Product;

@Component
public class AmountPerSizeForProductMapper {

  @Autowired
  private ProductMapper productMapper;

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
        productMapper.convertProductToProductDTO(amountPerSizeForProduct.getProduct()),
        amountPerSizeForProduct.getAmountPerSize());

    return amountPerSizeDTO;
  }

  public AmountPerSizeForProduct fromDtoToAmountPerSizeForProductClass(
      AmountPerSizeForProductDTO amountPerSizeForProductDTO) {
    AmountPerSizeForProduct amountPerSizeForProduct = new AmountPerSizeForProduct();
    amountPerSizeForProduct.setAmountPerSize(amountPerSizeForProductDTO.getAmountPerSize());
    amountPerSizeForProduct.setId(amountPerSizeForProductDTO.getId());

    return amountPerSizeForProduct;
  }

  public List<AmountPerSizeForProduct> listDtoToAmountPerSizeForProductsClass(
      List<AmountPerSizeForProductDTO> amountPerSizeForProductDTOs) {
    List<AmountPerSizeForProduct> amountPerSizeForProductsList = amountPerSizeForProductDTOs.stream()
        .map(a -> {
          return this.fromDtoToAmountPerSizeForProductClass(a);
        }).collect(Collectors.toList());
    return amountPerSizeForProductsList;
  }

}
