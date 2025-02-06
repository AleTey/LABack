package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.entities.Gancho;
import com.backend.la.backendloveafrica.models.entities.Supplier;
import com.backend.la.backendloveafrica.models.enums.TipoGancho;
import com.backend.la.backendloveafrica.repositories.IGanchoRepository;

@Service
public class GanchoServiceImp implements IGanchoService {

  @Autowired
  private IGanchoRepository ganchoRepository;

  @Autowired
  private ISupplierService supplierService;

  @Override
  public List<Gancho> findAllGanchos() {
    return ganchoRepository.findAll();
  }

  @Override
  public Optional<Gancho> findGanchoById(Long id) {
    return ganchoRepository.findById(id);
  }

  @Override
  public Gancho saveGancho(Gancho gancho) {
    return ganchoRepository.save(gancho);
  }

  @Override
  public void deleteGanchoById(Long id) {
    ganchoRepository.deleteById(id);
  }

  @Override
  public Optional<Gancho> updateGancho(Long id, String nombre, Optional<String> codigo, Optional<String> detalle,
      Optional<Long> idProveedor, Optional<TipoGancho> tipoGancho, Optional<String> medida, Optional<String> material,
      Optional<String> color, Optional<Integer> cantPorPack, Optional<Double> precioPorPack, Optional<Double> precioUni,
      Optional<Integer> stockPacks) {

    Optional<Gancho> o = this.findGanchoById(id);

    if (o.isPresent()) {
      Gancho ganchoDb = o.get();

      ganchoDb.setNombre(nombre);
      codigo.ifPresent(op -> ganchoDb.setCodigo(op));
      detalle.ifPresent(op -> ganchoDb.setDetalle(op));
      idProveedor.ifPresent(op -> {
        Optional<Supplier> oSupp = supplierService.findOptionalSupplierById(op);
        oSupp.ifPresent(opSup -> ganchoDb.setProveedor(opSup));
      });
      tipoGancho.ifPresent(op -> ganchoDb.setTipoGancho(op));
      medida.ifPresent(op -> ganchoDb.setMedida(op));
      material.ifPresent(op -> ganchoDb.setMaterial(op));
      color.ifPresent(op -> ganchoDb.setColor(op));
      // cantPorPack.ifPresent(op -> ganchoDb.setCantPorPack(op));
      // precioPorPack.ifPresent(op -> ganchoDb.setPrecioPorPack(op));
      precioUni.ifPresent(op -> ganchoDb.setPrecioUni(op));
      stockPacks.ifPresent(op -> ganchoDb.setStock(op));

      return Optional.of(this.saveGancho(ganchoDb));

    }
    return Optional.empty();

  }

}
