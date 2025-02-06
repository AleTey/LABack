package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.mapper.WorkShopSpreadSheetMapper;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.WorkShopSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.WorkShopSpreadSheet;
import com.backend.la.backendloveafrica.repositories.IWorkShopSpreadSheetRepository;

@Service
public class WorkShopSpreadSheetServiceImp implements IWorkShopSpreadSheetService {

  @Autowired
  private IWorkShopSpreadSheetRepository workShopSpreadSheetRepository;

  @Autowired
  private WorkShopSpreadSheetMapper workShopSpreadSheetMapper;

  @Override
  public List<WorkShopSpreadSheet> findAll() {
    return workShopSpreadSheetRepository.findAll();
  }

  @Override
  public Optional<WorkShopSpreadSheet> findById(Long id) {
    return workShopSpreadSheetRepository.findById(id);
  }

  @Override
  public Optional<WorkShopSpreadSheetDTO> getDtoById(Long id) {
    Optional<WorkShopSpreadSheet> o = this.findById(id);
    if (o.isPresent()) {
      return Optional.of(workShopSpreadSheetMapper.toWorkShopSpreadSheetDTO(o.get()));
    }
    return Optional.empty();
  }

  @Override
  public WorkShopSpreadSheet save(WorkShopSpreadSheet workShopSpreadSheet) {
    return workShopSpreadSheetRepository.save(workShopSpreadSheet);
  }

  @Override
  public Optional<WorkShopSpreadSheetDTO> update(WorkShopSpreadSheet workShopSpreadSheet) {
    Optional<WorkShopSpreadSheet> o = this.findById(workShopSpreadSheet.getId());
    if (o.isPresent()) {
      WorkShopSpreadSheet workShopSpreadSheetDb = o.get();
      workShopSpreadSheetDb.getAmountPerSizeForProducts().clear();
      workShopSpreadSheetDb.getAmountPerSizeForProducts().addAll(workShopSpreadSheet.getAmountPerSizeForProducts());
      workShopSpreadSheetDb.getAmountPerSizeDefectiveForProducts().clear();
      workShopSpreadSheetDb.getAmountPerSizeDefectiveForProducts()
          .addAll(workShopSpreadSheet.getAmountPerSizeDefectiveForProducts());
      workShopSpreadSheetDb.setDetails(workShopSpreadSheet.getDetails());
      // workShopSpreadSheetDb.setFinished(workShopSpreadSheet.isFinished());

      return Optional.of(workShopSpreadSheetMapper.toWorkShopSpreadSheetDTO(this.save(workShopSpreadSheetDb)));
    }

    return Optional.empty();
  }

}
