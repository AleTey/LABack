package com.backend.la.backendloveafrica.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.backend.la.backendloveafrica.Exception.CustomAccessDeniedException;
import com.backend.la.backendloveafrica.Services.security.IUserService;
import com.backend.la.backendloveafrica.mapper.PreparationSpreadSheetMapper;
import com.backend.la.backendloveafrica.models.dtos.ImageDTO;
import com.backend.la.backendloveafrica.models.dtos.dtosForLotes.PreparationSpreadSheetDTO;
import com.backend.la.backendloveafrica.models.entities.InputQuantityForSpreadSheet;
import com.backend.la.backendloveafrica.models.entities.PreparationSpreadSheet;
import com.backend.la.backendloveafrica.repositories.IPreparationSpreadSheetRepository;

@Service
public class PreparationSpreadSheetServiceImp implements IPreparationSpreadSheetService {

  @Autowired
  private IPreparationSpreadSheetRepository preparationSpreadSheetRepository;

  @Autowired
  private PreparationSpreadSheetMapper preparationSpreadSheetMapper;

  @Autowired
  private IInputQuantityForSpreadSheetService inputQuantityForSpreadSheetService;

  @Autowired
  private IUserService userService;

  @Autowired
  private ILoteService loteService;

  @Override
  public List<PreparationSpreadSheet> findAll() {
    return preparationSpreadSheetRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PreparationSpreadSheet> findById(Long id) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
    System.out.println(auth);
    System.out.println("XXXXXXXXXXXXXXXXXXXXXX");

    // if (userService.hasAnyRole("ROLE_WORKSHOP")) {
    //   boolean isSameUsername = userService
    //       .sameUsername(loteService.findLoteByCutSpreadSheetId(id).orElseThrow().getWorkShop().getUser().getUsername());
    //   if (!isSameUsername) {
    //     // throw new Exception("Acceso denegado");
    //     throw new CustomAccessDeniedException("Denied access");
    //   }
    // }

    return preparationSpreadSheetRepository.findById(id);
  }

  @Override
  public PreparationSpreadSheet save(PreparationSpreadSheet preparationSpreadSheet) {
    return preparationSpreadSheetRepository.save(preparationSpreadSheet);
  }

  @Override
  public void delete(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PreparationSpreadSheetDTO> findDtoById(Long id) {
    Optional<PreparationSpreadSheet> o = this.findById(id);

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
    // System.out.println(auth.getCredentials().toString());
    System.out.println("XXXXXXXXXXXXXXXXXXXXXX");

    if (o.isPresent()) {

      if (userService.hasAnyRole("ROLE_WORKSHOP")) {
        boolean isSameUsername = userService
            .sameUsername(
                loteService.findLoteByCutSpreadSheetId(id).orElseThrow().getWorkShop().getUser().getUsername());
        if (!isSameUsername) {
          // throw new Exception("Acceso denegado");
          throw new CustomAccessDeniedException("Denied access");
        }
      }

      List<InputQuantityForSpreadSheet> inputs = o.get().getInputQuantityForSpreadSheet();
      System.out.println("-----------------");
      System.out.println(inputs.toString());
      System.out.println("-----------------");
      PreparationSpreadSheetDTO oDto = preparationSpreadSheetMapper.toPreparationSpreadSheetDTO(o.get());
      return Optional.of(oDto);
    }
    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<PreparationSpreadSheetDTO> update(PreparationSpreadSheet preparationSpreadSheet) {
    Optional<PreparationSpreadSheet> o = this.findById(preparationSpreadSheet.getId());

    if (o.isPresent()) {
      PreparationSpreadSheet preparationSpreadSheetDb = o.get();
      // preparationSpreadSheetDb.setAmountPerSizeForProducts(preparationSpreadSheet.getAmountPerSizeForProducts());
      preparationSpreadSheetDb.getAmountPerSizeForProducts().clear();
      preparationSpreadSheetDb.getAmountPerSizeForProducts()
          .addAll(preparationSpreadSheet.getAmountPerSizeForProducts());
      // preparationSpreadSheetDb.setInputQuantityForSpreadSheet(preparationSpreadSheet.getInputQuantityForSpreadSheet());
      if (preparationSpreadSheet.getInputQuantityForSpreadSheet() != null) {
        preparationSpreadSheetDb.getInputQuantityForSpreadSheet().clear();
        preparationSpreadSheetDb.getInputQuantityForSpreadSheet()
            // .addAll(inputQuantityForSpreadSheetService.calculateInputs(preparationSpreadSheet));
            .addAll(preparationSpreadSheet.getInputQuantityForSpreadSheet());
      }
      preparationSpreadSheetDb.setDetails(preparationSpreadSheet.getDetails());

      PreparationSpreadSheet saved = this.save(preparationSpreadSheetDb);

      PreparationSpreadSheetDTO savedDto = preparationSpreadSheetMapper.toPreparationSpreadSheetDTO(saved);

      return Optional.of(savedDto);
    }
    return Optional.empty();

  }

  @Override
  public ImageDTO updateImage(Long id, MultipartFile imageFile) throws IOException {
    Optional<PreparationSpreadSheet> o = this.findById(id);

    if (o.isPresent()) {
      PreparationSpreadSheet pSpreadSheetDb = o.get();
      byte[] imageBytes = imageFile.getBytes();
      pSpreadSheetDb.setImg(imageBytes);
      this.save(pSpreadSheetDb);

      ImageDTO img = new ImageDTO(imageBytes);

      return img;
    }
    return null;
  }

}
