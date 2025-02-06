package com.backend.la.backendloveafrica.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.la.backendloveafrica.models.entities.Fabric;

@Repository
public interface IFabricRepository extends JpaRepository<Fabric, Long> {

  Page<Fabric> findAll(Pageable pageable);

  @Query("select f from Fabric f where LOWER(f.nombre) like %?1% or LOWER(f.color) LIKE %?1% or LOWER(f.tipo) like %?1% or LOWER(f.codigo) LIKE %?1%")
  Page<Fabric> findByString(String string, Pageable pageable);

  @Query("SELECT DISTINCT f.temporada FROM Fabric f")
  List<String> findDistinctSeason();

  // @Query("SELECT f FROM Fabric f WHERE f.temporada = :season OR f.temporada = 'ALL'")
  // List<Fabric> findFabricBySeason(@Param("season") String season);

  @Query("SELECT f FROM Fabric f WHERE f.temporada = :season OR f.temporada = 'ALL'")
  List<Fabric> findFabricBySeason(@Param("season") String season);

}
