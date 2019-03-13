/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.persistencia.excepcion;

import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.plantilla.IGenericoMensaje;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class PersistenciaExcepcion extends AplicacionExcepcion {

  public PersistenciaExcepcion(IGenericoMensaje mensaje) {
    super(mensaje);
  }

  public PersistenciaExcepcion(IGenericoMensaje mensaje, Object datos) {
    super(mensaje, datos);
  }

  public PersistenciaExcepcion(IGenericoMensaje eMensaje, String complemento) {
    super(eMensaje);
    mensaje = eMensaje.getMensaje().replaceAll("__COMPLEMENTO__", complemento);
  }

}
