package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.Argolla;
import com.backend.la.backendloveafrica.models.entities.Supplier;

public interface IArgollaService {

  List<Argolla> findAllArgollas();

  Argolla saveArgolla(Argolla argolla);

  Optional<Argolla> findArgollaById(Long id);

  Optional<Argolla> updateArgolla(Long id, String nombre, Optional<Long> idProveedor, Optional<String> codigo,
      Optional<String> detalle, Optional<String> forma, Optional<String> circunferenciaInterna,
      Optional<String> circunferenciaExterna, Optional<String> material, Optional<String> color,
      Optional<Integer> cantPorPack, Optional<Double> precioPorPack,
      Optional<Double> precioUni, Optional<Integer> stockPacks);

  void deleteArgollaById(Long id);

}
