package com.backend.la.backendloveafrica.models.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "collections")
public class Collection {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String year;

  @ManyToMany
  @JoinTable(name = "collections_models", joinColumns = @JoinColumn(name = "Collections"), inverseJoinColumns = @JoinColumn(name = "models"))
  private Set<Model> models;

  @ManyToMany
  @JoinTable(name = "collections_products_set", joinColumns = @JoinColumn(name = "collections"), inverseJoinColumns = @JoinColumn(name = "products_set"))
  private Set<ProductSet> productsSet;

  @ManyToMany
  @JoinTable(name = "collections_products", joinColumns = @JoinColumn(name = "collections"), inverseJoinColumns = @JoinColumn(name = "products"))
  private Set<Product> products;

}
