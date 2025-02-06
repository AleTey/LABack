package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.entities.Model;
import com.backend.la.backendloveafrica.models.entities.ModelAndStripsForProduct;
import com.backend.la.backendloveafrica.models.entities.StripDetailsForProduct;
import com.backend.la.backendloveafrica.repositories.IModelAndStripsForProductRepository;

@Service
public class ModelAndStripsForProductServiceImp implements IModelAndStripsForProductService {

  @Autowired
  private IModelAndStripsForProductRepository modelAndStripsForProductRepo;

  @Override
  public List<ModelAndStripsForProduct> findAllModelAndStripsForProducts() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAllModelAndStripsForProducts'");
  }

  @Override
  public Optional<ModelAndStripsForProduct> findModelAndStripsForProductById(Long id) {
    return modelAndStripsForProductRepo.findById(id);
  }

  @Override
  public ModelAndStripsForProduct saveModelAndStripsForProduct(ModelAndStripsForProduct modelAndStrips) {
    return modelAndStripsForProductRepo.save(modelAndStrips);
  }

  @Override
  public void deleteModelAndStripsForProduct(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteModelAndStripsForProduct'");
  }

  @Override
  public Optional<ModelAndStripsForProduct> updateModelsAndStripsForProduct(Long id, Optional<Model> model,
      Optional<StripDetailsForProduct> stripDetails) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateModelsAndStripsForProduct'");
  }

}
