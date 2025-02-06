package com.backend.la.backendloveafrica.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ControlSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ControlSpreadSheetForDTO;
import com.backend.la.backendloveafrica.models.entities.ControlSpreadSheet;

@Component
public class ControlSpreadSheetMapper {

  @Autowired
  private AmountPerSizeForProductMapper amountPerSizeForProductMapper;

  public ControlSpreadSheetDTO toControlSpreadSheetDTO(ControlSpreadSheet controlSpreadSheet) {
    ControlSpreadSheetDTO controlSpreadSheetDTO = new ControlSpreadSheetDTO();
    controlSpreadSheetDTO.setId(controlSpreadSheet.getId());
    controlSpreadSheetDTO.setLoteId(controlSpreadSheet.getLote().getId());
    controlSpreadSheetDTO.setAmountPerSizeForProductDTO(amountPerSizeForProductMapper
        .convertListAmountToListDTO(controlSpreadSheet.getAmountPerSizeReceivedForProducts()));
    controlSpreadSheetDTO.setDetails(controlSpreadSheet.getDetails());
    controlSpreadSheetDTO.setFinished(controlSpreadSheet.isFinished());
    controlSpreadSheetDTO.setPublicationDate(controlSpreadSheet.getPublicationDate());
    return controlSpreadSheetDTO;
  }

  public ControlSpreadSheetForDTO toControlSpreadSheetForDTO(ControlSpreadSheet controlSpreadSheet) {
    ControlSpreadSheetForDTO controlSpreadSheetForDTO = new ControlSpreadSheetForDTO();
    controlSpreadSheetForDTO.setId(controlSpreadSheet.getId());
    controlSpreadSheetForDTO.setFinished(controlSpreadSheet.isFinished());
    return controlSpreadSheetForDTO;
  }

}
