package com.backend.la.backendloveafrica.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.la.backendloveafrica.models.entities.Collection;

@Repository
public interface ICollectionRepository extends JpaRepository<Collection, Long> {

  Page<Collection> findAll(Pageable pageable);
}
