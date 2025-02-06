package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import com.backend.la.backendloveafrica.models.dtos.FabricNombreCodigoTipoImgDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FabricLengthDetailsDTO {

  private Long id;

  private FabricNombreCodigoTipoImgDTO fabricNombreCodigoTipoImgDTO;

  private Integer numberOfLayers;

}
