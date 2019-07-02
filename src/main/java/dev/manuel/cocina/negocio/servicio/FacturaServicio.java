package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.negocio.delegados.RegistrarFacturaDelegado;
import dev.manuel.cocina.persistencia.dto.FacturaDTO;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaException;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.Transactional;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FacturaServicio extends GenericoServicio {

  /**
   * Servicio que gestiona el registro de una nueva factura de un cliente
   *
   * @param factura Dto del encabezado de factura con la lista de detalles de factura
   * @throws PersistenciaException Error al insertar el encabezado de la facturas
   */
  @Transactional(
    value = Transactional.TxType.REQUIRED,
    rollbackOn = {Throwable.class}
  )
  public void guardarFactura(FacturaDTO factura) throws PersistenciaException {
    new RegistrarFacturaDelegado(getConexion(), null).guardarFactura(factura);
  }

}
