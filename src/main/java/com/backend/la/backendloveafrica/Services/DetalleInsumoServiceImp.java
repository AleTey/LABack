package com.backend.la.backendloveafrica.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.models.entities.DetalleInsumo;
import com.backend.la.backendloveafrica.repositories.IDetalleInsumoRepository;

@Service
public class DetalleInsumoServiceImp implements IDetalleInsumoService {

  @Autowired
  private IDetalleInsumoRepository detalleInsumoRepository;

  @Override
  @Transactional
  public DetalleInsumo saveDetalleInsumo(DetalleInsumo detalleInsumo) {
    return detalleInsumoRepository.save(detalleInsumo);
  }

}
