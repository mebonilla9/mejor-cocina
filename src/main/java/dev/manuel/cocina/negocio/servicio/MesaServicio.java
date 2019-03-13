package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.persistencia.basedatos.PostgresBD;
import dev.manuel.cocina.persistencia.dao.MesaDAO;
import dev.manuel.cocina.persistencia.entidades.Mesa;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@Stateless
public class MesaServicio extends GenericoServicio {


  /**
   * Consulta las mesas del restaurante registradas en la aplicación
   *
   * @return Lista de mesas del restaurante
   * @throws PersistenciaExcepcion Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<Mesa> consultarTodos() throws PersistenciaExcepcion {
    List<Mesa> listaMesas = new MesaDAO(this.conn, null).consultar();
    //PostgresBD.desconectar(conn);
    return listaMesas;
  }
}
