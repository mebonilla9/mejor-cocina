package dev.manuel.cocina.aplicacion.configuracion;

import dev.manuel.estandar.dto.RespuestaDTO;
import dev.manuel.estandar.excepcion.AplicacionException;
import org.apache.http.HttpStatus;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class AplicacionExcepcionHandler implements ExceptionMapper<AplicacionException> {

  /**
   * Proveedor de JAX-RS que mapea y retorna una excepción de tipo AplicacionException
   *
   * @param exception Excepción que es tratada por el proveedor de mapeo de excepciones
   * @return Respuesta del servidor en formato JSON
   */
  @Override
  public Response toResponse(AplicacionException exception) {
    RespuestaDTO respuestaError = new RespuestaDTO();
    respuestaError.setCodigo(exception.getCodigo());
    respuestaError.setMensaje(exception.getMensaje());
    return Response.status(HttpStatus.SC_BAD_REQUEST).entity(respuestaError).type(MediaType.APPLICATION_JSON_TYPE).build();
  }
}
