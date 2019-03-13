/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.cocina.persistencia.basedatos;

import dev.manuel.cocina.persistencia.constante.EMensajePersistencia;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.persistencia.abstracto.GenericoConexion;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;
import dev.manuel.estandar.util.LogUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class PostgresBD extends GenericoConexion {

  public static Connection conectar()
    throws AplicacionExcepcion {
    try {
      Context context = new InitialContext();
      DataSource ds = (DataSource) context.lookup("java:/CocinaDS");
      Connection cnn = ds.getConnection();
      //cnn.setAutoCommit(false);
      return cnn;
    } catch (Exception ex) {
      LogUtil.error(ex);
      throw new AplicacionExcepcion(EMensajePersistencia.ERROR_CONEXION_BD);
    }
  }

  public static Connection getConexion(DataSource datasource)
    throws PersistenciaExcepcion {
    try {
      return datasource.getConnection();
    } catch (SQLException ex) {
      LogUtil.error(ex);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_CONEXION_BD);
    }
  }

  public static void commit(DataSource datasource)
    throws PersistenciaExcepcion {
    try {
      getConexion(datasource).commit();
    } catch (SQLException ex) {
      LogUtil.error(ex);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_CONFIRMAR);
    }
  }

  public static void rollback(DataSource datasource)
    throws PersistenciaExcepcion {
    try {
      getConexion(datasource).rollback();
    } catch (SQLException ex) {
      LogUtil.error(ex);
    }
  }

}
