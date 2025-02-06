package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.Etiqueta;

public interface IEtiquetaService {

  List<Etiqueta> findAllEtiquetas();

  Optional<Etiqueta> findEtiquetaById(Long id);

  Etiqueta saveEtiqueta(Etiqueta etiqueta);

  void deleteEtiquetaById(Long id);

  Optional<Etiqueta> updateEtiqueta(Long id, String nombre, Optional<String> codigo,
      Optional<Long> idProveedor, Optional<String> detalle, Optional<String> marca,
      Optional<Double> precioUnidad, Optional<Integer> stock);
}
