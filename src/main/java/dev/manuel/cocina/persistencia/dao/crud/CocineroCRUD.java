package dev.manuel.cocina.persistencia.dao.crud;

import dev.manuel.cocina.persistencia.constante.EMensajePersistencia;
import dev.manuel.cocina.persistencia.entidades.Cocinero;
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
public class CocineroCRUD extends GenericoCRUD {

  private final int ID = 1;

  public CocineroCRUD(Connection conn, AuditoriaDTO auditoria) throws PersistenciaException {
    super(conn, auditoria);
  }


  public void insertar(Cocinero cocinero) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.cocinero(nombre,primer_apellido,segundo_apellido) VALUES (?,?,?)";
      sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      sentencia.setObject(i++, cocinero.getNombre());
      sentencia.setObject(i++, cocinero.getPrimerApellido());
      sentencia.setObject(i++, cocinero.getSegundoApellido());

      sentencia.executeUpdate();
      ResultSet rs = sentencia.getGeneratedKeys();
      if (rs.next()) {
        cocinero.setIdCocinero(rs.getInt("id_cocinero"));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void insertarTodos(Cocinero cocinero) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.cocinero(id_cocinero,nombre,primer_apellido,segundo_apellido) VALUES (?,?,?,?)";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, cocinero.getIdCocinero());
      sentencia.setObject(i++, cocinero.getNombre());
      sentencia.setObject(i++, cocinero.getPrimerApellido());
      sentencia.setObject(i++, cocinero.getSegundoApellido());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void editar(Cocinero cocinero) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "UPDATE public.cocinero SET nombre=?,primer_apellido=?,segundo_apellido=? where id_cocinero=? ";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, cocinero.getNombre());
      sentencia.setObject(i++, cocinero.getPrimerApellido());
      sentencia.setObject(i++, cocinero.getSegundoApellido());
      sentencia.setObject(i++, cocinero.getIdCocinero());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_EDITAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public List<Cocinero> consultar() throws PersistenciaException {
    PreparedStatement sentencia = null;
    List<Cocinero> lista = new ArrayList<>();
    try {

      String sql = "SELECT * FROM public.cocinero";
      sentencia = cnn.prepareStatement(sql);
      ResultSet rs = sentencia.executeQuery();
      while (rs.next()) {
        lista.add(getCocinero(rs));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return lista;

  }


  public Cocinero consultar(long id) throws PersistenciaException {
    PreparedStatement sentencia = null;
    Cocinero obj = null;
    try {

      String sql = "SELECT * FROM public.cocinero WHERE id_cocinero=?";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setLong(1, id);
      ResultSet rs = sentencia.executeQuery();
      if (rs.next()) {
        obj = getCocinero(rs);
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return obj;
  }

  public static Cocinero getCocinero(ResultSet rs) throws PersistenciaException {
    Cocinero cocinero = new Cocinero();
    cocinero.setIdCocinero(getObject("id_cocinero", Integer.class, rs));
    cocinero.setNombre(getObject("nombre", String.class, rs));
    cocinero.setPrimerApellido(getObject("primer_apellido", String.class, rs));
    cocinero.setSegundoApellido(getObject("segundo_apellido", String.class, rs));

    return cocinero;
  }

  public static void getCocinero(ResultSet rs, Map<String, Integer> columnas, Cocinero cocinero) throws PersistenciaException {
    Integer columna = columnas.get("cocinero_id_cocinero");
    if (columna != null) {
      cocinero.setIdCocinero(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("cocinero_nombre");
    if (columna != null) {
      cocinero.setNombre(getObject(columna, String.class, rs));
    }
    columna = columnas.get("cocinero_primer_apellido");
    if (columna != null) {
      cocinero.setPrimerApellido(getObject(columna, String.class, rs));
    }
    columna = columnas.get("cocinero_segundo_apellido");
    if (columna != null) {
      cocinero.setSegundoApellido(getObject(columna, String.class, rs));
    }
  }

  public static void getCocinero(ResultSet rs, Map<String, Integer> columnas, Cocinero cocinero, String alias) throws PersistenciaException {
    Integer columna = columnas.get(alias + "_id_cocinero");
    if (columna != null) {
      cocinero.setIdCocinero(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_nombre");
    if (columna != null) {
      cocinero.setNombre(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_primer_apellido");
    if (columna != null) {
      cocinero.setPrimerApellido(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_segundo_apellido");
    if (columna != null) {
      cocinero.setSegundoApellido(getObject(columna, String.class, rs));
    }
  }

  public static Cocinero getCocinero(ResultSet rs, Map<String, Integer> columnas) throws PersistenciaException {
    Cocinero cocinero = new Cocinero();
    getCocinero(rs, columnas, cocinero);
    return cocinero;
  }

  public static Cocinero getCocinero(ResultSet rs, Map<String, Integer> columnas, String alias) throws PersistenciaException {
    Cocinero cocinero = new Cocinero();
    getCocinero(rs, columnas, cocinero, alias);
    return cocinero;
  }

}
