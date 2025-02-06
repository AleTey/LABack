package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.models.entities.Etiqueta;
import com.backend.la.backendloveafrica.models.entities.Supplier;
import com.backend.la.backendloveafrica.repositories.IEtiquetaRepository;

@Service
public class EtiquetaServiceImp implements IEtiquetaService {

  @Autowired
  private IEtiquetaRepository etiquetaRepository;

  @Autowired
  private ISupplierService supplierService;

  @Override
  @Transactional(readOnly = true)
  public List<Etiqueta> findAllEtiquetas() {
    return etiquetaRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Etiqueta> findEtiquetaById(Long id) {
    return etiquetaRepository.findById(id);
  }

  @Override
  @Transactional
  public Etiqueta saveEtiqueta(Etiqueta etiqueta) {
    return etiquetaRepository.save(etiqueta);
  }

  @Override
  @Transactional
  public void deleteEtiquetaById(Long id) {
    etiquetaRepository.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<Etiqueta> updateEtiqueta(Long id, String nombre, Optional<String> codigo,
      Optional<Long> idProveedor, Optional<String> detalle, Optional<String> marca, Optional<Double> precioUnidad,
      Optional<Integer> stock) {
        
    Optional<Etiqueta> o = this.findEtiquetaById(id);
    if (o.isPresent()) {
      Etiqueta etiquetaDb = o.get();
      etiquetaDb.setNombre(nombre);
      codigo.ifPresent(op -> etiquetaDb.setCodigo(op));
      idProveedor.ifPresent(supId -> {
        Optional<Supplier> suppOp = supplierService.findOptionalSupplierById(supId);
        suppOp.ifPresent(supp -> etiquetaDb.setProveedor(supp));
      });
      detalle.ifPresent(op -> etiquetaDb.setDetalle(op));
      marca.ifPresent(op -> etiquetaDb.setMarca(op));
      precioUnidad.ifPresent(op -> etiquetaDb.setPrecioUnidad(op));
      stock.ifPresent(op -> etiquetaDb.setStock(op));
      return Optional.of(this.saveEtiqueta(etiquetaDb));
    }
    return Optional.empty();
  }

}
