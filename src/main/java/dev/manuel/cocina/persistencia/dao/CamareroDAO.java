package dev.manuel.cocina.persistencia.dao;

import dev.manuel.cocina.persistencia.dao.crud.CamareroCRUD;
import dev.manuel.cocina.persistencia.dto.InformeCamareroDTO;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaException;

import java.sql.Connection;
import java.util.List;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class CamareroDAO extends CamareroCRUD {

  public CamareroDAO(Connection conn, AuditoriaDTO auditoria) throws PersistenciaException {
    super(conn, auditoria);
  }

  /**
   * Consulta el informe de los camareros y sus respectivas ventas por mes
   *
   * @return Lista de Dto que representa el informe de ventas de camareros
   * @throws PersistenciaException
   */
  public List<InformeCamareroDTO> consultarInforme() throws PersistenciaException {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT DISTINCT c.*, EXTRACT(MONTH FROM f.fecha)::CHARACTER VARYING fecha, sum(df.importe) total ")
      .append("FROM public.camarero c ")
      .append("INNER JOIN public.factura f ON c.id_camarero = f.id_camarero ")
      .append("INNER JOIN public.detalle_factura df ON f.id_factura = df.id_factura ")
      .append("GROUP BY c.id_camarero, f.fecha ")
      .append("ORDER BY fecha asc;");
    return ejecutarConsulta(sql, parametros, (rs, columns) -> {
      InformeCamareroDTO informe = new InformeCamareroDTO();
      informe.setCamarero(getCamarero(rs));
      informe.setFecha(getObject("fecha", String.class, rs));
      informe.setTotal(getObject("total",Double.class,rs));
      return informe;
    });
  }
}
