package com.backend.la.backendloveafrica.models.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.backend.la.backendloveafrica.models.enums.TipoPrenda;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  @Enumerated(EnumType.STRING)
  private TipoPrenda tipoPrenda;

  @ElementCollection
  private List<String> tallesDisponibles;

  @OneToMany(cascade = CascadeType.ALL)
  private List<DetalleInsumo> detalleInsumos;

  @OneToMany(cascade = CascadeType.ALL)
  private List<DetalleTiraModelo> detalleTiraModelo;

  private String detalle;

  private LocalDate fechaDeRegistro;

  @PrePersist
  public void setFechaDeRegistro() {
    this.fechaDeRegistro = LocalDate.now();
  }

}
