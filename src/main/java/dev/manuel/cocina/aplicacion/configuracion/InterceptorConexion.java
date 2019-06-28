package dev.manuel.cocina.aplicacion.configuracion;

import dev.manuel.cocina.persistencia.basedatos.PostgresBD;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class InterceptorConexion {

  /**
   * Representa el recurso del pool de conexiones del servidor de aplicaciones
   */
  @Resource(name = "CocinaDS", lookup = "java:/CocinaDS")
  protected DataSource dataSource;

  @AroundInvoke
  public Object interceptorConexion(InvocationContext contexto) throws Exception {
    contexto.getContextData().put("conexion", dataSource.getConnection());
    Object resultado = contexto.proceed();
    PostgresBD.desconectar((Connection) contexto.getContextData().get("conexion"));
    return resultado;
  }


}
