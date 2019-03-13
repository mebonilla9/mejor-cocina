/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.util;

import dev.manuel.estandar.constante.EMensajeEstandar;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;

import java.util.List;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class ValidacionObjeto {

  /**
   * Valida si la colección está vacía si es así lanza un error diciendo que la
   * lista es obligatoria
   *
   * @param lista
   * @param campo
   * @throws AplicacionExcepcion
   */
  public static void listaObligatoria(List lista, String campo)
          throws AplicacionExcepcion
  {
    if (lista == null || lista.isEmpty()) {
      throw new AplicacionExcepcion(EMensajeEstandar.ERROR_VALIDACION_MENSAJE, campo);
    }
  }
}
