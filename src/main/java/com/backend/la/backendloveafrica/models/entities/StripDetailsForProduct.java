package com.backend.la.backendloveafrica.models.entities;

import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StripDetailsForProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Fabric fabric;

  private Double width;

  @ElementCollection
  @CollectionTable(name = "map_strips_per_size_for_product", joinColumns = @JoinColumn(name = "id"))
  @MapKeyColumn(name = "talle")
  @Column(name = "cantidad_tiras")
  private Map<String, Double> quantityPerSize;
}
