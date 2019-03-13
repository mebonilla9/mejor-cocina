package dev.manuel.cocina.persistencia.dao.crud;

import dev.manuel.cocina.persistencia.basedatos.PostgresBD;
import dev.manuel.cocina.persistencia.constante.EMensajePersistencia;
import dev.manuel.cocina.persistencia.entidades.Cocinero;
import dev.manuel.cocina.persistencia.entidades.DetalleFactura;
import dev.manuel.cocina.persistencia.entidades.Factura;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.abstracto.GenericoCRUD;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;
import dev.manuel.estandar.util.LogUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class DetalleFacturaCRUD extends GenericoCRUD {

  private final int ID = 1;

  public DetalleFacturaCRUD(Connection conn, AuditoriaDTO auditoria) throws PersistenciaExcepcion {
    super(conn, auditoria);
  }


  public void insertar(DetalleFactura detalleFactura) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.detalle_factura(plato,importe,id_cocinero,id_factura) VALUES (?,?,?,?)";
      sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      sentencia.setObject(i++, detalleFactura.getPlato());
      sentencia.setObject(i++, detalleFactura.getImporte());
      Object idCocinero = (detalleFactura.getIdCocinero() == null) ? null : detalleFactura.getIdCocinero().getIdCocinero();
      sentencia.setObject(i++, idCocinero);
      Object idFactura = (detalleFactura.getIdFactura() == null) ? null : detalleFactura.getIdFactura().getIdFactura();
      sentencia.setObject(i++, idFactura);

      sentencia.executeUpdate();
      ResultSet rs = sentencia.getGeneratedKeys();
      if (rs.next()) {
        detalleFactura.setIdDetalleFactura(rs.getInt("id_detalle_factura"));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void insertarTodos(DetalleFactura detalleFactura) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.detalle_factura(id_detalle_factura,plato,importe,id_cocinero,id_factura) VALUES (?,?,?,?,?)";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, detalleFactura.getIdDetalleFactura());
      sentencia.setObject(i++, detalleFactura.getPlato());
      sentencia.setObject(i++, detalleFactura.getImporte());
      Object idCocinero = (detalleFactura.getIdCocinero() == null) ? null : detalleFactura.getIdCocinero().getIdCocinero();
      sentencia.setObject(i++, idCocinero);
      Object idFactura = (detalleFactura.getIdFactura() == null) ? null : detalleFactura.getIdFactura().getIdFactura();
      sentencia.setObject(i++, idFactura);

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void editar(DetalleFactura detalleFactura) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "UPDATE public.detalle_factura SET plato=?,importe=?,id_cocinero=?,id_factura=? where id_detalle_factura=? ";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, detalleFactura.getPlato());
      sentencia.setObject(i++, detalleFactura.getImporte());
      Object idCocinero = (detalleFactura.getIdCocinero() == null) ? null : detalleFactura.getIdCocinero().getIdCocinero();
      sentencia.setObject(i++, idCocinero);
      Object idFactura = (detalleFactura.getIdFactura() == null) ? null : detalleFactura.getIdFactura().getIdFactura();
      sentencia.setObject(i++, idFactura);
      sentencia.setObject(i++, detalleFactura.getIdDetalleFactura());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_EDITAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public List<DetalleFactura> consultar() throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    List<DetalleFactura> lista = new ArrayList<>();
    try {

      String sql = "SELECT * FROM public.detalle_factura";
      sentencia = cnn.prepareStatement(sql);
      ResultSet rs = sentencia.executeQuery();
      while (rs.next()) {
        lista.add(getDetalleFactura(rs));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return lista;

  }


  public DetalleFactura consultar(long id) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    DetalleFactura obj = null;
    try {

      String sql = "SELECT * FROM public.detalle_factura WHERE id_detalle_factura=?";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setLong(1, id);
      ResultSet rs = sentencia.executeQuery();
      if (rs.next()) {
        obj = getDetalleFactura(rs);
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return obj;
  }

  public static DetalleFactura getDetalleFactura(ResultSet rs) throws PersistenciaExcepcion {
    DetalleFactura detalleFactura = new DetalleFactura();
    detalleFactura.setIdDetalleFactura(getObject("id_detalle_factura", Integer.class, rs));
    detalleFactura.setPlato(getObject("plato", String.class, rs));
    detalleFactura.setImporte(getObject("importe", Double.class, rs));
    Cocinero cocinero = new Cocinero();
    cocinero.setIdCocinero(getObject("id_cocinero", Integer.class, rs));
    detalleFactura.setIdCocinero(cocinero);
    Factura factura = new Factura();
    factura.setIdFactura(getObject("id_factura", Integer.class, rs));
    detalleFactura.setIdFactura(factura);

    return detalleFactura;
  }

  public static void getDetalleFactura(ResultSet rs, Map<String, Integer> columnas, DetalleFactura detalleFactura) throws PersistenciaExcepcion {
    Integer columna = columnas.get("detalle_factura_id_detalle_factura");
    if (columna != null) {
      detalleFactura.setIdDetalleFactura(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("detalle_factura_plato");
    if (columna != null) {
      detalleFactura.setPlato(getObject(columna, String.class, rs));
    }
    columna = columnas.get("detalle_factura_importe");
    if (columna != null) {
      detalleFactura.setImporte(getObject(columna, Double.class, rs));
    }
    columna = columnas.get("detalle_factura_id_cocinero");
    if (columna != null) {
      Cocinero cocinero = new Cocinero();
      cocinero.setIdCocinero(getObject(columna, Integer.class, rs));
      detalleFactura.setIdCocinero(cocinero);
    }
    columna = columnas.get("detalle_factura_id_factura");
    if (columna != null) {
      Factura factura = new Factura();
      factura.setIdFactura(getObject(columna, Integer.class, rs));
      detalleFactura.setIdFactura(factura);
    }
  }

  public static void getDetalleFactura(ResultSet rs, Map<String, Integer> columnas, DetalleFactura detalleFactura, String alias) throws PersistenciaExcepcion {
    Integer columna = columnas.get(alias + "_id_detalle_factura");
    if (columna != null) {
      detalleFactura.setIdDetalleFactura(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_plato");
    if (columna != null) {
      detalleFactura.setPlato(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_importe");
    if (columna != null) {
      detalleFactura.setImporte(getObject(columna, Double.class, rs));
    }
  }

  public static DetalleFactura getDetalleFactura(ResultSet rs, Map<String, Integer> columnas) throws PersistenciaExcepcion {
    DetalleFactura detalleFactura = new DetalleFactura();
    getDetalleFactura(rs, columnas, detalleFactura);
    return detalleFactura;
  }

  public static DetalleFactura getDetalleFactura(ResultSet rs, Map<String, Integer> columnas, String alias) throws PersistenciaExcepcion {
    DetalleFactura detalleFactura = new DetalleFactura();
    getDetalleFactura(rs, columnas, detalleFactura, alias);
    return detalleFactura;
  }

}
