package com.backend.la.backendloveafrica.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.backend.la.backendloveafrica.Services.external.IS3Service;
import com.backend.la.backendloveafrica.mapper.FabricMapper;
import com.backend.la.backendloveafrica.models.dtos.FabricNoPriceNoSupp;
import com.backend.la.backendloveafrica.models.dtos.FabricNombreCodigoTipoImgDTO;
import com.backend.la.backendloveafrica.models.entities.Fabric;
import com.backend.la.backendloveafrica.repositories.IFabricRepository;

@Service
public class FabricServiceImpl implements IFabricService {

  @Autowired
  private IFabricRepository fabricRepo;

  @Autowired
  private FabricMapper fabricMapper;

  @Autowired
  private IS3Service s3Service;

  @Override
  @Transactional(readOnly = true)
  public List<Fabric> findAll() {
    return (List<Fabric>) fabricRepo.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Fabric> findAll(Pageable pageable) {
    return fabricRepo.findAll(pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Fabric> findFabricById(Long id) {
    return fabricRepo.findById(id);
  }

  @Override
  @Transactional
  public Fabric saveFabric(Fabric fabric) {
    return fabricRepo.save(fabric);
  }

  @Override
  @Transactional
  public Fabric saveFabricWImage(String nombre, String codigo, String color, String tipo,
      String temporada, Integer stock, Double precio,
      Integer codeBarNumb, String tags, MultipartFile imageFile) throws IOException {

    // Convierte el archivo de imagen a un array de bytes
    // byte[] imageBytes = imageFile.getBytes();

    Fabric fabric = new Fabric();
    fabric.setNombre(nombre);
    fabric.setCodigo(codigo);
    fabric.setColor(color);
    fabric.setTipo(tipo);
    fabric.setTemporada(temporada);
    fabric.setStock(stock);
    fabric.setPrecio(precio);
    fabric.setCodeBarNumb(codeBarNumb);
    fabric.setTags(tags);
    // fabric.setImg(imageBytes);

    Fabric fabricDb = fabricRepo.save(fabric);
    if (!imageFile.isEmpty()) {
      try {
        Optional<Fabric> fabricWithImg = this.addImg(fabricDb.getId(), imageFile);
        fabricDb.setUrlFile(fabricWithImg.orElseThrow().getUrlFile());
        System.out.println("============================================");
        System.out.println(fabricWithImg.get().getUrlFile());
        System.out.println("============================================");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println("==========================================");
    System.out.println(fabricDb.getUrlFile());
    System.out.println("==========================================");
    return fabricDb;
  }

  @Override
  @Transactional
  public void deleteFabric(Long id) {
    Optional<Fabric> o = this.findFabricById(id);
    if (o.isPresent()) {
      try {
        s3Service.deleteFile(o.get().getUrlFile().substring(o.get().getUrlFile().lastIndexOf("/") + 1));
      } catch (Exception e) {
      }
    }
    fabricRepo.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<Fabric> updateFabric(Fabric fabric, Long id) {
    System.out.println("SERVICE updateFabric");
    Optional<Fabric> o = this.findFabricById(id);
    if (o.isPresent()) {
      Fabric fabricDb = o.orElseThrow();
      fabricDb.setNombre(fabric.getNombre());
      fabricDb.setCodigo(fabric.getCodigo());
      fabricDb.setColor(fabric.getColor());
      fabricDb.setTipo(fabric.getTipo());
      fabricDb.setTemporada(fabric.getTemporada());
      fabricDb.setStock(fabric.getStock());
      fabricDb.setPrecio(fabric.getPrecio());
      fabricDb.setCodeBarNumb(fabric.getCodeBarNumb());
      fabricDb.setTags(fabric.getTags());
      // fabricDb.setImg(fabric.getImg());
      fabricDb.setProveedor(fabric.getProveedor());
      return Optional.of(this.saveFabric(fabricDb));
    }
    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<Fabric> addImg(Long id, MultipartFile imageFile) throws Exception {
    System.out.println("ENTRANDO ADD IMAGE");
    Optional<Fabric> o = findFabricById(id);
    if (o.isPresent()) {
      Fabric fabricDb = o.orElseThrow();

      try {
        System.out.println("ENTRANDO AL TRY");
        fabricDb.setUrlFile(s3Service.uploadFile(s3Service.keyGenerator(o.get().getNombre(), id), imageFile));

        System.out.println(fabricDb.getUrlFile());

      } catch (Exception e) {
        throw new Exception("Error uploading image");
      }

      // byte[] imageBytes = imageFile.getBytes();
      // fabricDb.setImg(imageBytes);
      return Optional.of(this.saveFabric(fabricDb));
    }
    return Optional.empty();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Fabric> findByString(String string, int page, int size) {
    if (string == null || string.isEmpty()) {
      return Page.empty();
    }
    Pageable pageable = PageRequest.of(page, size);
    return fabricRepo.findByString(string.toLowerCase(), pageable);
  }

  @Override
  public Page<FabricNoPriceNoSupp> findPageDtoByString(String string, int page, int size) {
    if (string == null || string.isEmpty()) {
      return Page.empty();
    }
    Pageable pageable = PageRequest.of(page, size);
    return fabricMapper.toFabricNoPriceNoSuppPageDTO(fabricRepo.findByString(string.toLowerCase(), pageable));
  }

  @Override
  public List<String> findDistinctSeasonFromFabric() {
    return fabricRepo.findDistinctSeason();
  }

  @Override
  public List<FabricNombreCodigoTipoImgDTO> findFabricsBySeason(String season) {
    System.out.println("SIZE---------------------------------");
    System.out.println("SIZE: " + fabricRepo.findFabricBySeason(season).size());
    return fabricMapper.convertToListFabricNombreCodigoTipoImgDto(fabricRepo.findFabricBySeason(season));
  }

  @Override
  public Page<FabricNoPriceNoSupp> findPageDto(Pageable pageable) {
    return fabricMapper.toFabricNoPriceNoSuppPageDTO(this.findAll(pageable));
  }

}
