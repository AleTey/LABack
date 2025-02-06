package com.backend.la.backendloveafrica.models.entities;

import java.util.Map;

import com.backend.la.backendloveafrica.models.enums.AmountPerSizeForProductType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "size_amount_details_spreadsheet")

public class AmountPerSizeForProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_product")
  private Product product;

  // @ManyToOne
  // @JoinColumn(name = "spreadSheet_id")
  // private SpreadSheet spreadSheet;

  @Enumerated(EnumType.STRING)
  private AmountPerSizeForProductType type;

  private String collection;

  @ElementCollection
  @CollectionTable(name = "cantidad_por_talle_producto", joinColumns = @JoinColumn(name = "id"))
  @MapKeyColumn(name = "talle")
  @Column(name = "cantidad")
  private Map<String, Integer> amountPerSize;
}
