package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;
import com.backend.la.backendloveafrica.models.entities.DetalleInsumo;
import com.backend.la.backendloveafrica.models.entities.InputQuantityForSpreadSheet;
import com.backend.la.backendloveafrica.models.entities.PreparationSpreadSheet;

@Service
public class InputQuantityForSpreadSheetService implements IInputQuantityForSpreadSheetService {

  @Override
  public List<InputQuantityForSpreadSheet> calculateInputs(PreparationSpreadSheet preparationSpreadSheet) {
    // Optional<PreparationSpreadSheet> o =
    // preparationSpreadSheetService.findById(preparationSpreadSheet.getId());

    try {
      for (InputQuantityForSpreadSheet inputQ : preparationSpreadSheet.getInputQuantityForSpreadSheet()) {
        inputQ.setQuantity(0.0);
        for (AmountPerSizeForProduct amountPerSize : preparationSpreadSheet.getAmountPerSizeForProducts()) {

          Integer cantTotal = amountPerSize.getAmountPerSize().values().stream()
              .mapToInt(Integer::intValue)
              .sum();

          for (DetalleInsumo detalleInsumo : amountPerSize.getProduct().getModelAndStripsForProduct().getModel()
              .getDetalleInsumos()) {

            if (inputQ.getInput().getId() == detalleInsumo.getInput().getId()) {
              if (detalleInsumo.getCantidad() != null) {
                double prevCant = inputQ.getQuantity();
                inputQ.setQuantity(prevCant + (detalleInsumo.getCantidad() * cantTotal));
              } else {

                Double sum = 0.0;

                for (Map.Entry<String, Integer> entryDetail : detalleInsumo.getCantidadPorTalle().entrySet()) {

                  for (Map.Entry<String, Integer> entryAmount : amountPerSize.getAmountPerSize().entrySet()) {
                    if (entryDetail.getKey().equalsIgnoreCase(entryAmount.getKey())) {
                      sum = sum + (entryDetail.getValue() * entryAmount.getValue());
                    }
                  }
                }
                Double prevCantPerSize = inputQ.getQuantity();
                inputQ.setQuantity(prevCantPerSize + sum);
              }
            }
          }
        }
      }
      return preparationSpreadSheet.getInputQuantityForSpreadSheet();
      // }

    } catch (Exception e) {
      // e.set
    }
    return null;
  }

}
