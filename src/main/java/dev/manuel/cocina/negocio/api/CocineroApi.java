package dev.manuel.cocina.negocio.api;

import dev.manuel.cocina.negocio.constante.ERutas;
import dev.manuel.cocina.negocio.servicio.CocineroServicio;
import dev.manuel.cocina.persistencia.entidades.Cocinero;
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
public class CocineroApi {

  @Context
  private UriInfo context;

  /**
   * Objeto que contiene la lógica del negocio con relación a los Cocineros
   */
  @EJB
  private CocineroServicio cocineroServicio;

  /**
   * Consulta la información de todos los cocineros que estan registrados en la aplicación
   * @return Lista de cocineros registrados en la aplicación
   * @throws AplicacionExcepcion Error al consultar la información
   */
  @GET
  @Path(ERutas.Cocinero.CONSULTAR)
  @Produces(MediaType.APPLICATION_JSON)
  public RespuestaDTO consultarTodos() throws AplicacionExcepcion {
    List<Cocinero> listaCocineros = cocineroServicio.consultarTodos();
    return new RespuestaDTO().setDatos(listaCocineros);
  }
}
