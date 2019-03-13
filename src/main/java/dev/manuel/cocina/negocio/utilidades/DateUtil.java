
package dev.manuel.cocina.negocio.utilidades;

import java.sql.Timestamp;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class DateUtil {

  public static java.sql.Date parseDate(java.util.Date fecha) {
    if (fecha == null) {
      return null;
    }
    return new java.sql.Date(fecha.getTime());
  }

  public static Timestamp parseTimestamp(java.util.Date fecha) {
    return new Timestamp(fecha.getTime());
  }
}
