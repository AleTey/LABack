package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.Model;
import com.backend.la.backendloveafrica.models.entities.ModelAndStripsForProduct;
import com.backend.la.backendloveafrica.models.entities.StripDetailsForProduct;

public interface IModelAndStripsForProductService {

  List<ModelAndStripsForProduct> findAllModelAndStripsForProducts();

  Optional<ModelAndStripsForProduct> findModelAndStripsForProductById(Long id);

  ModelAndStripsForProduct saveModelAndStripsForProduct(ModelAndStripsForProduct modelAndStrips);

  void deleteModelAndStripsForProduct(Long id);

  Optional<ModelAndStripsForProduct> updateModelsAndStripsForProduct(Long id, Optional<Model> model, Optional<StripDetailsForProduct> stripDetails);

}
