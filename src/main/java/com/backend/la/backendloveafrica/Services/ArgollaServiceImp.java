package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.models.entities.Argolla;
import com.backend.la.backendloveafrica.models.entities.Supplier;
import com.backend.la.backendloveafrica.repositories.IArgollaRepository;

@Service
public class ArgollaServiceImp implements IArgollaService {

  @Autowired
  private IArgollaRepository argollaRepository;

  @Autowired
  private ISupplierService supplierService;

  @Override
  @Transactional(readOnly = true)
  public List<Argolla> findAllArgollas() {
    return argollaRepository.findAll();
  }

  @Override
  @Transactional
  public Argolla saveArgolla(Argolla argolla) {
    return argollaRepository.save(argolla);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Argolla> findArgollaById(Long id) {

    return argollaRepository.findById(id);
  }

  @Override
  @Transactional
  public Optional<Argolla> updateArgolla(Long id, String nombre, Optional<Long> idProveedor, Optional<String> codigo,
      Optional<String> detalle, Optional<String> forma, Optional<String> circunferenciaInterna,
      Optional<String> circunferenciaExterna, Optional<String> material, Optional<String> color,
      Optional<Integer> cantPorPack, Optional<Double> precioPorPack,
      Optional<Double> precioUni, Optional<Integer> stockPacks) {

    Optional<Argolla> o = argollaRepository.findById(id);

    if (o.isPresent()) {
      Argolla argollaDb = o.get();
      argollaDb.setNombre(nombre);
      idProveedor.ifPresent(op -> {
        Optional<Supplier> oSupp = supplierService.findOptionalSupplierById(op);
        oSupp.ifPresent(supp -> argollaDb.setProveedor(supp));
      });
      codigo.ifPresent(op -> argollaDb.setCodigo(op));
      detalle.ifPresent(op -> argollaDb.setDetalle(op));
      // forma.ifPresent(op -> argollaDb.setForma(op));
      circunferenciaInterna.ifPresent(op -> argollaDb.setCircunferenciaInterna(op));
      circunferenciaExterna.ifPresent(op -> argollaDb.setCircunferenciaExterna(op));
      material.ifPresent(op -> argollaDb.setMaterial(op));
      color.ifPresent(op -> argollaDb.setColor(op));
      // cantPorPack.ifPresent(op -> argollaDb.setCantPorPack(op));
      // precioPorPack.ifPresent(op -> argollaDb.setPrecioPorPack(op));
      precioUni.ifPresent(op -> argollaDb.setPrecioUni(op));
      stockPacks.ifPresent(op -> argollaDb.setStock(op));

      return Optional.of(this.saveArgolla(argollaDb));

    }
    return Optional.empty();

  }

  @Override
  @Transactional
  public void deleteArgollaById(Long id) {
    argollaRepository.deleteById(id);
  }

}
