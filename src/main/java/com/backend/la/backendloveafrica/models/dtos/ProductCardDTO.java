package com.backend.la.backendloveafrica.models.dtos;

import java.time.LocalDate;

import com.backend.la.backendloveafrica.models.enums.TipoPrenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCardDTO {

  private Long id;

  private String nombre;

  private String codeBarNumber;

  private String urlFile;

  private LocalDate fechaRegistro;

  private String fabric;

  private String model;

  private TipoPrenda tipoPrenda;

  private String temporada;

}
