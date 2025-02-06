package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.PreparationSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.PreparationSpreadSheetForDTO;
import com.backend.la.backendloveafrica.models.entities.PreparationSpreadSheet;

@Component
public class PreparationSpreadSheetMapper {

  @Autowired
  private AmountPerSizeForProductMapper amountPerSizeMapper;

  @Autowired
  private InputQuantityForSpreadSheetMapper inputQuantityForSpreadSheetMapper;

  public List<PreparationSpreadSheetForDTO> toPreparationSpreadSheetForDtoList(
      List<PreparationSpreadSheet> preparationSpreadSheetList) {
    List<PreparationSpreadSheetForDTO> preparationSpreadSheetForDTOsList = preparationSpreadSheetList.stream()
        .map(sheet -> {
          PreparationSpreadSheetForDTO preparationSpreadSheetForDTO = this.toPreparationSpreadSheetForDto(sheet);
          return preparationSpreadSheetForDTO;
        }).collect(Collectors.toList());
    return preparationSpreadSheetForDTOsList;
  }

  public PreparationSpreadSheetForDTO toPreparationSpreadSheetForDto(PreparationSpreadSheet preparationSpreadSheet) {
    return new PreparationSpreadSheetForDTO(preparationSpreadSheet.getId(), preparationSpreadSheet.isFinished());
  }

  public PreparationSpreadSheetDTO toPreparationSpreadSheetDTO(PreparationSpreadSheet preparationSpreadSheet) {
    if (preparationSpreadSheet == null) {
      return null;
    }
    PreparationSpreadSheetDTO preparationSpreadSheetDTO = new PreparationSpreadSheetDTO();
    preparationSpreadSheetDTO.setId(preparationSpreadSheet.getId());
    preparationSpreadSheetDTO.setAmountPerSizeForProductDTOs(
        amountPerSizeMapper.convertListAmountToListDTO(preparationSpreadSheet.getAmountPerSizeForProducts()));
    preparationSpreadSheetDTO.setInputQuantityForSpreadSheetDTOs(
        inputQuantityForSpreadSheetMapper.ToListDto(preparationSpreadSheet.getInputQuantityForSpreadSheet()));
    preparationSpreadSheetDTO.setDetails(preparationSpreadSheet.getDetails());
    preparationSpreadSheetDTO.setFinished(false);
    preparationSpreadSheetDTO.setImg(preparationSpreadSheet.getImg());

    return preparationSpreadSheetDTO;
  }

}
