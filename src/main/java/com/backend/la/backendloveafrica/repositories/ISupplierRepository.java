package com.backend.la.backendloveafrica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.la.backendloveafrica.models.entities.Supplier;

public interface ISupplierRepository extends JpaRepository<Supplier, Long> {

}
