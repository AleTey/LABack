package com.backend.la.backendloveafrica.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FabricNoPriceNoSupp {
  private Long id;
  private String nombre;
  private String codigo;
  private String color;
  private String tipo;
  private String temporada;
  private Integer stock;
  private Integer codeBarNumb;
  private String tags;
  private byte[] img;
  private String urlFile;

}
