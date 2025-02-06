package com.backend.la.backendloveafrica.models.entities;

import com.backend.la.backendloveafrica.models.enums.TipoGancho;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Gancho extends Input {

  @Enumerated(EnumType.STRING)
  private TipoGancho tipoGancho;
  private String medida;
  private String material;
  private String color;
  // private Integer cantPorPack;
  // private Double precioPorPack;
  private Double precioUni;
  private Integer stock;

}
