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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workshop-spreadsheet")
public class WorkShopSpreadSheet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AmountPerSizeForProduct> amountPerSizeForProducts;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AmountPerSizeForProduct> amountPerSizeDefectiveForProducts;

  private String details;
  private LocalDate publicationDate;
  private boolean isFinished;
}
