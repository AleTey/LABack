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
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class DetalleInsumo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer cantidad;

  @ElementCollection
  @CollectionTable(name = "cant_por_talle", joinColumns = @JoinColumn(name = "id"))
  @MapKeyColumn(name = "talle")
  @Column(name = "cantidad")
  private Map<String, Integer> cantidadPorTalle;

  @ManyToOne
  private Input input;

  // public DetalleInsumo() {
  // }

  // public DetalleInsumo(Long id, Integer cantidad, Input input) {
  // this.id = id;
  // this.cantidad = cantidad;
  // this.input = input;
  // }

}
