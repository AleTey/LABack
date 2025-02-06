package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.Input;
import com.backend.la.backendloveafrica.models.entities.Supplier;

public interface IInputService {

  List<Input> findAllInputs();

  Optional<Input> findInputById(Long id);

  // Input saveInput(Object object);

  void deleteInput(Long id);

  Optional<?> updateInput(Long id, Optional<String> nombre,
      Optional<String> codigo, Optional<Long> idSupplier,
      Optional<String> detalle, Optional<String> forma, Optional<String> medida,
      Optional<String> material, Optional<String> color, Optional<Integer> cantPorPack,
      Optional<Double> precioPorPack, Optional<Double> precioUni, Optional<Integer> stockPacks,
      Optional<String> ancho, Optional<Double> precioRollo, Optional<Double> mtsPorRollo,
      Optional<Double> precioMtr, Optional<Integer> stockEnRollos);
}
