package com.backend.la.backendloveafrica.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.backend.la.backendloveafrica.models.dtos.FabricNoPriceNoSupp;
import com.backend.la.backendloveafrica.models.dtos.FabricNombreCodigoTipoImgDTO;
import com.backend.la.backendloveafrica.models.entities.Fabric;

public interface IFabricService {

  List<Fabric> findAll();

  Page<Fabric> findAll(Pageable pageable);

  Page<FabricNoPriceNoSupp> findPageDto(Pageable pageable);

  Optional<Fabric> findFabricById(Long id);

  Fabric saveFabric(Fabric fabric);

  Fabric saveFabricWImage(String nombre, String codigo, String color, String tipo,
      String temporada, Integer stock, Double precio,
      Integer codeBarNumb, String tags, MultipartFile imageFile) throws IOException;

  void deleteFabric(Long id);

  Optional<Fabric> addImg(Long id, MultipartFile imageFile) throws IOException, Exception;

  Optional<Fabric> updateFabric(Fabric fabric, Long id);

  Page<Fabric> findByString(String string, int page, int size);
  
  Page<FabricNoPriceNoSupp> findPageDtoByString(String string, int page, int size);

  List<String> findDistinctSeasonFromFabric();

  List<FabricNombreCodigoTipoImgDTO> findFabricsBySeason(String season);

}
