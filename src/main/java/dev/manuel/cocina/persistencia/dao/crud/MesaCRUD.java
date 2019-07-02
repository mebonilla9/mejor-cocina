package dev.manuel.cocina.persistencia.dao.crud;

import dev.manuel.cocina.persistencia.constante.EMensajePersistencia;
import dev.manuel.cocina.persistencia.entidades.Mesa;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.abstracto.GenericoCRUD;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaException;
import dev.manuel.estandar.util.LogUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class MesaCRUD extends GenericoCRUD {

  private final int ID = 1;

  public MesaCRUD(Connection conn, AuditoriaDTO auditoria) throws PersistenciaException {
    super(conn, auditoria);
  }


  public void insertar(Mesa mesa) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.mesa(max_comensal,ubicacion) VALUES (?,?)";
      sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      sentencia.setObject(i++, mesa.getMaxComensal());
      sentencia.setObject(i++, mesa.getUbicacion());

      sentencia.executeUpdate();
      ResultSet rs = sentencia.getGeneratedKeys();
      if (rs.next()) {
        mesa.setIdMesa(rs.getInt("id_mesa"));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void insertarTodos(Mesa mesa) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.mesa(id_mesa,max_comensal,ubicacion) VALUES (?,?,?)";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, mesa.getIdMesa());
      sentencia.setObject(i++, mesa.getMaxComensal());
      sentencia.setObject(i++, mesa.getUbicacion());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void editar(Mesa mesa) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "UPDATE public.mesa SET max_comensal=?,ubicacion=? where id_mesa=? ";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, mesa.getMaxComensal());
      sentencia.setObject(i++, mesa.getUbicacion());
      sentencia.setObject(i++, mesa.getIdMesa());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_EDITAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public List<Mesa> consultar() throws PersistenciaException {
    PreparedStatement sentencia = null;
    List<Mesa> lista = new ArrayList<>();
    try {

      String sql = "SELECT * FROM public.mesa";
      sentencia = cnn.prepareStatement(sql);
      ResultSet rs = sentencia.executeQuery();
      while (rs.next()) {
        lista.add(getMesa(rs));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return lista;

  }


  public Mesa consultar(long id) throws PersistenciaException {
    PreparedStatement sentencia = null;
    Mesa obj = null;
    try {

      String sql = "SELECT * FROM public.mesa WHERE id_mesa=?";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setLong(1, id);
      ResultSet rs = sentencia.executeQuery();
      if (rs.next()) {
        obj = getMesa(rs);
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return obj;
  }

  public static Mesa getMesa(ResultSet rs) throws PersistenciaException {
    Mesa mesa = new Mesa();
    mesa.setIdMesa(getObject("id_mesa", Integer.class, rs));
    mesa.setMaxComensal(getObject("max_comensal", Integer.class, rs));
    mesa.setUbicacion(getObject("ubicacion", String.class, rs));

    return mesa;
  }

  public static void getMesa(ResultSet rs, Map<String, Integer> columnas, Mesa mesa) throws PersistenciaException {
    Integer columna = columnas.get("mesa_id_mesa");
    if (columna != null) {
      mesa.setIdMesa(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("mesa_max_comensal");
    if (columna != null) {
      mesa.setMaxComensal(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("mesa_ubicacion");
    if (columna != null) {
      mesa.setUbicacion(getObject(columna, String.class, rs));
    }
  }

  public static void getMesa(ResultSet rs, Map<String, Integer> columnas, Mesa mesa, String alias) throws PersistenciaException {
    Integer columna = columnas.get(alias + "_id_mesa");
    if (columna != null) {
      mesa.setIdMesa(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_max_comensal");
    if (columna != null) {
      mesa.setMaxComensal(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_ubicacion");
    if (columna != null) {
      mesa.setUbicacion(getObject(columna, String.class, rs));
    }
  }

  public static Mesa getMesa(ResultSet rs, Map<String, Integer> columnas) throws PersistenciaException {
    Mesa mesa = new Mesa();
    getMesa(rs, columnas, mesa);
    return mesa;
  }

  public static Mesa getMesa(ResultSet rs, Map<String, Integer> columnas, String alias) throws PersistenciaException {
    Mesa mesa = new Mesa();
    getMesa(rs, columnas, mesa, alias);
    return mesa;
  }

}
