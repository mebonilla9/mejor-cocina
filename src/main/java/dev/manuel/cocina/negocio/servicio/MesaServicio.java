package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.persistencia.dao.MesaDAO;
import dev.manuel.cocina.persistencia.entidades.Mesa;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaException;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.Transactional;
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
   * @throws PersistenciaException Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<Mesa> consultarTodos() throws PersistenciaException {
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
