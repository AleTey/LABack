package com.backend.la.backendloveafrica.Services;

import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;

public interface IAmountPerSizeForProductService {

  AmountPerSizeForProduct save(AmountPerSizeForProduct amountPerSizeForProduct);

  Optional<AmountPerSizeForProduct> findById(Long id);

  Optional<AmountPerSizeForProduct> update(AmountPerSizeForProduct amountPerSizeForProduct);

}
