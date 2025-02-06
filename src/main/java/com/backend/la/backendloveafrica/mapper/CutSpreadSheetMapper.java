package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.CutSpreadSheetForDTO;
import com.backend.la.backendloveafrica.models.entities.CutSpreadSheet;

@Component
public class CutSpreadSheetMapper {

  @Autowired
  AmountPerSizeForProductMapper amountPerSizeForProductMapper;

  public List<CutSpreadSheetForDTO> toListCutSpreadsheetForDTO(List<CutSpreadSheet> cutSpreadSheets) {
    List<CutSpreadSheetForDTO> cutSpreadSheetForDtoList = cutSpreadSheets.stream()
        .map(sheet -> {
          CutSpreadSheetForDTO cutSpreadSheetForDTO = this.toCutSpreadSheetForDTO(sheet);
          return cutSpreadSheetForDTO;
        }).collect(Collectors.toList());
    return cutSpreadSheetForDtoList;
  }

  public CutSpreadSheetForDTO toCutSpreadSheetForDTO(CutSpreadSheet cutSpreadSheet) {
    CutSpreadSheetForDTO cutSpreadSheetDTO = new CutSpreadSheetForDTO();
    cutSpreadSheetDTO.setId(cutSpreadSheet.getId());
    cutSpreadSheetDTO.setFinished(cutSpreadSheet.isFinished());

    return cutSpreadSheetDTO;
  }

  // public CutSpreadSheet fromDtoToSpreadSheetClass(CutSpreadSheetDTO cutSpreadSheetDTO){
  //   CutSpreadSheet cutSpreadSheet = new CutSpreadSheet();

  // }

}
