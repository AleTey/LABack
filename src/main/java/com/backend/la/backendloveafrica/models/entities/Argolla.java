package com.backend.la.backendloveafrica.models.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Argolla extends Input {

  private String circunferenciaInterna;

  private String circunferenciaExterna;

  private String material;

  private String color;

  private Double precioUni;

  private Integer stock;

}