package com.backend.la.backendloveafrica.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tira {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private Double ancho;

  private Double largo;

  // private Fabric tela;

  // @ElementCollection
  // @CollectionTable(name = "cant_tiras_por_talle", joinColumns = @JoinColumn(name = "tira_id"))
  // @MapKeyColumn(name = "talle")
  // @Column(name = "cantidad")
  // private Map<String, Double> cantTirasPorTalle;

}
