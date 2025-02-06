package com.backend.la.backendloveafrica.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "elasticos")
public class Elastico extends Input {

  // final static String tipo = "ELASTICO";
  private String ancho;
  private String material;
  private String color;
  private Double mtsPorRollo;
  private Double precioMtr;
  private Integer stock;

}
