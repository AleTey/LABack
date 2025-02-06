package com.backend.la.backendloveafrica.models.entities;

import java.util.List;

import com.backend.la.backendloveafrica.models.entities.security.UserSec;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workshops")
public class WorkShop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String name;

  @OneToOne
  private UserSec user;

  @ManyToMany
  @JoinTable(name = "workshop_model", joinColumns = { @JoinColumn(name = "workshop_id") }, inverseJoinColumns = {
      @JoinColumn(name = "model_id") })
  private List<Model> modelosDelTaller;

}
