package com.backend.la.backendloveafrica.models.dtos;

import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoWithTotalAmountPerSizeProduced {

  private ProductNameModelImgType product;

  private TotalAmountProduced amountPerSizeForProduct;

}
