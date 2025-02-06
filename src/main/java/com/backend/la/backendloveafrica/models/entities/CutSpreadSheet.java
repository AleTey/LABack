package com.backend.la.backendloveafrica.models.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "cut_spreadsheets")
public class CutSpreadSheet {

  // @OneToOne(mappedBy = "cutSpreadSh eet")
  // private Lote lote;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AmountPerSizeForProduct> amountPerSizeForProducts;

  private String details;

  private LocalDate publicationDate;

  private Double tableLength = 2.6;

  private boolean isFinished;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FabricLengthDetails> fabricLengthDetails;
}
