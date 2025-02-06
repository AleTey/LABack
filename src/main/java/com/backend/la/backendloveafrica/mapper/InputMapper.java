package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.InputIdNombreCodigoDTO;
import com.backend.la.backendloveafrica.models.dtos.InputIdNombreDetalleDTO;
import com.backend.la.backendloveafrica.models.entities.Input;

@Component
public class InputMapper {

  public InputIdNombreDetalleDTO toIdNombreDetalleDTO(Input input) {
    return new InputIdNombreDetalleDTO(input.getId(), input.getNombre(), input.getDetalle());
  }

  public InputIdNombreCodigoDTO toIdNombreCodigoDTO(Input input) {
    return new InputIdNombreCodigoDTO(input.getId(), input.getNombre(), input.getCodigo());
  }

  public List<InputIdNombreCodigoDTO> toInputIdNombreCodigoDTOsList(List<Input> inputs) {
    return inputs.stream()
        .map(input -> toIdNombreCodigoDTO(input))
        .collect(Collectors.toList());

  }

}
