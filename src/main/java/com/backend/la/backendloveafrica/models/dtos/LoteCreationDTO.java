package com.backend.la.backendloveafrica.models.dtos;

import java.util.List;

import com.backend.la.backendloveafrica.models.entities.Product;
import com.backend.la.backendloveafrica.models.entities.WorkShop;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoteCreationDTO {

  @NotNull
  private List<Product> products;
  @NotNull
  private WorkShop workShop;
  private String additionalDetails;

}
