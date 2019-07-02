
package dev.manuel.cocina.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.estandar.persistencia.abstracto.Entidad;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Factura extends Entidad implements Serializable {

  private Integer idFactura;
  private Date fecha;
  private Cliente idCliente;
  private Mesa idMesa;
  private Camarero idCamarero;


  public Factura() {
  }

  // <editor-fold defaultstate="collapsed" desc="GET-SET">
  public Integer getIdFactura() {
    return idFactura;
  }

  public Factura setIdFactura(Integer idFactura) {
    this.idFactura = idFactura;
    return this;
  }

  public Date getFecha() {
    return fecha;
  }

  public Factura setFecha(Date fecha) {
    this.fecha = fecha;
    return this;
  }

  public Cliente getIdCliente() {
    return idCliente;
  }

  public Factura setIdCliente(Cliente idCliente) {
    this.idCliente = idCliente;
    return this;
  }

  public Mesa getIdMesa() {
    return idMesa;
  }

  public Factura setIdMesa(Mesa idMesa) {
    this.idMesa = idMesa;
    return this;
  }

  public Camarero getIdCamarero() {
    return idCamarero;
  }

  public Factura setIdCamarero(Camarero idCamarero) {
    this.idCamarero = idCamarero;
    return this;
  }

  // </editor-fold>

  @Override
  public Factura validar() throws AplicacionException {
    return this;
  }
}

