package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.models.entities.Supplier;
import com.backend.la.backendloveafrica.repositories.ISupplierRepository;

@Service
public class SupplierServiceImpl implements ISupplierService {

  @Autowired
  private ISupplierRepository suppRepo;

  @Override
  @Transactional(readOnly = true)
  public List<Supplier> findAllSuppliers() {
    return (List<Supplier>) suppRepo.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Supplier findSupplierById(Long id) {
    return suppRepo.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public Optional<Supplier> findOptionalSupplierById(Long id) {

    Optional<Supplier> o = suppRepo.findById(id);
    return o;
  }

  @Override
  @Transactional
  public Supplier saveSupplier(Supplier supplier) {
    Supplier savedSupplier = suppRepo.save(supplier);
    return savedSupplier;
  }

  @Override
  @Transactional
  public void deleteSupplier(Long id) {
    suppRepo.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<Supplier> updateSupplier(Supplier supplier, Long id) {
    Optional<Supplier> o = this.findOptionalSupplierById(id);
    if (o.isPresent()) {
      Supplier suppDb = o.orElseThrow();
      suppDb.setEmpresa(supplier.getEmpresa());
      suppDb.setEmail(supplier.getEmail());
      suppDb.setSector(supplier.getSector());
      suppDb.setCalle(supplier.getCalle());
      suppDb.setNro(supplier.getNro());
      suppDb.setLocalidad(supplier.getLocalidad());
      suppDb.setNombreContacto(supplier.getNombreContacto());
      suppDb.setCaracteristica(supplier.getCaracteristica());
      suppDb.setCelContacto(supplier.getCelContacto());
      suppDb.setEmailContacto(supplier.getEmailContacto());
      return Optional.of(this.saveSupplier(suppDb));
    }
    return Optional.empty();
  }

}
