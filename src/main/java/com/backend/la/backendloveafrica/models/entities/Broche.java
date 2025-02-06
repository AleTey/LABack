package com.backend.la.backendloveafrica.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Broche {
@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String codigo;
  private String nombre;
  private String medida;
  private String material;
  private String color;
  private Double precioPar;
  private Integer stock;
  private Double cantPorPack;
  private String detalle;

  @ManyToOne
  private Supplier Proveedor;
}
