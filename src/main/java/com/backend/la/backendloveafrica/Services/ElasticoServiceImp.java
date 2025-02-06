package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.models.entities.Elastico;
import com.backend.la.backendloveafrica.models.entities.Supplier;
import com.backend.la.backendloveafrica.repositories.IElasticoRepository;

@Service
public class ElasticoServiceImp implements IElasticoService {

  @Autowired
  private IElasticoRepository elasticoRepository;

  @Autowired
  private ISupplierService supplierService;

  @Override
  @Transactional(readOnly = true)
  public List<Elastico> findAllElasticos() {
    return elasticoRepository.findAll();
  }

  @Override
  @Transactional
  public Elastico saveElastico(Elastico elastico) {
    return elasticoRepository.save(elastico);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Elastico> findElasticoById(Long id) {
    return elasticoRepository.findById(id);
    // Optional<Elastico> o = elasticoRepository.findById(id);

    // if (o.isPresent()) {
    // return Optional.of(o.get());
    // }
    // return Optional.empty();
  }

  @Override
  @Transactional
  public void deleteElastico(Long id) {
    elasticoRepository.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<Elastico> updateElastico(Long id, Optional<String> nombre, Optional<String> codigo,
      Optional<Long> idProveedor,
      Optional<String> detalle, Optional<String> ancho, Optional<String> material, Optional<String> color,
      Optional<Double> precioRollo, Optional<Double> metrosPorRollo, Optional<Double> precioMtr,
      Optional<Integer> stockEnRollos) {

    Optional<Elastico> o = this.findElasticoById(id);

    if (o.isPresent()) {
      Elastico elasticoDb = o.orElseThrow();

      nombre.ifPresent(op -> elasticoDb.setNombre(op));
      codigo.ifPresent(op -> elasticoDb.setCodigo(op));
      idProveedor.ifPresent(op -> {
        Optional<Supplier> oSup = supplierService.findOptionalSupplierById(op);
        oSup.ifPresent(sup -> elasticoDb.setProveedor(sup));
      });
      detalle.ifPresent(op -> elasticoDb.setDetalle(op));
      ancho.ifPresent(op -> elasticoDb.setAncho(op));
      material.ifPresent(op -> elasticoDb.setMaterial(op));
      color.ifPresent(op -> elasticoDb.setColor(op));
      // precioRollo.ifPresent(op -> elasticoDb.setPrecioRollo(op));
      metrosPorRollo.ifPresent(op -> elasticoDb.setMtsPorRollo(op));
      precioMtr.ifPresent(op -> elasticoDb.setPrecioMtr(op));
      stockEnRollos.ifPresent(op -> elasticoDb.setStock(op));

      return Optional.of(this.saveElastico(elasticoDb));
    }

    return Optional.empty();
  }

}
