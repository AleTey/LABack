package com.backend.la.backendloveafrica.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.la.backendloveafrica.models.entities.Lote;
import com.backend.la.backendloveafrica.models.entities.Product;
import com.backend.la.backendloveafrica.models.enums.LoteState;

@Repository
public interface ILoteRepository extends JpaRepository<Lote, Long> {

  @Query("SELECT l from Lote l WHERE l.status = :state")
  List<Lote> findByState(@Param("state") LoteState state);

  @Query("SELECT l from Lote l WHERE l.status = :state")
  Page<Lote> findByState(@Param("state") LoteState state, Pageable pageable);

  @Query("SELECT l from Lote l JOIN l.products p WHERE p.id = :id AND l.status = 'FINALIZADO'")
  List<Lote> findLotesByProductId(Long id);

  @Query("SELECT l from Lote l JOIN l.products p WHERE p.id = :id ORDER BY p.id")
  Page<Lote> findLotesByProductIdTop3(Long id, Pageable pageable);

  @Query("SELECT l from Lote l JOIN l.workShop w JOIN w.user u WHERE u.username = :username AND l.status = 'TALLER'")
  List<Lote> findLotesByWorkshopsUsername(String username);

  boolean existsByStatus(LoteState state);

  @Query("SELECT l FROM Lote l JOIN l.cutSpreadSheet c WHERE c.id = :id")
  Optional<Lote> findLoteByCutSpreadSheetId(Long id);

  // @Query("SELECT DISTINCT l FROM Lote l JOIN l.products p JOIN p.fabric f WHERE
  // f.temporada = ':collection'")
  @Query("SELECT DISTINCT l FROM Lote l JOIN l.products p JOIN p.fabric f WHERE f.temporada = :collection")
  List<Lote> findByFabricCollection(String collection);

  @Query("SELECT DISTINCT p FROM Lote l JOIN l.products p WHERE p.fabric.temporada = :season AND l.status = :status")
  List<Product> findProductsByStatusLoteAndSeason(String season, LoteState status);

  @Query("SELECT DISTINCT p FROM Lote l JOIN l.products p WHERE p.fabric.temporada = :season AND l.status NOT IN :statuses")
  List<Product> findProductsBySeasonExcludingStatuses(String season, List<LoteState> statuses);

}
