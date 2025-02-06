package com.backend.la.backendloveafrica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.la.backendloveafrica.models.entities.Corredera;

@Repository
public interface ICorrederaRepository extends JpaRepository<Corredera, Long> {


}
