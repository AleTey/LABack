package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import com.backend.la.backendloveafrica.models.entities.Corredera;

public interface ICorrederaService {

  public List<Corredera> findAllCorrederas();

  public Corredera saveCorredera(Corredera corredera);

  
  public Optional<Corredera> findCorrederaById(Long id);
  
  public void deleteCorrederaById(Long id);
  
  public Optional<Corredera> updateCorredera(Long id, Optional<String> nombre, Optional<String> codigo,
  Optional<Long> idProveedor, Optional<String> detalle, Optional<String> forma, Optional<String> medida,
  Optional<String> material,
  Optional<String> color, Optional<Integer> cantPorPack, Optional<Double> precioPorPack, Optional<Double> precioUni,
  Optional<Integer> stockPacks);
  // public Corredera saveCorrederaWithImage(String codigo, Double stock, String
  // unitOfMeasurement, Double precio,
  // Double precioEnDolares, MultipartFile imageFile, Supplier proveedor, String
  // forma, String material, String medida,
  // String color,
  // Integer cantPorPack, String detalle) throws IOException;

  // public Corredera saveCorrederaWithImageProveedorInt(String codigo, Double
  // stock, String unitOfMeasurement,
  // Double precio,
  // Double precioEnDolares, MultipartFile imageFile, Long idProveedor, String
  // forma, String material, String medida,
  // String color,
  // Integer cantPorPack, String detalle) throws IOException;

  // public Optional<Corredera> updateCorrederaWithImage(Long id, String codigo,
  // Double stock, String unitOfMeasurement,
  // Double precio, Double precioEnDolares, MultipartFile imageFile, Long
  // idProveedor, String forma, String material,
  // String medida, String color, Integer cantPorPack, String detalle) throws
  // IOException;
}
