package dev.manuel.cocina.persistencia.dao.crud;

import dev.manuel.cocina.persistencia.constante.EMensajePersistencia;
import dev.manuel.cocina.persistencia.entidades.Camarero;
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
public class CamareroCRUD extends GenericoCRUD {

  private final int ID = 1;

  public CamareroCRUD(Connection conn, AuditoriaDTO auditoria) throws PersistenciaException {
    super(conn, auditoria);
  }


  public void insertar(Camarero camarero) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.camarero(nombre,primer_apellido,segundo_apellido) VALUES (?,?,?)";
      sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      sentencia.setObject(i++, camarero.getNombre());
      sentencia.setObject(i++, camarero.getPrimerApellido());
      sentencia.setObject(i++, camarero.getSegundoApellido());

      sentencia.executeUpdate();
      ResultSet rs = sentencia.getGeneratedKeys();
      if (rs.next()) {
        camarero.setIdCamarero(rs.getInt("id_camarero"));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void insertarTodos(Camarero camarero) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.camarero(id_camarero,nombre,primer_apellido,segundo_apellido) VALUES (?,?,?,?)";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, camarero.getIdCamarero());
      sentencia.setObject(i++, camarero.getNombre());
      sentencia.setObject(i++, camarero.getPrimerApellido());
      sentencia.setObject(i++, camarero.getSegundoApellido());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void editar(Camarero camarero) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "UPDATE public.camarero SET nombre=?,primer_apellido=?,segundo_apellido=? where id_camarero=? ";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, camarero.getNombre());
      sentencia.setObject(i++, camarero.getPrimerApellido());
      sentencia.setObject(i++, camarero.getSegundoApellido());
      sentencia.setObject(i++, camarero.getIdCamarero());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_EDITAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public List<Camarero> consultar() throws PersistenciaException {
    PreparedStatement sentencia = null;
    List<Camarero> lista = new ArrayList<>();
    try {

      String sql = "SELECT * FROM public.camarero";
      sentencia = cnn.prepareStatement(sql);
      ResultSet rs = sentencia.executeQuery();
      while (rs.next()) {
        lista.add(getCamarero(rs));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return lista;

  }


  public Camarero consultar(long id) throws PersistenciaException {
    PreparedStatement sentencia = null;
    Camarero obj = null;
    try {

      String sql = "SELECT * FROM public.camarero WHERE id_camarero=?";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setLong(1, id);
      ResultSet rs = sentencia.executeQuery();
      if (rs.next()) {
        obj = getCamarero(rs);
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return obj;
  }

  public static Camarero getCamarero(ResultSet rs) throws PersistenciaException {
    Camarero camarero = new Camarero();
    camarero.setIdCamarero(getObject("id_camarero", Integer.class, rs));
    camarero.setNombre(getObject("nombre", String.class, rs));
    camarero.setPrimerApellido(getObject("primer_apellido", String.class, rs));
    camarero.setSegundoApellido(getObject("segundo_apellido", String.class, rs));

    return camarero;
  }

  public static void getCamarero(ResultSet rs, Map<String, Integer> columnas, Camarero camarero) throws PersistenciaException {
    Integer columna = columnas.get("camarero_id_camarero");
    if (columna != null) {
      camarero.setIdCamarero(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("camarero_nombre");
    if (columna != null) {
      camarero.setNombre(getObject(columna, String.class, rs));
    }
    columna = columnas.get("camarero_primer_apellido");
    if (columna != null) {
      camarero.setPrimerApellido(getObject(columna, String.class, rs));
    }
    columna = columnas.get("camarero_segundo_apellido");
    if (columna != null) {
      camarero.setSegundoApellido(getObject(columna, String.class, rs));
    }
  }

  public static void getCamarero(ResultSet rs, Map<String, Integer> columnas, Camarero camarero, String alias) throws PersistenciaException {
    Integer columna = columnas.get(alias + "_id_camarero");
    if (columna != null) {
      camarero.setIdCamarero(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_nombre");
    if (columna != null) {
      camarero.setNombre(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_primer_apellido");
    if (columna != null) {
      camarero.setPrimerApellido(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_segundo_apellido");
    if (columna != null) {
      camarero.setSegundoApellido(getObject(columna, String.class, rs));
    }
  }

  public static Camarero getCamarero(ResultSet rs, Map<String, Integer> columnas) throws PersistenciaException {
    Camarero camarero = new Camarero();
    getCamarero(rs, columnas, camarero);
    return camarero;
  }

  public static Camarero getCamarero(ResultSet rs, Map<String, Integer> columnas, String alias) throws PersistenciaException {
    Camarero camarero = new Camarero();
    getCamarero(rs, columnas, camarero, alias);
    return camarero;
  }

}
