package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.persistencia.basedatos.PostgresBD;
import dev.manuel.cocina.persistencia.dao.CocineroDAO;
import dev.manuel.cocina.persistencia.entidades.Cocinero;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttributeType;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@Stateless
public class CocineroServicio extends GenericoServicio {

  public CocineroServicio() throws AplicacionExcepcion {
  }

  /**
   * Consulta la lista de cocineros existentes en la aplicación
   *
   * @return Lista de cocineros
   * @throws PersistenciaExcepcion Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<Cocinero> consultarTodos() throws PersistenciaExcepcion {
    List<Cocinero> listaCocineros = new CocineroDAO(this.conn, null).consultar();
    //PostgresBD.desconectar(conn);
    return listaCocineros;
  }
}
