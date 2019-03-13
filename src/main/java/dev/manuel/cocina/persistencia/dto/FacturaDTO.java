package dev.manuel.cocina.persistencia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.cocina.persistencia.entidades.DetalleFactura;
import dev.manuel.cocina.persistencia.entidades.Factura;

import java.util.List;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacturaDTO {

  private Factura factura;
  private List<DetalleFactura> detalles;


  public Factura getFactura() {
    return factura;
  }

  public void setFactura(Factura factura) {
    this.factura = factura;
  }

  public List<DetalleFactura> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<DetalleFactura> detalles) {
    this.detalles = detalles;
  }
}
