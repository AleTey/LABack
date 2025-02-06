package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.models.entities.WorkShop;
import com.backend.la.backendloveafrica.repositories.IWorkShopRepository;

@Service
public class WorkShopServiceImp implements IWorkShopService {

  @Autowired
  private IWorkShopRepository workShopRepository;

  @Override
  @Transactional(readOnly = true)
  public List<WorkShop> findAll() {
    return workShopRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<WorkShop> findById(Long id) {
    return workShopRepository.findById(id);
  }

  @Override
  @Transactional
  public WorkShop save(WorkShop workShop) {
    return workShopRepository.save(workShop);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    Optional<WorkShop> o = this.findById(id);
    if (o.isPresent()) {
      workShopRepository.deleteById(id);
    }
  }

  @Override
  @Transactional
  public Optional<WorkShop> update(WorkShop workShop) {
    Optional<WorkShop> o = this.findById(workShop.getId());
    WorkShop workShopDb;
    if (o.isPresent()) {
      workShopDb = o.get();
      workShopDb.setName(workShop.getName());
      return Optional.of(this.save(workShop));
    }
    return Optional.empty();
  }

}
