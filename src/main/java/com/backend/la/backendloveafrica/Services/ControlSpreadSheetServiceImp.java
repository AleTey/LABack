package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.mapper.ControlSpreadSheetMapper;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ControlSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ControlSpreadSheetUpdateDTO;
import com.backend.la.backendloveafrica.models.entities.AmountPerSizeForProduct;
import com.backend.la.backendloveafrica.models.entities.ControlSpreadSheet;
import com.backend.la.backendloveafrica.repositories.IControlSpreadSheetRepository;

@Service
public class ControlSpreadSheetServiceImp implements IControleSpreadSheetService {

  @Autowired
  private IControlSpreadSheetRepository controlSpreadSheetRepository;

  @Autowired
  private ControlSpreadSheetMapper controlSpreadSheetMapper;

  @Override
  public List<ControlSpreadSheet> findAll() {
    return controlSpreadSheetRepository.findAll();
  }

  @Override
  public Optional<ControlSpreadSheet> findById(Long id) {
    return controlSpreadSheetRepository.findById(id);
  }

  @Override
  public ControlSpreadSheet save(ControlSpreadSheet controlSpreadSheet) {
    return controlSpreadSheetRepository.save(controlSpreadSheet);
  }

  // REALIZAR METHOD EN MAPPER QUE MAPEE UNA LISTA DE ControlSpreadsheet a DTO
  @Override
  public List<ControlSpreadSheetDTO> getAllDtos() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllDtos'");
  }

  @Override
  public Optional<ControlSpreadSheetDTO> getDtoById(Long id) {
    Optional<ControlSpreadSheet> o = this.findById(id);
    if (o.isPresent()) {
      // System.out.println(o.get().toString());
      return Optional.of(controlSpreadSheetMapper.toControlSpreadSheetDTO(o.get()));
    }
    return Optional.empty();
  }

  @Override
  public Optional<ControlSpreadSheetDTO> update(ControlSpreadSheet controlSpreadSheet) {

    Optional<ControlSpreadSheet> o = this.findById(controlSpreadSheet.getId());

    if (o.isPresent()) {
      ControlSpreadSheet controlSpreadSheetDb = o.get();
      controlSpreadSheetDb.getAmountPerSizeReceivedForProducts().clear();
      controlSpreadSheetDb.getAmountPerSizeReceivedForProducts()
          .addAll(controlSpreadSheet.getAmountPerSizeReceivedForProducts());
      controlSpreadSheetDb.setDetails(controlSpreadSheet.getDetails());

      return Optional.of(controlSpreadSheetMapper.toControlSpreadSheetDTO(this.save(controlSpreadSheetDb)));
    }

    return Optional.empty();
  }
  // @Override
  // public Optional<ControlSpreadSheetDTO> update(ControlSpreadSheetUpdateDTO
  // controlSpreadSheetUpdateDTO) {
  // Optional<ControlSpreadSheet> o =
  // this.findById(controlSpreadSheetUpdateDTO.getId());

  // if (o.isPresent()) {
  // ControlSpreadSheet controlSpreadSheetDb = o.get();
  // controlSpreadSheetDb.setDetails(controlSpreadSheetUpdateDTO.getDetails());

  // AmountPerSizeForProduct amountPerSizeForProduct =
  // controlSpreadSheetDb.getAmountPerSizeReceivedForProducts().stream()
  // .map(amount -> {
  // amount.getAmountPerSize().forEach(a -> {
  // a.
  // });
  // controlSpreadSheetUpdateDTO.getAmountPerSizeReceivedForProducts().stream()
  // .filter(newAmount -> amount.getId() == newAmount.getId());

  // })

  // for (Map.Entry<String, Integer> entry :
  // controlSpreadSheetUpdateDTO.getAmountPerSize().entrySet()) {
  // String key = entry.getKey();
  // Integer newValue = entry.getValue();

  // if (controlSpreadSheetDb.) {

  // }
  // }
  // }

  // return null;
  // }

}
