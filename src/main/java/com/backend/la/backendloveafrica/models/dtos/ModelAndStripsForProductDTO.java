package com.backend.la.backendloveafrica.models.dtos;

import java.util.List;

import com.backend.la.backendloveafrica.models.entities.StripDetailsForProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelAndStripsForProductDTO {

  private Long id;

  private ModelWithDetalleInsumoDtoDTO model;

  private List<StripDetailsForProductDTO> stripDetailsForProducts;

}
