package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.Supplier;

public interface ISupplierService {
  public List<Supplier> findAllSuppliers();

  public Supplier findSupplierById(Long id);

  public Optional<Supplier> findOptionalSupplierById(Long id);

  public Supplier saveSupplier(Supplier supplier);

  public void deleteSupplier(Long id);

  public Optional<Supplier> updateSupplier(Supplier supplier, Long id);
}
