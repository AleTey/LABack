package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import com.backend.la.backendloveafrica.models.dtos.InputIdNombreDetalleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputQuantityForSpreadSheetDTO {

  private Long id;

  private InputIdNombreDetalleDTO inputIdNombreDetalleDTO;

  private Double quantity;

}
