package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.WorkShop;

public interface IWorkShopService {

  List<WorkShop> findAll();

  Optional<WorkShop> findById(Long id);

  WorkShop save(WorkShop workShop);

  void deleteById(Long id);

  Optional<WorkShop> update(WorkShop workShop);

}
