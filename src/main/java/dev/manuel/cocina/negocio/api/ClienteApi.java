package dev.manuel.cocina.negocio.api;

import dev.manuel.cocina.negocio.constante.ERutas;
import dev.manuel.cocina.negocio.servicio.ClienteServicio;
import dev.manuel.cocina.persistencia.dto.InformeClienteDTO;
import dev.manuel.cocina.persistencia.entidades.Cliente;
import dev.manuel.estandar.dto.RespuestaDTO;
import dev.manuel.estandar.excepcion.AplicacionExcepcion;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Inject;
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
public class ClienteApi {

  @Context
  private UriInfo context;

  /**
   * Objeto que contiene la lógica del negocio con relación a los clientes
   */
  @Inject
  private ClienteServicio clienteServicio;

  /**
   * Metodo que permite consultar los clientes registrados en el sistema
   *
   * @return Lista de clientes registrados en la aplicación
   * @throws AplicacionExcepcion Error al consultar la información
   */
  @GET
  @Path(ERutas.Cliente.CONSULTAR)
  @Produces(MediaType.APPLICATION_JSON)
  public RespuestaDTO consultar() throws AplicacionExcepcion {
    List<Cliente> listaClientes = clienteServicio.consultarTodos();
    return new RespuestaDTO().setDatos(listaClientes);
  }

  /**
   * Consulta los datos pertenecientes al informe de gastos de los clientes del restaurante
   * @return Lista de Dto que representa el informe de gastos de los clientes
   * @throws AplicacionExcepcion Error al consultar la información
   */
  @GET
  @Path(ERutas.Cliente.CONSULTAR_GASTOS)
  @Produces(MediaType.APPLICATION_JSON)
  public RespuestaDTO consultarGastos() throws AplicacionExcepcion {
    List<InformeClienteDTO> listaGastos = clienteServicio.consultarGastos();
    return new RespuestaDTO().setDatos(listaGastos);
  }
}
