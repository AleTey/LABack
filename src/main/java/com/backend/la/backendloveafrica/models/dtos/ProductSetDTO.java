package com.backend.la.backendloveafrica.models.dtos;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSetDTO {

  private Long id;

  private Set<ProductNameModelImgType> products;

}
