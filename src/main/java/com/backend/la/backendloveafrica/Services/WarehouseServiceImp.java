package com.backend.la.backendloveafrica.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.entities.Model;
import com.backend.la.backendloveafrica.models.entities.Product;
import com.backend.la.backendloveafrica.models.entities.Warehouse;
import com.backend.la.backendloveafrica.models.enums.TipoPrenda;
import com.backend.la.backendloveafrica.repositories.IWarehouseRepository;

@Service
public class WarehouseServiceImp implements IWarehouseService {

  @Autowired
  private IWarehouseRepository warehouseRepository;

  // @Autowired
  // private IProductService productService;

  @Override
  public List<Warehouse> findAll() {
    return warehouseRepository.findAll();
  }

  @Override
  public Optional<Warehouse> findById(Long id) {
    return warehouseRepository.findById(id);
  }

  @Override
  public Warehouse save(Warehouse warehouse) {
    return warehouseRepository.save(warehouse);
  }

  @Override
  public void deleteById(Long id) {
    Optional<Warehouse> o = this.findById(id);
    if (o.isPresent()) {
      warehouseRepository.deleteById(id);
    }
  }

  @Override
  public Optional<Warehouse> update(Warehouse warehouse) {
    Optional<Warehouse> o = this.findById(warehouse.getId());
    if (o.isPresent()) {
      Warehouse warehouseDb = o.get();
      warehouseDb.setAmountPerSize(warehouse.getAmountPerSize());
      warehouseDb.setSection(warehouse.getSection());
      warehouseDb.setLocation(warehouse.getLocation());
      return Optional.of(this.save(warehouseDb));
    }
    return Optional.empty();
  }

  @Override
  public Warehouse saveFromProduct(Product product, Model model) {
    Warehouse warehouse = new Warehouse();
    warehouse.setId(product.getId());
    warehouse.setProduct(product);

    Map<String, Integer> amountPerSize = new HashMap<>();
    for (String size : model.getTallesDisponibles()) {
      amountPerSize.put(size, 0);
    }
    warehouse.setAmountPerSize(amountPerSize);
    return this.save(warehouse);
    // return null;
  }

  @Override
  public Page<Warehouse> findPageByString(String string, int page, int size) {
    if (string == null || string.isEmpty()) {
      return Page.empty();
    }
    Pageable pageable = PageRequest.of(page, size);

    try {
      TipoPrenda tp = TipoPrenda.valueOf(string.toUpperCase());
      return warehouseRepository.findByTipoPrenda(tp, pageable);
    } catch (Exception e) {
      // TODO: handle exception
    }

    return warehouseRepository.findPageByString(string.toLowerCase(), pageable);
  }

}
