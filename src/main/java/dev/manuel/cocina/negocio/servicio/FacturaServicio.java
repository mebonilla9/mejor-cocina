package dev.manuel.cocina.negocio.servicio;

import dev.manuel.cocina.negocio.delegados.RegistrarFacturaDelegado;
import dev.manuel.cocina.persistencia.dto.FacturaDTO;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@Stateless
public class FacturaServicio extends GenericoServicio {

  /**
   * Servicio que gestiona el registro de una nueva factura de un cliente
   *
   * @param factura Dto del encabezado de factura con la lista de detalles de factura
   * @throws PersistenciaExcepcion Error al insertar el encabezado de la facturas
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void guardarFactura(FacturaDTO factura) throws PersistenciaExcepcion {
    new RegistrarFacturaDelegado(this.conn, null).guardarFactura(factura);
  }

}
