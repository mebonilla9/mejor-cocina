package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.persistencia.basedatos.PostgresBD;
import dev.manuel.cocina.persistencia.dao.CamareroDAO;
import dev.manuel.cocina.persistencia.dto.InformeCamareroDTO;
import dev.manuel.cocina.persistencia.entidades.Camarero;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@Stateless
public class CamareroServicio extends GenericoServicio {


  /**
   * Consulta la lista de camareros existentes en la aplicación
   *
   * @return Lista de camareros
   * @throws PersistenciaExcepcion Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<Camarero> consultarTodos() throws PersistenciaExcepcion {
    List<Camarero> listaCamareros = new CamareroDAO(this.conn, null).consultar();
    //PostgresBD.desconectar(conn);
    return listaCamareros;
  }

  /**
   * Consulta el informe de ventas de los camareros ordenados por mes
   *
   * @return Lista de Dtos que representa el informe de ventas
   * @throws PersistenciaExcepcion Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<InformeCamareroDTO> consultarInforme() throws PersistenciaExcepcion {
    List<InformeCamareroDTO> informeCamareros = new CamareroDAO(this.conn, null).consultarInforme();
    return informeCamareros;
  }
}
