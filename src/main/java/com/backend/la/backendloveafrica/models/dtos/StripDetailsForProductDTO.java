package com.backend.la.backendloveafrica.models.dtos;

import java.util.Map;

import com.backend.la.backendloveafrica.models.entities.Fabric;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StripDetailsForProductDTO {

  private Long id;
  
  private FabricNombreCodigoTipoImgDTO fabric;

  private Double width;

  private Map<String, Double> quantityPerSize;
}
