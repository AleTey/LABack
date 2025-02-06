package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.Elastico;

public interface IElasticoService {

  List<Elastico> findAllElasticos();

  Elastico saveElastico(Elastico elastico);

  Optional<Elastico> findElasticoById(Long id);

  void deleteElastico(Long id);

  Optional<Elastico> updateElastico(Long id, Optional<String> nombre, Optional<String> codigo, Optional<Long> idProveedor,
      Optional<String> detalle, Optional<String> ancho, Optional<String> material, Optional<String> color,
      Optional<Double> precioRollo, Optional<Double> metrosPorRollo, Optional<Double> precioMtr,
      Optional<Integer> stockEnRollos);
}
