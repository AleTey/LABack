package com.backend.la.backendloveafrica.models.entities;

import java.time.LocalDate;
import java.util.List;

import com.backend.la.backendloveafrica.models.enums.LoteState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "lotes")
public class Lote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "lotes_productos", joinColumns = @JoinColumn(name = "lote"), inverseJoinColumns = @JoinColumn(name = "producto"))
  private List<Product> products;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "cut_spreadsheet_id")
  private CutSpreadSheet cutSpreadSheet;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "preparation_spreadsheet_id")
  private PreparationSpreadSheet preparationSpreadSheet;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "workshop_spreadsheet_id")
  private WorkShopSpreadSheet workShopSpreadSheet;

  @OneToOne(mappedBy = "lote", cascade = CascadeType.ALL, orphanRemoval = true)
  private ControlSpreadSheet controlSpreadSheet;

  @ManyToOne
  @JoinColumn(name = "workshop_id")
  private WorkShop workShop;

  private LocalDate creationDate = LocalDate.now();

  private LocalDate finishedDate;

  @Enumerated(EnumType.STRING)
  private LoteState status;

  private boolean isFinished;

  private String additionalDetails;

  private String collection;

  public ControlSpreadSheet getControlSpreadSheet() {
    return controlSpreadSheet;
  }

  public void setControlSpreadSheet(ControlSpreadSheet controlSpreadSheet) {
    this.controlSpreadSheet = controlSpreadSheet;
    if (controlSpreadSheet != null) {
      controlSpreadSheet.setLote(this);
    }
  }

}
// @OneToMany(mappedBy = "lote", orphanRemoval = true)
// @JsonIgnore
// private List<SpreadSheet> spreadSheets;