package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.Exception.ResourceNotFoundException;
import com.backend.la.backendloveafrica.mapper.ProductSetMapper;
import com.backend.la.backendloveafrica.models.dtos.ProductSetDTO;
import com.backend.la.backendloveafrica.models.entities.ProductSet;
import com.backend.la.backendloveafrica.repositories.IProductSetRepository;

@Service
public class ProductSetServiceImp implements IProductSetService {

  @Autowired
  private IProductSetRepository productSetRepository;

  @Autowired
  private ProductSetMapper productSetMapper;

  @Override
  public List<ProductSetDTO> findAllDto() {
    return productSetMapper.toProductSetDTOsList(productSetRepository.findAll());
  }

  @Override
  public Optional<ProductSet> findById(Long id) {
    return productSetRepository.findById(id);
  }

  @Override
  public Optional<ProductSetDTO> findByIdDto(Long id) {
    Optional<ProductSet> o = this.findById(id);
    if (o.isPresent()) {
      return Optional.of(productSetMapper.toProductSetDTO(o.get()));
    }
    return Optional.empty();
  }

  @Override
  public ProductSet save(ProductSet productSet) {
    return productSetRepository.save(productSet);
  }

  @Override
  public void deleteById(Long id) {
    Optional<ProductSet> o = this.findById(id);
    if (o.isPresent()) {
      productSetRepository.deleteById(id);
    } else {
      throw new ResourceNotFoundException("ProductSet not found");
    }
  }

  @Override
  public Optional<ProductSet> update(ProductSet productSet) {
    Optional<ProductSet> o = this.findById(productSet.getId());
    if (o.isPresent()) {
      ProductSet productSetDb = o.get();
      productSetDb.getProducts().clear();
      productSetDb.setProducts(productSet.getProducts());
      return Optional.of(this.save(productSetDb));
    }
    return Optional.empty();

  }

}
