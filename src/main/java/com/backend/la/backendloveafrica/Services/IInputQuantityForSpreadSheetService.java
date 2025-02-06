package com.backend.la.backendloveafrica.Services;

import java.util.List;

import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;
import com.backend.la.backendloveafrica.models.entities.InputQuantityForSpreadSheet;
import com.backend.la.backendloveafrica.models.entities.PreparationSpreadSheet;

public interface IInputQuantityForSpreadSheetService {

  List<InputQuantityForSpreadSheet> calculateInputs(PreparationSpreadSheet preparationSpreadSheet);

  // List<InputQuantityForSpreadSheet> calculateOneInputs(AmountPerSizeForProduct amountPerSizeForProducts);
}
