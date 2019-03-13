package dev.manuel.cocina.persistencia.dao.crud;

import dev.manuel.cocina.negocio.utilidades.DateUtil;
import dev.manuel.cocina.persistencia.basedatos.PostgresBD;
import dev.manuel.cocina.persistencia.constante.EMensajePersistencia;
import dev.manuel.cocina.persistencia.entidades.Camarero;
import dev.manuel.cocina.persistencia.entidades.Cliente;
import dev.manuel.cocina.persistencia.entidades.Factura;
import dev.manuel.cocina.persistencia.entidades.Mesa;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.abstracto.GenericoCRUD;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;
import dev.manuel.estandar.util.LogUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class FacturaCRUD extends GenericoCRUD {

  private final int ID = 1;

  public FacturaCRUD(Connection conn, AuditoriaDTO auditoria) throws PersistenciaExcepcion {
    super(conn, auditoria);
  }


  public void insertar(Factura factura) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.factura(fecha,id_cliente,id_mesa,id_camarero) VALUES (?,?,?,?)";
      sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      sentencia.setObject(i++, DateUtil.parseDate(factura.getFecha()));
      Object idCliente = (factura.getIdCliente() == null) ? null : factura.getIdCliente().getIdCliente();
      sentencia.setObject(i++, idCliente);
      Object idMesa = (factura.getIdMesa() == null) ? null : factura.getIdMesa().getIdMesa();
      sentencia.setObject(i++, idMesa);
      Object idCamarero = (factura.getIdCamarero() == null) ? null : factura.getIdCamarero().getIdCamarero();
      sentencia.setObject(i++, idCamarero);

      sentencia.executeUpdate();
      ResultSet rs = sentencia.getGeneratedKeys();
      if (rs.next()) {
        factura.setIdFactura(rs.getInt("id_factura"));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void insertarTodos(Factura factura) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.factura(id_factura,fecha,id_cliente,id_mesa,id_camarero) VALUES (?,?,?,?,?)";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, factura.getIdFactura());
      sentencia.setObject(i++, DateUtil.parseDate(factura.getFecha()));
      Object idCliente = (factura.getIdCliente() == null) ? null : factura.getIdCliente().getIdCliente();
      sentencia.setObject(i++, idCliente);
      Object idMesa = (factura.getIdMesa() == null) ? null : factura.getIdMesa().getIdMesa();
      sentencia.setObject(i++, idMesa);
      Object idCamarero = (factura.getIdCamarero() == null) ? null : factura.getIdCamarero().getIdCamarero();
      sentencia.setObject(i++, idCamarero);

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void editar(Factura factura) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "UPDATE public.factura SET fecha=?,id_cliente=?,id_mesa=?,id_camarero=? where id_factura=? ";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, DateUtil.parseDate(factura.getFecha()));
      Object idCliente = (factura.getIdCliente() == null) ? null : factura.getIdCliente().getIdCliente();
      sentencia.setObject(i++, idCliente);
      Object idMesa = (factura.getIdMesa() == null) ? null : factura.getIdMesa().getIdMesa();
      sentencia.setObject(i++, idMesa);
      Object idCamarero = (factura.getIdCamarero() == null) ? null : factura.getIdCamarero().getIdCamarero();
      sentencia.setObject(i++, idCamarero);
      sentencia.setObject(i++, factura.getIdFactura());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_EDITAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public List<Factura> consultar() throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    List<Factura> lista = new ArrayList<>();
    try {

      String sql = "SELECT * FROM public.factura";
      sentencia = cnn.prepareStatement(sql);
      ResultSet rs = sentencia.executeQuery();
      while (rs.next()) {
        lista.add(getFactura(rs));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return lista;

  }


  public Factura consultar(long id) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    Factura obj = null;
    try {

      String sql = "SELECT * FROM public.factura WHERE id_factura=?";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setLong(1, id);
      ResultSet rs = sentencia.executeQuery();
      if (rs.next()) {
        obj = getFactura(rs);
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return obj;
  }

  public static Factura getFactura(ResultSet rs) throws PersistenciaExcepcion {
    Factura factura = new Factura();
    factura.setIdFactura(getObject("id_factura", Integer.class, rs));
    factura.setFecha(getObject("fecha", Date.class, rs));
    Cliente cliente = new Cliente();
    cliente.setIdCliente(getObject("id_cliente", Integer.class, rs));
    factura.setIdCliente(cliente);
    Mesa mesa = new Mesa();
    mesa.setIdMesa(getObject("id_mesa", Integer.class, rs));
    factura.setIdMesa(mesa);
    Camarero camarero = new Camarero();
    camarero.setIdCamarero(getObject("id_camarero", Integer.class, rs));
    factura.setIdCamarero(camarero);

    return factura;
  }

  public static void getFactura(ResultSet rs, Map<String, Integer> columnas, Factura factura) throws PersistenciaExcepcion {
    Integer columna = columnas.get("factura_id_factura");
    if (columna != null) {
      factura.setIdFactura(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("factura_fecha");
    if (columna != null) {
      factura.setFecha(getObject(columna, Date.class, rs));
    }
    columna = columnas.get("factura_id_cliente");
    if (columna != null) {
      Cliente cliente = new Cliente();
      cliente.setIdCliente(getObject(columna, Integer.class, rs));
      factura.setIdCliente(cliente);
    }
    columna = columnas.get("factura_id_mesa");
    if (columna != null) {
      Mesa mesa = new Mesa();
      mesa.setIdMesa(getObject(columna, Integer.class, rs));
      factura.setIdMesa(mesa);
    }
    columna = columnas.get("factura_id_camarero");
    if (columna != null) {
      Camarero camarero = new Camarero();
      camarero.setIdCamarero(getObject(columna, Integer.class, rs));
      factura.setIdCamarero(camarero);
    }
  }

  public static void getFactura(ResultSet rs, Map<String, Integer> columnas, Factura factura, String alias) throws PersistenciaExcepcion {
    Integer columna = columnas.get(alias + "_id_factura");
    if (columna != null) {
      factura.setIdFactura(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_fecha");
    if (columna != null) {
      factura.setFecha(getObject(columna, Date.class, rs));
    }
  }

  public static Factura getFactura(ResultSet rs, Map<String, Integer> columnas) throws PersistenciaExcepcion {
    Factura factura = new Factura();
    getFactura(rs, columnas, factura);
    return factura;
  }

  public static Factura getFactura(ResultSet rs, Map<String, Integer> columnas, String alias) throws PersistenciaExcepcion {
    Factura factura = new Factura();
    getFactura(rs, columnas, factura, alias);
    return factura;
  }

}
