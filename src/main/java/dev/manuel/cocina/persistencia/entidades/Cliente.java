
package dev.manuel.cocina.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.persistencia.abstracto.Entidad;

import java.io.Serializable;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente extends Entidad implements Serializable {

  private Integer idCliente;
  private String nombre;
  private String primerApellido;
  private String segundoApellido;
  private String observaciones;


  public Cliente() {
  }

  // <editor-fold defaultstate="collapsed" desc="GET-SET">
  public Integer getIdCliente() {
    return idCliente;
  }

  public Cliente setIdCliente(Integer idCliente) {
    this.idCliente = idCliente;
    return this;
  }

  public String getNombre() {
    return nombre;
  }

  public Cliente setNombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public String getPrimerApellido() {
    return primerApellido;
  }

  public Cliente setPrimerApellido(String primerApellido) {
    this.primerApellido = primerApellido;
    return this;
  }

  public String getSegundoApellido() {
    return segundoApellido;
  }

  public Cliente setSegundoApellido(String segundoApellido) {
    this.segundoApellido = segundoApellido;
    return this;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public Cliente setObservaciones(String observaciones) {
    this.observaciones = observaciones;
    return this;
  }

  // </editor-fold>

  @Override
  public Cliente validar() throws AplicacionExcepcion {
    return this;
  }
}

