package com.backend.la.backendloveafrica.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.backend.la.backendloveafrica.models.dtos.ImageDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.PreparationSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.PreparationSpreadSheet;

public interface IPreparationSpreadSheetService {

  List<PreparationSpreadSheet> findAll();

  Optional<PreparationSpreadSheet> findById(Long id);

  Optional<PreparationSpreadSheetDTO> findDtoById(Long id);

  PreparationSpreadSheet save(PreparationSpreadSheet preparationSpreadSheet);

  void delete(Long id);

  Optional<PreparationSpreadSheetDTO> update(PreparationSpreadSheet preparationSpreadSheet);

  ImageDTO updateImage(Long id, MultipartFile imageFile) throws IOException;

}
