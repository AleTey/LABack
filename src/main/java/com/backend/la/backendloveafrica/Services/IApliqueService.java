package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.Aplique;

public interface IApliqueService {

  List<Aplique> findAllApliques();

  Optional<Aplique> findApliqueById(Long id);

  Aplique saveAplique(Aplique aplique);

  void deleteAplique(Long id);

  Optional<Aplique> updateAplique(Long id, Optional<String> nombre,
      Optional<String> codigo, Optional<Long> idProveedor,
      Optional<String> detalle, Optional<String> color,
      Optional<Integer> cantPorPack, Optional<Double> precioPorPack,
      Optional<Double> precioUnidad, Optional<Integer> stockPacks);

}
