package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import java.util.List;
import java.util.Map;

import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ControlSpreadSheetUpdateDTO {

  private Long id;

  // private Map<String, Integer> amountPerSize;

  private List<AmountPerSizeForProduct> amountPerSizeReceivedForProducts;

  private String details;

}
