package dev.manuel.cocina.negocio.api;

import dev.manuel.cocina.negocio.constante.ERutas;
import dev.manuel.cocina.negocio.servicio.CamareroServicio;
import dev.manuel.cocina.persistencia.dto.InformeCamareroDTO;
import dev.manuel.cocina.persistencia.entidades.Camarero;
import dev.manuel.estandar.dto.RespuestaDTO;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;

import javax.annotation.PreDestroy;
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
public class CamareroApi {

  @Context
  private UriInfo context;

  /**
   * Objeto que contiene la lógica del negocio con relación a los camareros
   */
  @EJB
  private CamareroServicio camareroServicio;

  /**
   * Consulta la información de todos los camareros que estan registrados en la aplicación
   *
   * @return Lista de camareros registrados en la aplicación
   * @throws AplicacionExcepcion Error al consultar la información
   */
  @GET
  @Path(ERutas.Camarero.CONSULTAR)
  @Produces(MediaType.APPLICATION_JSON)
  public RespuestaDTO consultar() throws AplicacionExcepcion {
    List<Camarero> listaCamareros = camareroServicio.consultarTodos();
    return new RespuestaDTO().setDatos(listaCamareros);
  }

  /**
   * Consulta los datos pertenecientes al informe de ventas de los camareros del restaurante
   *
   * @return Lista de Dto que representa el informe de los camareros
   * @throws AplicacionExcepcion Error al consultar la información
   */
  @GET
  @Path(ERutas.Camarero.CONSULTAR_INFORME)
  @Produces(MediaType.APPLICATION_JSON)
  public RespuestaDTO consultarInforme() throws AplicacionExcepcion {
    List<InformeCamareroDTO> listaInforme = camareroServicio.consultarInforme();
    return new RespuestaDTO().setDatos(listaInforme);
  }
}
