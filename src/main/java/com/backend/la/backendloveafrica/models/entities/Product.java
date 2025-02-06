package com.backend.la.backendloveafrica.models.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  private String codeBarNumber;

  private String colorForro;

  private Double cost;

  @Lob
  @Column(name = "img", columnDefinition = "LONGBLOB")
  private byte[] img;

  private LocalDate fechaRegistro;

  @ManyToOne(fetch = FetchType.EAGER)
  private Fabric fabric;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private ModelAndStripsForProduct modelAndStripsForProduct;

  private String urlFile;

  
  public Product(Long id) {
    this.id = id;
  }

  public Product(String nombre, String colorForro, byte[] img, Fabric fabric,
      ModelAndStripsForProduct modelAndStripsForProduct) {
    this.nombre = nombre;
    this.colorForro = colorForro;
    this.img = img;
    this.fabric = fabric;
    this.modelAndStripsForProduct = modelAndStripsForProduct;
  }

  @PrePersist
  private void setFechaRegistro() {
    this.fechaRegistro = LocalDate.now();
  }

}
