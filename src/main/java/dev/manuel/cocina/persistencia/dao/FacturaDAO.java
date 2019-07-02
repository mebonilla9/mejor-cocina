package dev.manuel.cocina.persistencia.dao;

import dev.manuel.cocina.persistencia.dao.crud.FacturaCRUD;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaException;

import java.sql.Connection;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class FacturaDAO extends FacturaCRUD {

  public FacturaDAO(Connection conn, AuditoriaDTO auditoria) throws PersistenciaException {
     super(conn, auditoria);
  }
}
