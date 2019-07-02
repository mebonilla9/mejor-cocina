
package dev.manuel.cocina.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.estandar.persistencia.abstracto.Entidad;

import java.io.Serializable;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleFactura extends Entidad implements Serializable {

  private Integer idDetalleFactura;
  private String plato;
  private Double importe;
  private Cocinero idCocinero;
  private Factura idFactura;


  public DetalleFactura() {
  }

  // <editor-fold defaultstate="collapsed" desc="GET-SET">
  public Integer getIdDetalleFactura() {
    return idDetalleFactura;
  }

  public DetalleFactura setIdDetalleFactura(Integer idDetalleFactura) {
    this.idDetalleFactura = idDetalleFactura;
    return this;
  }

  public String getPlato() {
    return plato;
  }

  public DetalleFactura setPlato(String plato) {
    this.plato = plato;
    return this;
  }

  public Double getImporte() {
    return importe;
  }

  public DetalleFactura setImporte(Double importe) {
    this.importe = importe;
    return this;
  }

  public Cocinero getIdCocinero() {
    return idCocinero;
  }

  public DetalleFactura setIdCocinero(Cocinero idCocinero) {
    this.idCocinero = idCocinero;
    return this;
  }

  public Factura getIdFactura() {
    return idFactura;
  }

  public DetalleFactura setIdFactura(Factura idFactura) {
    this.idFactura = idFactura;
    return this;
  }

  // </editor-fold>

  @Override
  public DetalleFactura validar() throws AplicacionException {
    return this;
  }
}

