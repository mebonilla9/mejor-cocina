package dev.manuel.cocina.persistencia.dao;

import dev.manuel.cocina.persistencia.dao.crud.MesaCRUD;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaException;

import java.sql.Connection;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class MesaDAO extends MesaCRUD {

  public MesaDAO(Connection conn, AuditoriaDTO auditoria) throws PersistenciaException {
     super(conn, auditoria);
  }
}
