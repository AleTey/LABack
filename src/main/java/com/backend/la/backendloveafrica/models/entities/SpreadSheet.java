package com.backend.la.backendloveafrica.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "spreadsheet")
public abstract class SpreadSheet {

  private Long id;

  private Double tableLength = 2.6;
  
  private String details;

  private String publicationDate;

  private boolean isFinished;

}
// @OneToMany
// private Lote lote;
