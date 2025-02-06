package com.backend.la.backendloveafrica.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.ModelAndStripsForProductDTO;
import com.backend.la.backendloveafrica.models.dtos.StripDetailsForProductDTO;
import com.backend.la.backendloveafrica.models.entities.ModelAndStripsForProduct;

@Component
public class ModelAndStripsForProductMapper {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private StripDetailsForProductMapper stripDetailsForProductMapper;

  public ModelAndStripsForProductDTO toModelAndStripsForProductDTO(ModelAndStripsForProduct modelAndStripsForProduct) {
    return new ModelAndStripsForProductDTO(modelAndStripsForProduct.getId(),
        modelMapper.toModelWithDetalleInsumosDtoDTO(modelAndStripsForProduct.getModel()),
        stripDetailsForProductMapper
            .toStripDetailsForProductDtoList(modelAndStripsForProduct.getStripDetailsForProducts()));
  }

}
