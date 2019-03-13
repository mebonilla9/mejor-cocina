package dev.manuel.cocina.negocio.api;


import dev.manuel.cocina.negocio.constante.ERutas;
import dev.manuel.cocina.negocio.servicio.MesaServicio;
import dev.manuel.cocina.persistencia.entidades.Mesa;
import dev.manuel.estandar.dto.RespuestaDTO;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@Path(ERutas.CONTROL_BASE)
public class MesaApi {

  @Context
  private UriInfo context;

  /**
   * Objeto que contiene la lógica del negocio con relación a las mesas
   */
  @EJB
  private MesaServicio mesaServicio;

  /**
   * Consulta la información de todos las mesas que estan registrados en la aplicación
   * @return Lista de mesas registradas en la aplicación
   * @throws AplicacionExcepcion Error al consultar la información
   */
  @GET
  @Path(ERutas.Mesa.CONSULTAR)
  @Produces(MediaType.APPLICATION_JSON)
  public RespuestaDTO consultarTodos() throws AplicacionExcepcion {
    List<Mesa> listaMesas = mesaServicio.consultarTodos();
    return new RespuestaDTO().setDatos(listaMesas);
  }
}
