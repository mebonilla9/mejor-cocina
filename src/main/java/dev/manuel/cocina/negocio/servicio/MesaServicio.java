package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.persistencia.basedatos.PostgresBD;
import dev.manuel.cocina.persistencia.dao.MesaDAO;
import dev.manuel.cocina.persistencia.entidades.Mesa;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MesaServicio extends GenericoServicio {

  /**
   * Consulta las mesas del restaurante registradas en la aplicación
   *
   * @return Lista de mesas del restaurante
   * @throws PersistenciaExcepcion Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<Mesa> consultarTodos() throws PersistenciaExcepcion {
    //this.conn = PostgresBD.getConexion(dataSource);
    List<Mesa> listaMesas = new MesaDAO(getConexion(), null).consultar();
    /*try {
      System.out.println("------ y el autocommit = " + this.conn.getAutoCommit());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    PostgresBD.desconectar(conn);*/
    return listaMesas;
  }
}
