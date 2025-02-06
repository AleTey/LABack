package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ControlSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.ControlSpreadSheetUpdateDTO;
import com.backend.la.backendloveafrica.models.entities.ControlSpreadSheet;

public interface IControleSpreadSheetService {

  List<ControlSpreadSheet> findAll();

  List<ControlSpreadSheetDTO> getAllDtos();

  Optional<ControlSpreadSheet> findById(Long id);

  Optional<ControlSpreadSheetDTO> getDtoById(Long id);

  ControlSpreadSheet save(ControlSpreadSheet controlSpreadSheet);

  Optional<ControlSpreadSheetDTO> update(ControlSpreadSheet controlSpreadSheet);

}
