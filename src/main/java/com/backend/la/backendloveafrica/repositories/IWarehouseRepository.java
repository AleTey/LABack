package com.backend.la.backendloveafrica.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.la.backendloveafrica.models.entities.Warehouse;
import com.backend.la.backendloveafrica.models.enums.TipoPrenda;

@Repository
public interface IWarehouseRepository extends JpaRepository<Warehouse, Long> {

  @Query("SELECT w FROM Warehouse w WHERE LOWER(w.product.nombre) LIKE %?1% OR LOWER(w.product.modelAndStripsForProduct.model.nombre) LIKE %?1%")
  Page<Warehouse> findPageByString(String string, Pageable pageable);

  @Query("SELECT w FROM Warehouse w Where w.product.modelAndStripsForProduct.model.tipoPrenda = :tipoPrenda")
  Page<Warehouse> findByTipoPrenda(TipoPrenda tipoPrenda, Pageable pageable);

}
