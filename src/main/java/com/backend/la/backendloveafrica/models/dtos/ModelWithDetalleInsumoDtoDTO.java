package com.backend.la.backendloveafrica.models.dtos;

import java.time.LocalDate;
import java.util.List;

import com.backend.la.backendloveafrica.models.entities.DetalleInsumo;
import com.backend.la.backendloveafrica.models.entities.DetalleTiraModelo;
import com.backend.la.backendloveafrica.models.enums.TipoPrenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelWithDetalleInsumoDtoDTO {

  private Long id;
  private String nombre;
  private TipoPrenda tipoPrenda;

  private List<String> tallesDisponibles;

  private List<DetalleInsumoDTO> detalleInsumos;

  private List<DetalleTiraModelo> detalleTiraModelo;

  private String detalle;

  private LocalDate fechaDeRegistro;

}
