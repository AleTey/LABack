package com.backend.la.backendloveafrica.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.WorkShopSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.WorkShopSpreadSheetForDTO;
import com.backend.la.backendloveafrica.models.entities.WorkShopSpreadSheet;

@Component
public class WorkShopSpreadSheetMapper {

  @Autowired
  private AmountPerSizeForProductMapper amountPerSizeForProductMapper;

  public WorkShopSpreadSheetDTO toWorkShopSpreadSheetDTO(WorkShopSpreadSheet workShopSpreadSheet) {
    WorkShopSpreadSheetDTO workShopSpreadSheetDTO = new WorkShopSpreadSheetDTO();

    workShopSpreadSheetDTO.setId(workShopSpreadSheet.getId());
    workShopSpreadSheetDTO.setAmountPerSizeForProducts(
        amountPerSizeForProductMapper.convertListAmountToListDTO(workShopSpreadSheet.getAmountPerSizeForProducts()));
    workShopSpreadSheetDTO.setAmountPerSizeDefectiveForProducts(amountPerSizeForProductMapper
        .convertListAmountToListDTO(workShopSpreadSheet.getAmountPerSizeDefectiveForProducts()));
    workShopSpreadSheetDTO.setDetails(workShopSpreadSheet.getDetails());
    workShopSpreadSheetDTO.setFinished(workShopSpreadSheet.isFinished());
    workShopSpreadSheetDTO.setPublicationDate(workShopSpreadSheet.getPublicationDate());

    return workShopSpreadSheetDTO;
  }

  public WorkShopSpreadSheetForDTO toWorkShopSpreadSheetForDTO(WorkShopSpreadSheet workShopSpreadSheet) {
    WorkShopSpreadSheetForDTO workShopSpreadSheetForDTO = new WorkShopSpreadSheetForDTO();
    workShopSpreadSheetForDTO.setId(workShopSpreadSheet.getId());
    workShopSpreadSheetForDTO.setFinished(workShopSpreadSheet.isFinished());
    return workShopSpreadSheetForDTO;
  }

}
