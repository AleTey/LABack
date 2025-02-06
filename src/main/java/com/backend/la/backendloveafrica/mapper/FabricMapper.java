package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.FabricNoPriceNoSupp;
import com.backend.la.backendloveafrica.models.dtos.FabricNombreCodigoTipoImgDTO;
import com.backend.la.backendloveafrica.models.entities.Fabric;

@Component
public class FabricMapper {

  public FabricNombreCodigoTipoImgDTO convertFabricToFabricDto(Fabric fabric) {
    return new FabricNombreCodigoTipoImgDTO(fabric.getId(), fabric.getNombre(), fabric.getCodeBarNumb(),
        fabric.getTipo(), fabric.getImg(), fabric.getUrlFile());
  }

  public List<FabricNombreCodigoTipoImgDTO> convertToListFabricNombreCodigoTipoImgDto(List<Fabric> fabrics) {
    return fabrics.stream()
        .map(fabric -> convertFabricToFabricDto(fabric))
        .collect(Collectors.toList());
  }

  public FabricNoPriceNoSupp convertToFabricNoPriceNoSuppDTO(Fabric fabric) {
    return new FabricNoPriceNoSupp(
        fabric.getId(),
        fabric.getNombre(),
        fabric.getCodigo(),
        fabric.getColor(),
        fabric.getTipo(),
        fabric.getTemporada(),
        fabric.getStock(),
        fabric.getCodeBarNumb(),
        fabric.getTags(),
        fabric.getImg(),
        fabric.getUrlFile());
  }

  public List<FabricNoPriceNoSupp> toFabricNoPriceNoSuppListDTO(List<Fabric> fabrics) {
    return fabrics.stream()
        .map(f -> this.convertToFabricNoPriceNoSuppDTO(f))
        .collect(Collectors.toList());
  }

  public Page<FabricNoPriceNoSupp> toFabricNoPriceNoSuppPageDTO(Page<Fabric> fabricPage) {
    List<FabricNoPriceNoSupp> fabricsDto = this.toFabricNoPriceNoSuppListDTO(fabricPage.getContent());
    Pageable pageable = fabricPage.getPageable();
    return new PageImpl<>(fabricsDto, pageable, fabricPage.getTotalElements());
  }

}
