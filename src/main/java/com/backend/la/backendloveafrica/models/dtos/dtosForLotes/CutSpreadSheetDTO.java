package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CutSpreadSheetDTO {

  private Long id;

  private List<AmountPerSizeForProductDTO> amountPerSizeForProductDTO;

  private List<FabricLengthDetailsDTO> fabricLengthDetailsDTOs;

  private Double tableLength;

  private String details;

  private LocalDate publicationDate;

  private boolean isFinished;

}
