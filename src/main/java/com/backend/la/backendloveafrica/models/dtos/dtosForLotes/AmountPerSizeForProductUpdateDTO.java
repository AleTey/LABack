package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmountPerSizeForProductUpdateDTO {

  private Long id;

  private Map<String, Integer> amountPerSize;

}
