package com.backend.la.backendloveafrica.models.entities;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// @Table(name = "telas")
public class Fabric {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nombre_tela")
  private String nombre;
  private String codigo;
  private String color;
  private String tipo;
  private String temporada;
  private Integer stock;
  private Double precio;
  private Integer codeBarNumb;
  private String tags;
  @Lob
  @Column(name = "img", columnDefinition = "LONGBLOB")
  private byte[] img;

  @ManyToOne
  // @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
  private Supplier proveedor;

  private String urlFile;

  public Fabric() {
  }

  public Fabric(Long id) {
    this.id = id;
  }

  public Fabric(Long id, String nombre, String codigo, String color, String tipo, String temporada, Integer stock,
      Double precio, Integer codeBarNumb, String tags, byte[] img, Supplier proveedor) {
    this.id = id;
    this.nombre = nombre;
    this.codigo = codigo;
    this.color = color;
    this.tipo = tipo;
    this.temporada = temporada;
    this.stock = stock;
    this.precio = precio;
    this.codeBarNumb = codeBarNumb;
    this.tags = tags;
    this.img = img;
    this.proveedor = proveedor;
  }

  public Fabric(Long id, String nombre, String codigo, String color, String tipo, String temporada, Integer stock,
      Double precio, String tags, Supplier proveedor) {
    this.id = id;
    this.nombre = nombre;
    this.codigo = codigo;
    this.color = color;
    this.tipo = tipo;
    this.temporada = temporada;
    this.stock = stock;
    this.precio = precio;
    this.tags = tags;
    this.proveedor = proveedor;
  }

  public Fabric(Long id, String nombre, String codigo, String color, String tipo, String temporada, Integer stock,
      Double precio, String tags) {
    this.id = id;
    this.nombre = nombre;
    this.codigo = codigo;
    this.color = color;
    this.tipo = tipo;
    this.temporada = temporada;
    this.stock = stock;
    this.precio = precio;
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "Fabric [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", color=" + color + ", tipo=" + tipo
        + ", temporada=" + temporada + ", stock=" + stock + ", precio=" + precio + ", codeBarNumb=" + codeBarNumb
        + ", tags=" + tags + ", img=" + Arrays.toString(img) + ", proveedor=" + proveedor + "]";
  }

}
