package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.aplicacion.configuracion.InterceptorConexion;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.Interceptors;
import java.sql.Connection;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@Interceptors(InterceptorConexion.class)
public class GenericoServicio {

  @Resource
  protected SessionContext contexto;

  protected static final int NO_REGISTROS = 0;

  protected Connection getConexion(){
    return (Connection) contexto.getContextData().get("conexion");
  }
}
