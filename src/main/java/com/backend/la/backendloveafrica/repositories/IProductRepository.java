package com.backend.la.backendloveafrica.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.la.backendloveafrica.models.entities.Product;
import com.backend.la.backendloveafrica.models.enums.TipoPrenda;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT p FROM Product p WHERE LOWER(p.nombre) LIKE %?1% OR LOWER(p.modelAndStripsForProduct.model.nombre) LIKE%?1%")
  List<Product> findByName(String string);

  Page<Product> findAll(Pageable pageable);

  @Query("SELECT p FROM Product p WHERE LOWER(p.nombre) LIKE %?1% OR LOWER(p.modelAndStripsForProduct.model.nombre) LIKE%?1% OR LOWER(p.fabric.nombre) LIKE%?1%")
  Page<Product> findPageByName(String string, Pageable pageable);

  @Query("SELECT p FROM Product p WHERE p.modelAndStripsForProduct.model.tipoPrenda = ?1 ")
  Page<Product> findByTipoPrenda(TipoPrenda tipoPrenda, Pageable pageable);

  @Query("SELECT DISTINCT p FROM Product p WHERE p.fabric.temporada = :season")
  List<Product> findBySeason(String season);

}
