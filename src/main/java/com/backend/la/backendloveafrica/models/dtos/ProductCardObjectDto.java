package com.backend.la.backendloveafrica.models.dtos;

import java.time.LocalDate;

import com.backend.la.backendloveafrica.models.entities.ModelAndStripsForProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCardObjectDto {

  private Long id;
  private String nombre;
  private String codeBarNumber;
  private String colorForro;
  private String urlFile;
  // private byte[] img;
  private LocalDate fechaRegistro;
  private FabricNombreCodigoTipoImgDTO fabric;
  private ModelAndStripsForProductDTO modelAndStripsForProduct;

}
