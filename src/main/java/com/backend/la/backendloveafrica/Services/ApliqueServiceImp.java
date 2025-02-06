package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.entities.Aplique;
import com.backend.la.backendloveafrica.models.entities.Supplier;
import com.backend.la.backendloveafrica.repositories.IApliqueRepository;

@Service
public class ApliqueServiceImp implements IApliqueService {

  @Autowired
  private IApliqueRepository apliqueRepository;

  @Autowired
  private ISupplierService supplierService;

  @Override
  public List<Aplique> findAllApliques() {
    return apliqueRepository.findAll();
  }

  @Override
  public Optional<Aplique> findApliqueById(Long id) {
    return apliqueRepository.findById(id);
  }

  @Override
  public Aplique saveAplique(Aplique aplique) {
    return apliqueRepository.save(aplique);
  }

  @Override
  public void deleteAplique(Long id) {
    apliqueRepository.deleteById(id);
  }

  @Override
  public Optional<Aplique> updateAplique(Long id, Optional<String> nombre, Optional<String> codigo,
      Optional<Long> idProveedor, Optional<String> detalle, Optional<String> color, Optional<Integer> cantPorPack,
      Optional<Double> precioPorPack, Optional<Double> precioUnidad, Optional<Integer> stockPacks) {

    Optional<Aplique> oAplique = this.findApliqueById(id);

    if (oAplique.isPresent()) {
      Aplique apliqueDb = oAplique.get();

      nombre.ifPresent(op -> apliqueDb.setNombre(op));
      codigo.ifPresent(op -> apliqueDb.setCodigo(op));
      idProveedor.ifPresent(op -> {
        Optional<Supplier> oSupp = supplierService.findOptionalSupplierById(op);
        oSupp.ifPresent(supp -> apliqueDb.setProveedor(supp));
      });
      detalle.ifPresent(op -> apliqueDb.setDetalle(op));
      color.ifPresent(op -> apliqueDb.setColor(op));
      // cantPorPack.ifPresent(op -> apliqueDb.setCantPorPack(op));
      // precioPorPack.ifPresent(op -> apliqueDb.setPrecioPorPack(op));
      precioUnidad.ifPresent(op -> apliqueDb.setPrecioUnidad(op));
      stockPacks.ifPresent(op -> apliqueDb.setStock(op));

      return Optional.of(this.saveAplique(apliqueDb));

    } else {
      return Optional.empty();
    }
  }

}
