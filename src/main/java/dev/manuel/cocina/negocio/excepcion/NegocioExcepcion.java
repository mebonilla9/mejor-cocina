/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.cocina.negocio.excepcion;

import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.plantilla.IGenericoMensaje;

/**
 * Clase que controla las excepciones en la capa de lógica
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class NegocioExcepcion extends AplicacionExcepcion {

  public NegocioExcepcion(IGenericoMensaje mensaje) {
    super(mensaje);
  }

  public NegocioExcepcion(IGenericoMensaje mensaje, Object datos) {
    super(mensaje, datos);
  }

  public NegocioExcepcion(IGenericoMensaje eMensaje, String complemento) {
    super(eMensaje);
    mensaje = eMensaje.getMensaje().replaceAll("__COMPLEMENTO__", complemento);
  }

}
