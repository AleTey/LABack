package com.backend.la.backendloveafrica.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;
import com.backend.la.backendloveafrica.repositories.IAmountPerSizeForProductRepository;

@Service
public class AmountPerSizeForProductImp implements IAmountPerSizeForProductService {

  @Autowired
  private IAmountPerSizeForProductRepository amountPerSizeForProductRepository;

  @Override
  public AmountPerSizeForProduct save(AmountPerSizeForProduct amountPerSizeForProduct) {
    return amountPerSizeForProductRepository.save(amountPerSizeForProduct);
  }

  @Override
  public Optional<AmountPerSizeForProduct> findById(Long id) {
    return amountPerSizeForProductRepository.findById(id);
  }

  @Override
  public Optional<AmountPerSizeForProduct> update(AmountPerSizeForProduct amountPerSizeForProduct) {
    Optional<AmountPerSizeForProduct> o = this.findById(amountPerSizeForProduct.getId());
    if (o.isPresent()) {
      AmountPerSizeForProduct amountPerSizeForProductDb = o.get();
      amountPerSizeForProductDb.setAmountPerSize(amountPerSizeForProduct.getAmountPerSize());
      return Optional.of(this.save(amountPerSizeForProductDb));
    }
    return Optional.empty();
  }

}
