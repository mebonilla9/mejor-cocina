package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.persistencia.basedatos.PostgresBD;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class GenericoServicio {

  protected static final int NO_REGISTROS = 0;

  /**
   * Representa el recurso del pool de conexiones del servidor de aplicaciones
   */
  @Resource(name = "CocinaDS", lookup = "java:/CocinaDS")
  protected DataSource dataSource;

  public Connection conn;

  @PostConstruct
  public void onCreate() throws PersistenciaExcepcion, SQLException {
    this.conn = PostgresBD.getConexion(dataSource);
  }

}
