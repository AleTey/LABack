package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.InputQuantityForSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.InputQuantityForSpreadSheet;

@Component
public class InputQuantityForSpreadSheetMapper {

  @Autowired
  private InputMapper inputMapper;

  public InputQuantityForSpreadSheetDTO toDTO(InputQuantityForSpreadSheet inputQuantityForSpreadSheet) {
    InputQuantityForSpreadSheetDTO inputDTO = new InputQuantityForSpreadSheetDTO();

    inputDTO.setId(inputQuantityForSpreadSheet.getId());

    inputDTO.setInputIdNombreDetalleDTO(inputMapper.toIdNombreDetalleDTO(inputQuantityForSpreadSheet.getInput()));

    inputDTO.setQuantity(inputQuantityForSpreadSheet.getQuantity());

    return inputDTO;

  }

  public List<InputQuantityForSpreadSheetDTO> ToListDto(
      List<InputQuantityForSpreadSheet> inputQuantityForSpreadSheets) {

    List<InputQuantityForSpreadSheetDTO> inputsDtoList = inputQuantityForSpreadSheets.stream()
        .map(detail -> {
          return this.toDTO(detail);
        }).collect(Collectors.toList());
    return inputsDtoList;
  }

}
