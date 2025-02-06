package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ControlSpreadSheetDTO {

  private Long id;

  private Long loteId;

  private List<AmountPerSizeForProductDTO> amountPerSizeForProductDTO;

  private String details;

  private LocalDate publicationDate;

  private boolean isFinished;
}
