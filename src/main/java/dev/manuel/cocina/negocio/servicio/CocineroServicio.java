package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.persistencia.dao.CocineroDAO;
import dev.manuel.cocina.persistencia.entidades.Cocinero;
import dev.manuel.estandar.excepcion.AplicacionException;
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
public class CocineroServicio extends GenericoServicio {

  public CocineroServicio() throws AplicacionException {
  }

  /**
   * Consulta la lista de cocineros existentes en la aplicación
   *
   * @return Lista de cocineros
   * @throws PersistenciaException Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<Cocinero> consultarTodos() throws PersistenciaException {
    List<Cocinero> listaCocineros = new CocineroDAO(getConexion(), null).consultar();
    return listaCocineros;
  }
}
