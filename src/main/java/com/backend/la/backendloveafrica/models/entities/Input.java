package com.backend.la.backendloveafrica.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Input {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String codigo;
  @ManyToOne
  private Supplier proveedor;
  private String detalle;

  public Input() {
  }

  public Input(String nombre, String codigo, Supplier proveedor, String detalle) {
    this.nombre = nombre;
    this.codigo = codigo;
    this.proveedor = proveedor;
    this.detalle = detalle;
  }

  public Input(Long id,String nombre, String codigo, Supplier proveedor, String detalle) {
    this.id = id;
    this.nombre = nombre;
    this.codigo = codigo;
    this.proveedor = proveedor;
    this.detalle = detalle;
  }

  @Override
  public String toString() {
    return "Input [id=" + id + ", codigo=" + codigo + ", proveedor=" + proveedor + ", detalle=" + detalle + "]";
  }

}
