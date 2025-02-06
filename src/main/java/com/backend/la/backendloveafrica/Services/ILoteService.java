package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.dtos.LoteCreationDTO;
import com.backend.la.backendloveafrica.models.dtos.ProductNameModelImgType;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.LoteResponseDTO;
import com.backend.la.backendloveafrica.models.entities.Lote;
import com.backend.la.backendloveafrica.models.entities.Product;
import com.backend.la.backendloveafrica.models.enums.LoteState;

public interface ILoteService {

  List<Lote> findAll();

  Optional<Lote> findById(Long id);

  Optional<LoteResponseDTO> findByIdResponseDTO(Long id);
  // Optional<Lote> findByIdResponseDTO(Long id);

  List<LoteResponseDTO> findLotesByStatus(LoteState state);

  LoteResponseDTO save(LoteCreationDTO loteCreationDTO);

  Lote saveForUpdate(Lote lote);

  void deleteById(Long id);

  Optional<Lote> update(Lote lote);

  void changeStatus(Long id, LoteState state);

  void changeStatusWorkshop(Long id, LoteState state);

  List<Lote> findByProduct(Long id);

  List<LoteResponseDTO> findLoteByWorkshop();

  boolean existsByStatus(LoteState state);

  Optional<Lote> findLoteByCutSpreadSheetId(Long id);

  List<LoteResponseDTO> findByFabricCollection(String collection);

  List<ProductNameModelImgType> findProductsByStatusLoteAndSeason(String season, LoteState status);

  List<ProductNameModelImgType> findProductsBySeasonExcludingStatuses(String season, List<LoteState> statuses);

  Map<String, List<ProductNameModelImgType>> undoPreparingFinishedProducts(String season);
}
