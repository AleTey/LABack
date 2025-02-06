package com.backend.la.backendloveafrica.models.dtos;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleInsumoDTO {

  private Long id;

  private Integer cantidad;

  private Map<String, Integer> cantidadPorTalle;

  private InputIdNombreCodigoDTO input;

}
