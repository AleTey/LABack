package com.backend.la.backendloveafrica.models.dtos.dtosForLotes;

import org.springframework.stereotype.Component;

import com.backend.la.backendloveafrica.models.entities.WorkShop;

@Component
public class WorkShopMapper {

  public WorkShopDTO toWorkshopDto(WorkShop workShop) {
    return new WorkShopDTO(workShop.getId(), workShop.getName());
  }

}
