package com.backend.la.backendloveafrica.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.la.backendloveafrica.models.entities.Tira;
import com.backend.la.backendloveafrica.repositories.ITiraRepository;

@Service
public class tiraServiceImp implements ITiraService {

  @Autowired
  private ITiraRepository tiraRepository;

  @Override
  public List<Tira> findAllTiras() {

    return tiraRepository.findAll();
  }
}
