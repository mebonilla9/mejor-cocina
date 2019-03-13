
package dev.manuel.cocina.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.persistencia.abstracto.Entidad;

import java.io.Serializable;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cocinero extends Entidad implements Serializable {

  private Integer idCocinero;
  private String nombre;
  private String primerApellido;
  private String segundoApellido;


  public Cocinero() {
  }

  // <editor-fold defaultstate="collapsed" desc="GET-SET">
  public Integer getIdCocinero() {
    return idCocinero;
  }

  public Cocinero setIdCocinero(Integer idCocinero) {
    this.idCocinero = idCocinero;
    return this;
  }

  public String getNombre() {
    return nombre;
  }

  public Cocinero setNombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public String getPrimerApellido() {
    return primerApellido;
  }

  public Cocinero setPrimerApellido(String primerApellido) {
    this.primerApellido = primerApellido;
    return this;
  }

  public String getSegundoApellido() {
    return segundoApellido;
  }

  public Cocinero setSegundoApellido(String segundoApellido) {
    this.segundoApellido = segundoApellido;
    return this;
  }

  // </editor-fold>

  @Override
  public Cocinero validar() throws AplicacionExcepcion {
    return this;
  }
}

