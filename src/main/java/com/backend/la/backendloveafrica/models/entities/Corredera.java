package com.backend.la.backendloveafrica.models.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Corredera extends Input {

  // private String forma;
  private String medida;
  private String material;
  private String color;
  // private Integer cantPorPack;
  // private Double precioPorPack;
  private Double precioUni;
  private Integer stock;

  // public Corredera() {
  // }

  // public Corredera(Long id, String nombre, String codigo, Supplier proveedor, String detalle) {
  //   super(id, nombre, codigo, proveedor, detalle);
  // }

  // public Corredera(String nombre, String codigo, Supplier proveedor, String detalle, String forma, String material,
  //     String medida, String color, Integer cantPorPack, Double precioPorPack, Integer stockPacks) {
  //   super(nombre, codigo, proveedor, detalle);
  //   this.forma = forma;
  //   this.material = material;
  //   this.medida = medida;
  //   this.color = color;
  //   this.cantPorPack = cantPorPack;
  //   this.precioPorPack = precioPorPack;
  //   this.stockPacks = stockPacks;
  // }

  // public Corredera(Long id, String nombre, String codigo, Supplier proveedor, String detalle, String forma, String material,
  //     String medida, String color, Integer cantPorPack, Double precioPorPack, Integer stockPacks) {
  //   super(id, nombre, codigo, proveedor, detalle);
  //   this.forma = forma;
  //   this.material = material;
  //   this.medida = medida;
  //   this.color = color;
  //   this.cantPorPack = cantPorPack;
  //   this.precioPorPack = precioPorPack;
  //   this.stockPacks = stockPacks;
  // }

  // public Corredera(String forma, String medida, String material, String color, Integer cantPorPack,
  //     Double precioPorPack, Double precioUni, Integer stockPacks) {
  //   this.forma = forma;
  //   this.medida = medida;
  //   this.material = material;
  //   this.color = color;
  //   this.cantPorPack = cantPorPack;
  //   this.precioPorPack = precioPorPack;
  //   this.precioUni = precioUni;
  //   this.stockPacks = stockPacks;
  // }

  // public Corredera(String nombre, String codigo, Supplier proveedor, String detalle, String forma, String medida, String material,
  //     String color, Integer cantPorPack, Double precioPorPack, Double precioUni, Integer stockPacks) {
  //   super(nombre, codigo, proveedor, detalle);
  //   this.forma = forma;
  //   this.medida = medida;
  //   this.material = material;
  //   this.color = color;
  //   this.cantPorPack = cantPorPack;
  //   this.precioPorPack = precioPorPack;
  //   this.precioUni = precioUni;
  //   this.stockPacks = stockPacks;
  // }

  // public Corredera(Long id, String nombre, String codigo, Supplier proveedor, String detalle, String forma, String medida,
  //     String material, String color, Integer cantPorPack, Double precioPorPack, Double precioUni, Integer stockPacks) {
  //   super(id, nombre, codigo, proveedor, detalle);
  //   this.forma = forma;
  //   this.medida = medida;
  //   this.material = material;
  //   this.color = color;
  //   this.cantPorPack = cantPorPack;
  //   this.precioPorPack = precioPorPack;
  //   this.precioUni = precioUni;
  //   this.stockPacks = stockPacks;
  // }

  // public void setPrecioUni() {
  //   this.precioUni = this.precioPorPack / this.cantPorPack;
  // }

}
