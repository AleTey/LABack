package com.backend.la.backendloveafrica.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FabricNombreCodigoTipoImgDTO {

  private Long id;
  private String nombre;
  private Integer codigo;
  private String tipo;
  private byte[] img;
  private String urlFile;
}
