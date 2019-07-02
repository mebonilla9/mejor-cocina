package dev.manuel.cocina.negocio.api;

import dev.manuel.cocina.negocio.constante.ERutas;
import dev.manuel.cocina.negocio.servicio.FacturaServicio;
import dev.manuel.cocina.persistencia.dto.FacturaDTO;
import dev.manuel.estandar.dto.RespuestaDTO;
import dev.manuel.estandar.excepcion.AplicacionException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@Path(ERutas.CONTROL_BASE)
public class FacturaApi {

  @Context
  private UriInfo context;

  /**
   * Objeto que contiene la lógica del negocio con relación a las facturas
   */
  @EJB
  private FacturaServicio facturaServicio;

  /**
   * Registra la transacción de una factura, la transaccion puede regisrar no solo la factura, sino tambien el cliente
   * el camarero y los diferentes platos
   *
   * @param factura Dto que representa la factura a registrar
   * @return Respuesta del resultado de la transacción
   * @throws AplicacionException Error al registrar uno de los pasos de la transaccion
   */
  @POST
  @Path(ERutas.Factura.GUARDAR)
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public RespuestaDTO RespuestaDTO(FacturaDTO factura) throws AplicacionException {
    facturaServicio.guardarFactura(factura);
    return new RespuestaDTO();
  }

}
