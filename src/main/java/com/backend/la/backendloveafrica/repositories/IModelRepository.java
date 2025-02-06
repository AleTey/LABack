package com.backend.la.backendloveafrica.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.la.backendloveafrica.models.entities.Model;

public interface IModelRepository extends JpaRepository<Model, Long> {

  Page<Model> findAll(Pageable pageable);

  @Query("SELECT m FROM Model m WHERE LOWER(m.tipoPrenda) LIKE %?1% OR LOWER(m.nombre) LIKE %?1%")
  Page<Model> getPageByString(String string, Pageable pageable);

  @Query("SELECT m from Model m WHERE LOWER(m.nombre) LIKE %?1% or LOWER(m.tipoPrenda) LIKE %?1%")
  List<Model> findModelByString(String string);

}
