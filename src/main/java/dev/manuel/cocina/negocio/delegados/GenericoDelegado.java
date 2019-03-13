/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.cocina.negocio.delegados;

import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.util.FuncionesDatoUtil;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Se crean los delegados cuando la lógica de un serivicio es demasidao extensa
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public abstract class GenericoDelegado extends FuncionesDatoUtil {

  protected Connection conn;
  protected AuditoriaDTO auditoria;

  /**
   * Constructor de la clase
   *
   * @param conn conexión a la base de datos
   * @param auditoria Información del usuario logeado
   */
  public GenericoDelegado(Connection conn, AuditoriaDTO auditoria)
  {
    this.conn = conn;
    this.auditoria = auditoria;
  }

}
