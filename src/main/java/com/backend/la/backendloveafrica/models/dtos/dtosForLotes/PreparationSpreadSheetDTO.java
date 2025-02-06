package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import java.time.LocalDate;
import java.util.List;

import com.backend.la.backendloveafrica.models.dtos.InputIdNombreDetalleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreparationSpreadSheetDTO {

  private Long id;

  private List<AmountPerSizeForProductDTO> amountPerSizeForProductDTOs;

  private List<InputQuantityForSpreadSheetDTO> inputQuantityForSpreadSheetDTOs;

  private String details;

  private LocalDate publicationDate;

  private boolean isFinished;

  private byte[] img;

}
