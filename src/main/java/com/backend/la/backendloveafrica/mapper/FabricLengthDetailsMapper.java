package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.FabricLengthDetailsDTO;
import com.backend.la.backendloveafrica.models.entities.Fabric;
import com.backend.la.backendloveafrica.models.entities.FabricLengthDetails;

@Component
public class FabricLengthDetailsMapper {

  @Autowired
  private FabricMapper fabricMapper;

  public FabricLengthDetailsDTO toFabricLengthDetailsDTO(FabricLengthDetails fabricLengthDetails) {
    return new FabricLengthDetailsDTO(fabricLengthDetails.getId(),
        fabricMapper.convertFabricToFabricDto(fabricLengthDetails.getFabric()),
        fabricLengthDetails.getNumberOfLayers());
  }

  public List<FabricLengthDetailsDTO> convertFabricLengthDetailsToListDto(
      List<FabricLengthDetails> fabricLengthDetailsList) {
    List<FabricLengthDetailsDTO> fabricLengthDetailsDTOList = fabricLengthDetailsList.stream()
        .map(detail -> {
          return this.toFabricLengthDetailsDTO(detail);
        }).collect(Collectors.toList());

    return fabricLengthDetailsDTOList;
  }

  public FabricLengthDetails fabricLengthDetailsDtoToFabricLengthDetailsClass(
      FabricLengthDetailsDTO fabricLengthDetailsDTO) {
    FabricLengthDetails fabricLengthDetails = new FabricLengthDetails();
    fabricLengthDetails.setId(fabricLengthDetailsDTO.getId());
    fabricLengthDetails.setFabric(new Fabric(fabricLengthDetailsDTO.getFabricNombreCodigoTipoImgDTO().getId()));
    fabricLengthDetails.setNumberOfLayers(fabricLengthDetailsDTO.getNumberOfLayers());
    return fabricLengthDetails;
  }

  public List<FabricLengthDetails> listFabricLengthDetailsDtoToFabricLengthDetailsClass(
      List<FabricLengthDetailsDTO> fabricLengthDetailsDTOs) {
    List<FabricLengthDetails> fabricLengthDetailsList = fabricLengthDetailsDTOs.stream()
        .map(f -> {
          return this.fabricLengthDetailsDtoToFabricLengthDetailsClass(f);
        }).collect(Collectors.toList());
    return fabricLengthDetailsList;
  }

}
