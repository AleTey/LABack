package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.models.entities.Corredera;
import com.backend.la.backendloveafrica.models.entities.Elastico;
import com.backend.la.backendloveafrica.models.entities.Input;
import com.backend.la.backendloveafrica.models.entities.Supplier;
import com.backend.la.backendloveafrica.repositories.IInputRepository;

@Service
public class InputServiceImp implements IInputService {

  Logger logger = LoggerFactory.getLogger(InputServiceImp.class);

  @Autowired
  private IInputRepository inputRepository;

  @Autowired
  private ElasticoServiceImp elasticoService;

  @Autowired
  private ICorrederaService correderaService;

  @Autowired
  private ISupplierService supplierService;

  @Override
  @Transactional(readOnly = true)
  public List<Input> findAllInputs() {
    return inputRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Input> findInputById(Long id) {
    // Optional<Input> o = inputRepository.findById(id);

    // if (o.isPresent()) {
    // return Optional.of(o.get());
    // }
    // return Optional.empty();
    return inputRepository.findById(id);
  }

  // @Override
  // @Transactional
  // public Input saveInput(Object object) {
  // // logger.info("el tipo es: " + object.);
  // logger.info("object: " + (object.toString() + object.getClass()));
  // logger.info("Es instance of Elastico: " +(object instanceof Elastico));
  // // if (object instanceof Elastico) {
  // Elastico elastico = (Elastico) object;
  // return elasticoService.saveElastico(elastico);
  // // }
  // // return null;
  // // return inputRepository.save(input);
  // }

  @Override
  @Transactional
  public void deleteInput(Long id) {
    inputRepository.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<?> updateInput(Long id, Optional<String> nombre,
      Optional<String> codigo, Optional<Long> idSupplier,
      Optional<String> detalle, Optional<String> forma, Optional<String> medida,
      Optional<String> material, Optional<String> color, Optional<Integer> cantPorPack,
      Optional<Double> precioPorPack, Optional<Double> precioUni, Optional<Integer> stockPacks,
      Optional<String> ancho, Optional<Double> precioRollo, Optional<Double> mtsPorRollo,
      Optional<Double> precioMtr, Optional<Integer> stockEnRollos) {

    Optional<Elastico> oElastico = elasticoService.findElasticoById(id);

    if (oElastico.isPresent()) {
      Elastico elasticoDb = oElastico.get();

      nombre.ifPresent(op -> elasticoDb.setNombre(op));
      codigo.ifPresent(op -> elasticoDb.setCodigo(op));
      idSupplier.ifPresent(oIdSup -> {
        Optional<Supplier> oSupplier = supplierService.findOptionalSupplierById(id);
        oSupplier.ifPresent(oSup -> elasticoDb.setProveedor(oSup));
      });
      // idSupplier.ifPresent(op -> elasticoDb.setProveedor(op));
      detalle.ifPresent(op -> elasticoDb.setDetalle(op));
      ancho.ifPresent(op -> elasticoDb.setAncho(op));
      material.ifPresent(op -> elasticoDb.setMaterial(op));
      color.ifPresent(op -> elasticoDb.setColor(op));
      // precioRollo.ifPresent(op -> elasticoDb.setPrecioRollo(op));
      mtsPorRollo.ifPresent(op -> elasticoDb.setMtsPorRollo(op));
      precioMtr.ifPresent(op -> elasticoDb.setPrecioMtr(op));
      stockEnRollos.ifPresent(op -> elasticoDb.setStock(op));

      Elastico elastico = elasticoService.saveElastico(elasticoDb);

      return Optional.of(elastico);
    }

    Optional<Corredera> oCorredera = correderaService.findCorrederaById(id);

    if (oCorredera.isPresent()) {
      Corredera correderaDb = oCorredera.get();

      nombre.ifPresent(op -> correderaDb.setNombre(op));
      codigo.ifPresent(op -> correderaDb.setCodigo(op));
      idSupplier.ifPresent(opIdSup -> {
        Optional<Supplier> oSupplier = supplierService.findOptionalSupplierById(opIdSup);
        oSupplier.ifPresent(oSup -> correderaDb.setProveedor(oSup));
      });
      detalle.ifPresent(op -> correderaDb.setDetalle(op));
      // forma.ifPresent(op -> correderaDb.setForma(op));
      medida.ifPresent(op -> correderaDb.setMedida(op));
      material.ifPresent(op -> correderaDb.setMaterial(op));
      color.ifPresent(op -> correderaDb.setColor(op));
      // cantPorPack.ifPresent(op -> correderaDb.setCantPorPack(op));
      // precioPorPack.ifPresent(op -> correderaDb.setPrecioPorPack(op));
      precioUni.ifPresent(op -> correderaDb.setPrecioUni(op));
      stockPacks.ifPresent(op -> correderaDb.setStock(op));

      correderaService.saveCorredera(correderaDb);

      return Optional.of(correderaDb);

    }

    return Optional.empty();
  }

}
