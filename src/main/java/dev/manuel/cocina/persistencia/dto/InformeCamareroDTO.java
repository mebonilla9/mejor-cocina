package dev.manuel.cocina.persistencia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.cocina.persistencia.entidades.Camarero;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformeCamareroDTO {

  private Camarero camarero;
  private String fecha;
  private Double total;


  public Camarero getCamarero() {
    return camarero;
  }

  public void setCamarero(Camarero camarero) {
    this.camarero = camarero;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }
}
