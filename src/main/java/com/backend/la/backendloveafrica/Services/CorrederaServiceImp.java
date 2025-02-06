package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.la.backendloveafrica.models.entities.Corredera;
import com.backend.la.backendloveafrica.models.entities.Supplier;
import com.backend.la.backendloveafrica.repositories.ICorrederaRepository;

@Service
public class CorrederaServiceImp implements ICorrederaService {

  @Autowired
  private ICorrederaRepository correderaRepository;

  @Autowired
  private ISupplierService supplierService;

  @Override
  @Transactional(readOnly = true)
  public List<Corredera> findAllCorrederas() {
    return correderaRepository.findAll();
  }

  @Override
  @Transactional
  public Corredera saveCorredera(Corredera corredera) {
    // corredera.setPrecioUni();
    return correderaRepository.save(corredera);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Corredera> findCorrederaById(Long id) {

    return correderaRepository.findById(id);
  }

  @Override
  @Transactional
  public void deleteCorrederaById(Long id) {
    correderaRepository.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<Corredera> updateCorredera(Long id, Optional<String> nombre, Optional<String> codigo,
      Optional<Long> idProveedor, Optional<String> detalle, Optional<String> forma, Optional<String> medida,
      Optional<String> material,
      Optional<String> color, Optional<Integer> cantPorPack, Optional<Double> precioPorPack, Optional<Double> precioUni,
      Optional<Integer> stockPacks) {

    Optional<Corredera> o = this.findCorrederaById(id);

    if (o.isPresent()) {

      Corredera correderaDb = o.orElseThrow();

      nombre.ifPresent(op -> correderaDb.setNombre(op));
      if (codigo.isPresent()) {
        correderaDb.setCodigo(codigo.orElseThrow());
      }
      idProveedor.ifPresent(op -> {
        Optional<Supplier> oSup = supplierService.findOptionalSupplierById(op);
        oSup.ifPresent(sup -> correderaDb.setProveedor(sup));
      });
      detalle.ifPresent(op -> correderaDb.setDetalle(op));
      // forma.ifPresent(op -> correderaDb.setForma(op));
      medida.ifPresent(op -> correderaDb.setMedida(op));
      material.ifPresent(op -> correderaDb.setMaterial(op));
      // cantPorPack.ifPresent(op -> correderaDb.setCantPorPack(op));
      // precioPorPack.ifPresent(op -> correderaDb.setPrecioPorPack(op));
      // try {
      precioUni.ifPresent(op-> correderaDb.setPrecioUni(op));
      // correderaDb.setPrecioUni(null);
      // } catch (Exception e) {
      // }
      stockPacks.ifPresent(op -> correderaDb.setStock(op));

      return Optional.of(this.saveCorredera(correderaDb));

    }

    return Optional.empty();
  }

  // MÉTODO SIN CONTROLLER(FUERA DE SERVICIO) EL UTILIZADO CON EL PUT ES
  // UpdateCorrederaWithImage
  // @Override
  // @Transactional
  // public Optional<Corredera> updateCorredera(Corredera corredera, Long id) {

  // Optional<Corredera> o = this.findCorrederaById(id);

  // if (o.isPresent()) {

  // Corredera correderaDb = o.orElseThrow();

  // correderaDb.setCodigo(corredera.getCodigo());
  // correderaDb.setStock(corredera.getStock());
  // correderaDb.setUnitOfMeasurement(corredera.getUnitOfMeasurement());
  // correderaDb.setPrecio(corredera.getPrecio());
  // correderaDb.setPrecioEnDolares(corredera.getPrecioEnDolares());
  // correderaDb.setProveedor(corredera.getProveedor());
  // correderaDb.setForma(corredera.getForma());
  // correderaDb.setMedida(corredera.getMedida());
  // correderaDb.setMaterial(corredera.getMaterial());
  // correderaDb.setCantPorPack(corredera.getCantPorPack());
  // correderaDb.setDetalle(corredera.getDetalle());

  // return Optional.of(this.saveCorredera(correderaDb));

  // }

  // return Optional.empty();
  // }

  // @Override
  // @Transactional
  // public Optional<Corredera> updateCorrederaWithImage(Long id, String codigo,
  // Double stock, String unitOfMeasurement,
  // Double precio, Double precioEnDolares, MultipartFile imageFile, Long
  // idProveedor, String forma, String material,
  // String medida, String color, Integer cantPorPack, String detalle) throws
  // IOException {

  // Optional<Corredera> o = this.findCorrederaById(id);

  // if (o.isPresent()) {
  // Corredera correderaDb = o.orElseThrow();

  // Supplier proveedorCorredera = supplierService.findSupplierById(idProveedor);

  // correderaDb.setCodigo(codigo);
  // correderaDb.setStock(stock);
  // correderaDb.setUnitOfMeasurement(unitOfMeasurement);
  // correderaDb.setPrecio(precio);
  // correderaDb.setPrecioEnDolares(precioEnDolares);
  // correderaDb.setProveedor(proveedorCorredera);
  // correderaDb.setForma(forma);
  // correderaDb.setMaterial(material);
  // correderaDb.setMedida(medida);
  // correderaDb.setColor(color);
  // correderaDb.setCantPorPack(cantPorPack);
  // correderaDb.setDetalle(detalle);

  // if (imageFile != null) {

  // byte[] img = imageFile.getBytes();

  // correderaDb.setInsumoImg(img);
  // }

  // return Optional.of(this.saveCorredera(correderaDb));
  // }

  // return Optional.empty();
  // }

  // @Override
  // @Transactional
  // public Corredera saveCorrederaWithImage(String codigo, Double stock, String
  // unitOfMeasurement, Double precio,
  // Double precioEnDolares, MultipartFile imageFile, Supplier proveedor, String
  // forma, String material, String medida,
  // String color,
  // Integer cantPorPack, String detalle) throws IOException {
  // byte[] imageBytes = imageFile.getBytes();

  // Corredera corredera = new Corredera(codigo, stock, unitOfMeasurement, precio,
  // precioEnDolares, proveedor, forma,
  // material,
  // medida, color, cantPorPack, detalle);

  // corredera.setInsumoImg(imageBytes);
  // return this.saveCorredera(corredera);
  // }

  // @Override
  // @Transactional
  // public Corredera saveCorrederaWithImageProveedorInt(String codigo, Double
  // stock, String unitOfMeasurement,
  // Double precio,
  // Double precioEnDolares, MultipartFile imageFile, Long idProveedor, String
  // forma, String material, String medida,
  // String color,
  // Integer cantPorPack, String detalle) throws IOException {
  // byte[] imageBytes = imageFile.getBytes();

  // // Optional<Supplier> proveedor =
  // // supplierService.findOptionalSupplierById(idProveedor);
  // Supplier proveedor = supplierService.findSupplierById(idProveedor);

  // Corredera corredera = new Corredera(codigo, stock, unitOfMeasurement, precio,
  // precioEnDolares, proveedor, forma,
  // material,
  // medida, color, cantPorPack, detalle);

  // corredera.setInsumoImg(imageBytes);
  // return this.saveCorredera(corredera);
  // }

}
