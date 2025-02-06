package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.DetalleInsumoDTO;
import com.backend.la.backendloveafrica.models.dtos.InputIdNombreCodigoDTO;
import com.backend.la.backendloveafrica.models.entities.DetalleInsumo;

@Component
public class DetalleInsumoMapper {

  @Autowired
  private InputMapper inputMapper;

  public DetalleInsumoDTO toDetalleInsumoDTO(DetalleInsumo detalleInsumo) {
    InputIdNombreCodigoDTO inputDto = inputMapper.toIdNombreCodigoDTO(detalleInsumo.getInput());
    return new DetalleInsumoDTO(detalleInsumo.getId(), detalleInsumo.getCantidad(), detalleInsumo.getCantidadPorTalle(),
        inputDto);
  }

  public List<DetalleInsumoDTO> toDetalleInsumoDtosList(List<DetalleInsumo> detalleInsumos) {
    return detalleInsumos.stream()
        .map(detalle -> this.toDetalleInsumoDTO(detalle))
        .collect(Collectors.toList());
  }

}
