package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.persistencia.dao.ClienteDAO;
import dev.manuel.cocina.persistencia.dto.InformeClienteDTO;
import dev.manuel.cocina.persistencia.entidades.Cliente;
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
public class ClienteServicio extends GenericoServicio {

  /**
   * Consulta la lista de clientes existentes en la aplicación
   *
   * @return Lista de clientes
   * @throws AplicacionException Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<Cliente> consultarTodos() throws AplicacionException {
    List<Cliente> listaClientes = new ClienteDAO(getConexion(), null).consultar();
    return listaClientes;
  }

  /**
   * Consulta el informe de gastos de los clientes
   *
   * @return Lista de Dtos que representa el informe de gastos por cliente
   * @throws PersistenciaException Error al consultar la información
   */
  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public List<InformeClienteDTO> consultarGastos() throws PersistenciaException {
    List<InformeClienteDTO> informeCliente = new ClienteDAO(getConexion(), null).consultarGastoClientes();
    return informeCliente;
  }

}
