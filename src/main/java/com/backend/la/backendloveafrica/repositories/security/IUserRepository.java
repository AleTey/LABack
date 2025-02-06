package com.backend.la.backendloveafrica.repositories.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.la.backendloveafrica.models.entities.security.UserSec;

@Repository
public interface IUserRepository extends JpaRepository<UserSec, Long> {

  Optional<UserSec> findUserEntityByUsername(String nombre);
}
