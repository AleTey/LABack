package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.mapper.ModelMapper;
import com.backend.la.backendloveafrica.models.dtos.ModelWithDetalleInsumoDtoDTO;
import com.backend.la.backendloveafrica.models.entities.Model;
import com.backend.la.backendloveafrica.repositories.IModelRepository;

@Service
public class ModelServiceImp implements IModelService {

  @Autowired
  private IModelRepository modelRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private IDetalleInsumoService detalleInsumoService;

  @Override
  @Transactional(readOnly = true)
  public List<Model> findAllModels() {
    return modelRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Model> findModelById(Long id) {
    return modelRepository.findById(id);
  }

  @Override
  @Transactional
  public Model saveModel(Model model) {

    System.out.println("MODELS --------------");
  System.out.println(model.getDetalleTiraModelo().toString());

    return modelRepository.save(model);
  }

  @Override
  @Transactional
  public void deleteModel(Long id) {
    modelRepository.deleteById(id);
  }

  @Override
  public Optional<Model> updateModel(Long id, Model model) {
    Optional<Model> o = findModelById(id);
    if (o.isPresent()) {
      return Optional.of(this.saveModel(model));
    }
    return Optional.empty();
  }

  @Override
  public List<Model> findModelByString(String string) {
    return modelRepository.findModelByString(string);
    // return
    // modelMapper.toModelWithDetalleInsumoDtoDTOsList(modelRepository.findModelByString(string));
  }

  @Override
  public Page<ModelWithDetalleInsumoDtoDTO> findAllPage(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return modelMapper.toModelWithDetalleInsumoDtoDTOsPage(modelRepository.findAll(pageable));
  }

  @Override
  public Page<ModelWithDetalleInsumoDtoDTO> getPageByString(String string, int page, int size) {

    Pageable pageable = PageRequest.of(page, size);

    return modelMapper.toModelWithDetalleInsumoDtoDTOsPage(modelRepository.getPageByString(string, pageable));
  }

}
