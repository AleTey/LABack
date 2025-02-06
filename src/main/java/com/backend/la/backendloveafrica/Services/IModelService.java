package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.la.backendloveafrica.models.dtos.ModelWithDetalleInsumoDtoDTO;
import com.backend.la.backendloveafrica.models.entities.Model;

public interface IModelService {

  public List<Model> findAllModels();

  public Page<ModelWithDetalleInsumoDtoDTO> findAllPage(int page, int size);

  public Page<ModelWithDetalleInsumoDtoDTO> getPageByString(String string, int page, int size);

  public Optional<Model> findModelById(Long id);

  public Model saveModel(Model model);

  public void deleteModel(Long id);

  Optional<Model> updateModel(Long id, Model model);

  List<Model> findModelByString(String string);
}
