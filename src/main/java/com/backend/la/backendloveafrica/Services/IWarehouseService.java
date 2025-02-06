package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.backend.la.backendloveafrica.models.entities.Model;
import com.backend.la.backendloveafrica.models.entities.Product;
import com.backend.la.backendloveafrica.models.entities.Warehouse;

public interface IWarehouseService {

  List<Warehouse> findAll();

  Page<Warehouse> findPageByString(String string, int page, int size);

  Optional<Warehouse> findById(Long id);

  Warehouse save(Warehouse warehouse);

  Warehouse saveFromProduct(Product product, Model model);

  void deleteById(Long id);

  Optional<Warehouse> update(Warehouse warehouse);

}
