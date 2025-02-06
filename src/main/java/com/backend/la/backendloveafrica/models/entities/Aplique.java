package com.backend.la.backendloveafrica.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "apliques")
public class Aplique extends Input {
  private String color;
  // private Integer cantPorPack;
  // private Double precioPorPack;
  private Double precioUnidad;
  private Integer stock;
}
