package com.backend.la.backendloveafrica.models.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Etiqueta extends Input {

  private String marca;
  private Double precioUnidad;
  private Integer stock;

}
