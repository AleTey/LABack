package com.backend.la.backendloveafrica.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputIdNombreDetalleDTO {

  private Long id;

  private String nombre;

  private String detalle;

}
