package dev.manuel.cocina.persistencia.dao;

import dev.manuel.cocina.persistencia.dao.crud.ClienteCRUD;
import dev.manuel.cocina.persistencia.dto.InformeClienteDTO;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class ClienteDAO extends ClienteCRUD {

  public ClienteDAO(Connection conn, AuditoriaDTO auditoria) throws PersistenciaExcepcion {
    super(conn, auditoria);
  }

  /**
   * Consulta el informe de los Clientes, y cual ha sido el total de sus gastos en el restaurante
   *
   * @return Lista de Dto que representa el informe de gastos por cliente
   * @throws PersistenciaExcepcion Error al consultar la información
   */
  public List<InformeClienteDTO> consultarGastoClientes() throws PersistenciaExcepcion {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT DISTINCT c.*, sum(df.importe) gasto ")
      .append("FROM public.cliente c ")
      .append("INNER JOIN public.factura f on c.id_cliente = f.id_cliente ")
      .append("INNER JOIN detalle_factura df on f.id_factura = df.id_factura ")
      .append("group by c.id_cliente ")
      .append("having sum(df.importe) > 100000;");
    return ejecutarConsulta(sql, parametros, (rs, columns) -> {
      InformeClienteDTO informe = new InformeClienteDTO();
      informe.setCliente(getCliente(rs));
      informe.setGasto(getObject("gasto", Double.class, rs));
      return informe;
    });
  }
}
