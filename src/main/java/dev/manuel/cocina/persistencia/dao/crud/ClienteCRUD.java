package dev.manuel.cocina.persistencia.dao.crud;

import dev.manuel.cocina.persistencia.basedatos.PostgresBD;
import dev.manuel.cocina.persistencia.constante.EMensajePersistencia;
import dev.manuel.cocina.persistencia.entidades.Cliente;
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
public class ClienteCRUD extends GenericoCRUD {

  private final int ID = 1;

  public ClienteCRUD(Connection conn, AuditoriaDTO auditoria) throws PersistenciaExcepcion {
    super(conn, auditoria);

  }


  public void insertar(Cliente cliente) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.cliente(nombre,primer_apellido,segundo_apellido,observaciones) VALUES (?,?,?,?)";
      sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      sentencia.setObject(i++, cliente.getNombre());
      sentencia.setObject(i++, cliente.getPrimerApellido());
      sentencia.setObject(i++, cliente.getSegundoApellido());
      sentencia.setObject(i++, cliente.getObservaciones());

      sentencia.executeUpdate();
      ResultSet rs = sentencia.getGeneratedKeys();
      if (rs.next()) {
        cliente.setIdCliente(rs.getInt("id_cliente"));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void insertarTodos(Cliente cliente) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.cliente(id_cliente,nombre,primer_apellido,segundo_apellido,observaciones) VALUES (?,?,?,?,?)";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, cliente.getIdCliente());
      sentencia.setObject(i++, cliente.getNombre());
      sentencia.setObject(i++, cliente.getPrimerApellido());
      sentencia.setObject(i++, cliente.getSegundoApellido());
      sentencia.setObject(i++, cliente.getObservaciones());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void editar(Cliente cliente) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "UPDATE public.cliente SET nombre=?,primer_apellido=?,segundo_apellido=?,observaciones=? where id_cliente=? ";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, cliente.getNombre());
      sentencia.setObject(i++, cliente.getPrimerApellido());
      sentencia.setObject(i++, cliente.getSegundoApellido());
      sentencia.setObject(i++, cliente.getObservaciones());
      sentencia.setObject(i++, cliente.getIdCliente());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_EDITAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public List<Cliente> consultar() throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    List<Cliente> lista = new ArrayList<>();
    try {

      String sql = "SELECT * FROM public.cliente";
      sentencia = cnn.prepareStatement(sql);
      ResultSet rs = sentencia.executeQuery();
      while (rs.next()) {
        lista.add(getCliente(rs));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return lista;

  }


  public Cliente consultar(long id) throws PersistenciaExcepcion {
    PreparedStatement sentencia = null;
    Cliente obj = null;
    try {

      String sql = "SELECT * FROM public.cliente WHERE id_cliente=?";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setLong(1, id);
      ResultSet rs = sentencia.executeQuery();
      if (rs.next()) {
        obj = getCliente(rs);
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaExcepcion(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return obj;
  }

  public static Cliente getCliente(ResultSet rs) throws PersistenciaExcepcion {
    Cliente cliente = new Cliente();
    cliente.setIdCliente(getObject("id_cliente", Integer.class, rs));
    cliente.setNombre(getObject("nombre", String.class, rs));
    cliente.setPrimerApellido(getObject("primer_apellido", String.class, rs));
    cliente.setSegundoApellido(getObject("segundo_apellido", String.class, rs));
    cliente.setObservaciones(getObject("observaciones", String.class, rs));

    return cliente;
  }

  public static void getCliente(ResultSet rs, Map<String, Integer> columnas, Cliente cliente) throws PersistenciaExcepcion {
    Integer columna = columnas.get("cliente_id_cliente");
    if (columna != null) {
      cliente.setIdCliente(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("cliente_nombre");
    if (columna != null) {
      cliente.setNombre(getObject(columna, String.class, rs));
    }
    columna = columnas.get("cliente_primer_apellido");
    if (columna != null) {
      cliente.setPrimerApellido(getObject(columna, String.class, rs));
    }
    columna = columnas.get("cliente_segundo_apellido");
    if (columna != null) {
      cliente.setSegundoApellido(getObject(columna, String.class, rs));
    }
    columna = columnas.get("cliente_observaciones");
    if (columna != null) {
      cliente.setObservaciones(getObject(columna, String.class, rs));
    }
  }

  public static void getCliente(ResultSet rs, Map<String, Integer> columnas, Cliente cliente, String alias) throws PersistenciaExcepcion {
    Integer columna = columnas.get(alias + "_id_cliente");
    if (columna != null) {
      cliente.setIdCliente(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_nombre");
    if (columna != null) {
      cliente.setNombre(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_primer_apellido");
    if (columna != null) {
      cliente.setPrimerApellido(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_segundo_apellido");
    if (columna != null) {
      cliente.setSegundoApellido(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_observaciones");
    if (columna != null) {
      cliente.setObservaciones(getObject(columna, String.class, rs));
    }
  }

  public static Cliente getCliente(ResultSet rs, Map<String, Integer> columnas) throws PersistenciaExcepcion {
    Cliente cliente = new Cliente();
    getCliente(rs, columnas, cliente);
    return cliente;
  }

  public static Cliente getCliente(ResultSet rs, Map<String, Integer> columnas, String alias) throws PersistenciaExcepcion {
    Cliente cliente = new Cliente();
    getCliente(rs, columnas, cliente, alias);
    return cliente;
  }

}
