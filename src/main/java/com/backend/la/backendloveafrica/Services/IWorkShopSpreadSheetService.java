package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.WorkShopSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.WorkShopSpreadSheet;

public interface IWorkShopSpreadSheetService {

  List<WorkShopSpreadSheet> findAll();

  Optional<WorkShopSpreadSheet> findById(Long id);

  Optional<WorkShopSpreadSheetDTO> getDtoById(Long id);

  WorkShopSpreadSheet save(WorkShopSpreadSheet workShopSpreadSheet);

  Optional<WorkShopSpreadSheetDTO> update(WorkShopSpreadSheet workShopSpreadSheet);

}
