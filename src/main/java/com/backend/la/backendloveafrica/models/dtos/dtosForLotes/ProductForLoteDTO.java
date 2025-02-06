package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import com.backend.la.backendloveafrica.models.enums.TipoPrenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductForLoteDTO {

  private Long id;

  private String nombre;

  private byte[] img;

  private TipoPrenda TipoPrenda;

  private String urlFile;

}
