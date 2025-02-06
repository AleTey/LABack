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
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

  @Id
  private Long id;

  @OneToOne
  private Product product;

  @ElementCollection
  @CollectionTable(name = "cantidad_por_talle_producto_warehouse", joinColumns = @JoinColumn(name = "id"))
  @MapKeyColumn(name = "talle")
  @Column(name = "cantidad")
  private Map<String, Integer> amountPerSize;

  private String section;

  private String location;

  public boolean enable;

}
