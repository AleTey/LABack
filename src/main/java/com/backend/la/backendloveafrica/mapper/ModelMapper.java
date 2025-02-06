package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.ModelWithDetalleInsumoDtoDTO;
import com.backend.la.backendloveafrica.models.entities.Model;

@Component
public class ModelMapper {

  @Autowired
  private DetalleInsumoMapper detalleInsumoMapper;

  public ModelWithDetalleInsumoDtoDTO toModelWithDetalleInsumosDtoDTO(Model model) {
    return new ModelWithDetalleInsumoDtoDTO(
        model.getId(),
        model.getNombre(),
        model.getTipoPrenda(),
        model.getTallesDisponibles(),
        detalleInsumoMapper.toDetalleInsumoDtosList(model.getDetalleInsumos()),
        model.getDetalleTiraModelo(),
        model.getDetalle(),
        model.getFechaDeRegistro());
  }

  public List<ModelWithDetalleInsumoDtoDTO> toModelWithDetalleInsumoDtoDTOsList(List<Model> models) {
    return models.stream()
        .map(model -> this.toModelWithDetalleInsumosDtoDTO(model))
        .collect(Collectors.toList());
  }

  public Page<ModelWithDetalleInsumoDtoDTO> toModelWithDetalleInsumoDtoDTOsPage(Page<Model> models) {
    List<ModelWithDetalleInsumoDtoDTO> modelsDtoList = models.getContent().stream()
        .map(model -> this.toModelWithDetalleInsumosDtoDTO(model))
        .collect(Collectors.toList());

    return new PageImpl<>(modelsDtoList, PageRequest.of(models.getNumber(), models.getSize()),
        models.getTotalElements());
  }

}
