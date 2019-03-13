/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.persistencia.abstracto;

import dev.manuel.estandar.constante.EMensajeEstandar;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @param <T> Entidad que se quiere manipular
 * @author Manuel Ernesto Bonilla Muñoz
 */
@FunctionalInterface
public interface ConsultaAdaptador<T> {

  public abstract T siguiente(ResultSet rs, Map<String, Integer> columns)
    throws PersistenciaExcepcion;

  public default void sinResultados()
    throws PersistenciaExcepcion {
    //throw new PersistenciaExcepcion(EMensajePersistencia.NO_RESULTADOS);
  }

  public default void error(SQLException ex)
    throws PersistenciaExcepcion {
    throw new PersistenciaExcepcion(EMensajeEstandar.ERROR_CONSULTAR);
  }

}
