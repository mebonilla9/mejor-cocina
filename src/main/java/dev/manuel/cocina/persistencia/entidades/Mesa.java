
package dev.manuel.cocina.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.estandar.persistencia.abstracto.Entidad;

import java.io.Serializable;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mesa extends Entidad implements Serializable {

  private Integer idMesa;
  private Integer maxComensal;
  private String ubicacion;


  public Mesa() {
  }

  // <editor-fold defaultstate="collapsed" desc="GET-SET">
  public Integer getIdMesa() {
    return idMesa;
  }

  public Mesa setIdMesa(Integer idMesa) {
    this.idMesa = idMesa;
    return this;
  }

  public Integer getMaxComensal() {
    return maxComensal;
  }

  public Mesa setMaxComensal(Integer maxComensal) {
    this.maxComensal = maxComensal;
    return this;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public Mesa setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
    return this;
  }

  // </editor-fold>

  @Override
  public Mesa validar() throws AplicacionException {
    return this;
  }
}

