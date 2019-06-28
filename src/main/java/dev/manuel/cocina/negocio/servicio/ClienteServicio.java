package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.persistencia.dao.ClienteDAO;
import dev.manuel.cocina.persistencia.dto.InformeClienteDTO;
import dev.manuel.cocina.persistencia.entidades.Cliente;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

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
public class ClienteServicio extends GenericoServicio {

  /**
   * Consulta la lista de clientes existentes en la aplicación
   *
   * @return Lista de clientes
   * @throws AplicacionExcepcion Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<Cliente> consultarTodos() throws AplicacionExcepcion {
    List<Cliente> listaClientes = new ClienteDAO(getConexion(), null).consultar();
    return listaClientes;
  }

  /**
   * Consulta el informe de gastos de los clientes
   *
   * @return Lista de Dtos que representa el informe de gastos por cliente
   * @throws PersistenciaExcepcion Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<InformeClienteDTO> consultarGastos() throws PersistenciaExcepcion {
    List<InformeClienteDTO> informeCliente = new ClienteDAO(getConexion(), null).consultarGastoClientes();
    return informeCliente;
  }

}
