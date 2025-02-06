package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.dtos.ProductSetDTO;
import com.backend.la.backendloveafrica.models.entities.ProductSet;

public interface IProductSetService {

  List<ProductSetDTO> findAllDto();

  Optional<ProductSet> findById(Long id);

  Optional<ProductSetDTO> findByIdDto(Long id);

  ProductSet save(ProductSet productSet);

  void deleteById(Long id);

  Optional<ProductSet> update(ProductSet productSet);

}
