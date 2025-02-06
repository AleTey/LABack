package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.LoteResponseDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.WorkShopMapper;
import com.backend.la.backendloveafrica.models.entities.Lote;
import com.backend.la.backendloveafrica.models.entities.Product;

@Component
public class LoteMapper {

  @Autowired
  private CutSpreadSheetMapper cutSpreadSheetMapper;

  @Autowired
  private PreparationSpreadSheetMapper preparationSpreadSheetMapper;

  @Autowired
  private WorkShopSpreadSheetMapper workShopSpreadSheetMapper;

  @Autowired
  private ControlSpreadSheetMapper controlSpreadSheetMapper;

  @Autowired
  private WorkShopMapper workShopMapper;

  @Autowired
  private ProductMapper productMapper;

  public List<LoteResponseDTO> toLoteResponseDtoList(List<Lote> lotes) {

    List<LoteResponseDTO> loteResponseDtoList = lotes.stream()
        .map(lote -> {
          LoteResponseDTO loteResponseDTO = this.toLoteResponseDTO(lote);
          return loteResponseDTO;
        }).collect(Collectors.toList());
    return loteResponseDtoList;
  }

  public LoteResponseDTO toLoteResponseDTO(Lote lote) {
    LoteResponseDTO loteResponseDTO = new LoteResponseDTO();
    loteResponseDTO.setId(lote.getId());
    loteResponseDTO.setProductsForLoteDTO(productMapper.transformToProductDtoList(lote.getProducts()));
    loteResponseDTO.setCutSpreadsheetForLoteDTO(cutSpreadSheetMapper.toCutSpreadSheetForDTO(lote.getCutSpreadSheet()));
    loteResponseDTO.setPreparationSpreadSheetForDTO(
        preparationSpreadSheetMapper.toPreparationSpreadSheetForDto(lote.getPreparationSpreadSheet()));
    loteResponseDTO.setWorkShopSpreadSheetForDTO(
        workShopSpreadSheetMapper.toWorkShopSpreadSheetForDTO(lote.getWorkShopSpreadSheet()));
    loteResponseDTO
        .setControlSpreadSheetForDTO(controlSpreadSheetMapper.toControlSpreadSheetForDTO(lote.getControlSpreadSheet()));
    loteResponseDTO.setWorkShopDto(workShopMapper.toWorkshopDto(lote.getWorkShop()));
    loteResponseDTO.setStatus(lote.getStatus());
    loteResponseDTO.setFinished(lote.isFinished());
    loteResponseDTO.setAdditionalDetails(lote.getAdditionalDetails());
    loteResponseDTO.setCreationDate(lote.getCreationDate());
    return loteResponseDTO;
  }

}
