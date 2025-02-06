package com.backend.la.backendloveafrica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.la.backendloveafrica.models.entities.Input;

public interface IInputRepository extends JpaRepository<Input, Long> {

}
