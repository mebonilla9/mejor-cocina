package dev.manuel.cocina.persistencia.dao;

import dev.manuel.cocina.persistencia.dao.crud.DetalleFacturaCRUD;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaException;

import java.sql.Connection;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class DetalleFacturaDAO extends DetalleFacturaCRUD {

  public DetalleFacturaDAO(Connection conn, AuditoriaDTO auditoria) throws PersistenciaException {
    super(conn, auditoria);
  }

  /**
   * Permite eliminar todos los detalles de factura de una factura especifica
   *
   * @param idFactura identificador de la factura a la que se le eliminaran los detalles
   * @throws PersistenciaException Error al ejecutar la eliminación
   */
  public void eliminar(Integer idFactura) throws PersistenciaException {
    StringBuilder sql = new StringBuilder();
    sql.append("DELETE FROM public.detalle_factura ")
      .append("WHERE id_factura = :idFactura;");
    parametros.put("idFactura", idFactura);
    ejecutarEdicion(sql, parametros);
  }
}
