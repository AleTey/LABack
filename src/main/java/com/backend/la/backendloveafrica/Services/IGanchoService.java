package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.Gancho;
import com.backend.la.backendloveafrica.models.enums.TipoGancho;

public interface IGanchoService {

  List<Gancho> findAllGanchos();

  Optional<Gancho> findGanchoById(Long id);

  Gancho saveGancho(Gancho gancho);

  void deleteGanchoById(Long id);

  Optional<Gancho> updateGancho(Long id, String nombre, Optional<String> codigo, Optional<String> detalle,
  Optional<Long> idProveedor, Optional<TipoGancho> tipoGancho, Optional<String> medida, Optional<String> material,
  Optional<String> color, Optional<Integer> cantPorPack, Optional<Double> precioPorPack, Optional<Double> precioUni,
  Optional<Integer> stockPacks);

}
