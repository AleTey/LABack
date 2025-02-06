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
public class DetalleTiraModelo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Tira tira;

  @ElementCollection
  @CollectionTable(name = "map_tiras_por_talle", joinColumns = @JoinColumn(name = "id"))
  @MapKeyColumn(name = "talle")
  @Column(name = "cantidad_tiras")
  private Map<String, Double> tirasPorTalle;

  // public CantTirasPorTalle(Long id, Tira tira, Map<String, Double> tirasPorTalle) {
  //   this.id = id;
  //   this.tira = tira;
  //   this.tirasPorTalle = tirasPorTalle;
  // }

}
