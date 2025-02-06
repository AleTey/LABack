package com.backend.la.backendloveafrica.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// @Table(name = "proveedores")
public class Supplier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String empresa;
  // @Column(unique = true)
  private String email;
  private String sector;
  private String calle;
  private String nro;
  private String localidad;
  private String nombreContacto;
  private Integer caracteristica;
  private String celContacto;
  private String emailContacto;

  public Supplier() {
  }

  public Supplier(Long id, String empresa, String email, String sector, String calle, String nro, String localidad,
      String nombreContacto, Integer caracteristica, String celContacto, String emailContacto) {
    this.id = id;
    this.empresa = empresa;
    this.email = email;
    this.sector = sector;
    this.calle = calle;
    this.nro = nro;
    this.localidad = localidad;
    this.nombreContacto = nombreContacto;
    this.caracteristica = caracteristica;
    this.celContacto = celContacto;
    this.emailContacto = emailContacto;
  }

}
