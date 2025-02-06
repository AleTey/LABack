package com.backend.la.backendloveafrica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.dtos.supplier.SupplierIdEmpresaDTO;
import com.backend.la.backendloveafrica.models.entities.Supplier;

@Component
public class SupplierMapper {

  public SupplierIdEmpresaDTO supplierToSupplierIdEmpresaDTO(Supplier supplier) {
    return new SupplierIdEmpresaDTO(supplier.getId(), supplier.getEmpresa());
  }

  public List<SupplierIdEmpresaDTO> toSupplierIdEmpresaDTOs(List<Supplier> suppliers) {
    List<SupplierIdEmpresaDTO> simplestSuppliersDTO = suppliers.stream()
        .map(this::supplierToSupplierIdEmpresaDTO)
        .collect(Collectors.toList());
    return simplestSuppliersDTO;
  }

}
