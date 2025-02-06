package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import java.time.LocalDate;
import java.util.List;

import com.backend.la.backendloveafrica.models.entities.WorkShop;
import com.backend.la.backendloveafrica.models.enums.LoteState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoteResponseDTO {

  private Long id;

  private List<ProductForLoteDTO> productsForLoteDTO;

  private CutSpreadSheetForDTO cutSpreadsheetForLoteDTO;

  private PreparationSpreadSheetForDTO preparationSpreadSheetForDTO;

  private WorkShopSpreadSheetForDTO workShopSpreadSheetForDTO;

  private ControlSpreadSheetForDTO controlSpreadSheetForDTO;

  private WorkShopDTO workShopDto;

  private LoteState status;

  private boolean isFinished;

  private String additionalDetails;

  private LocalDate creationDate;

}
