package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.CutSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.CutSpreadSheet;

public interface ICutSpreadSheetService {

  List<CutSpreadSheet> findAll();

  Optional<CutSpreadSheet> findById(Long id);

  Optional<CutSpreadSheetDTO> findDtoById(Long id);

  CutSpreadSheet save(CutSpreadSheet cutSpreadSheet);

  void deleteById(Long id);

  Optional<CutSpreadSheet> update(CutSpreadSheet cutSpreadSheet);
  
  Optional<CutSpreadSheet> updateWithDto(CutSpreadSheetDTO cutSpreadSheet);

  CutSpreadSheetDTO convertCutSpreadSheetToDTO(Optional<CutSpreadSheet> o);
}
