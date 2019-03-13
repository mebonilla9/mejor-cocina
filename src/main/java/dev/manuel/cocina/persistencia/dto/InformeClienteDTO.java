package dev.manuel.cocina.persistencia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.cocina.persistencia.entidades.Cliente;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformeClienteDTO {

  private Cliente cliente;
  private Double gasto;


  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Double getGasto() {
    return gasto;
  }

  public void setGasto(Double gasto) {
    this.gasto = gasto;
  }
}
