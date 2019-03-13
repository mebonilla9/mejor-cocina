
package dev.manuel.cocina.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.persistencia.abstracto.Entidad;

import java.io.Serializable;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Camarero extends Entidad implements Serializable {

  private Integer idCamarero;
  private String nombre;
  private String primerApellido;
  private String segundoApellido;


  public Camarero() {
  }

  // <editor-fold defaultstate="collapsed" desc="GET-SET">
  public Integer getIdCamarero() {
    return idCamarero;
  }

  public Camarero setIdCamarero(Integer idCamarero) {
    this.idCamarero = idCamarero;
    return this;
  }

  public String getNombre() {
    return nombre;
  }

  public Camarero setNombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public String getPrimerApellido() {
    return primerApellido;
  }

  public Camarero setPrimerApellido(String primerApellido) {
    this.primerApellido = primerApellido;
    return this;
  }

  public String getSegundoApellido() {
    return segundoApellido;
  }

  public Camarero setSegundoApellido(String segundoApellido) {
    this.segundoApellido = segundoApellido;
    return this;
  }

  // </editor-fold>

  @Override
  public Camarero validar() throws AplicacionExcepcion {
    return this;
  }
}

