package dev.manuel.cocina.negocio.delegados;

import dev.manuel.cocina.persistencia.dao.*;
import dev.manuel.cocina.persistencia.dto.FacturaDTO;
import dev.manuel.cocina.persistencia.entidades.Camarero;
import dev.manuel.cocina.persistencia.entidades.Cliente;
import dev.manuel.cocina.persistencia.entidades.DetalleFactura;
import dev.manuel.cocina.persistencia.entidades.Factura;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.excepcion.PersistenciaExcepcion;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class RegistrarFacturaDelegado extends GenericoDelegado {

  private final ClienteDAO clienteDAO;
  private final CamareroDAO camareroDAO;
  private final FacturaDAO facturaDAO;
  // private final CocineroDAO cocineroDAO;
  private final DetalleFacturaDAO detalleFacturaDAO;

  /**
   * Constructor de la clase
   *
   * @param conn      conexión a la base de datos
   * @param auditoria Información del usuario logeado
   */
  public RegistrarFacturaDelegado(Connection conn, AuditoriaDTO auditoria) throws PersistenciaExcepcion {
    super(conn, auditoria);
    try {
      //this.conn.setAutoCommit(false);*/
    System.out.println("-----------------"+this.conn.getAutoCommit());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    clienteDAO = new ClienteDAO(this.conn, auditoria);
    facturaDAO = new FacturaDAO(this.conn, auditoria);
    camareroDAO = new CamareroDAO(this.conn, auditoria);
    // cocineroDAO = new CocineroDAO(this.conn,auditoria);
    detalleFacturaDAO = new DetalleFacturaDAO(this.conn, auditoria);
  }

  /**
   * Metodo que se engarga de gestionar el registro de una factura con sus respectivos detalles
   *
   * @param factura Dto del encabezado de factura con la lista de detalles de factura
   * @throws PersistenciaExcepcion Error al insertar el encabezado de la facturas
   */
  public void guardarFactura(FacturaDTO factura) throws PersistenciaExcepcion {
    registrarCliente(factura.getFactura().getIdCliente());
    registrarCamarero(factura.getFactura().getIdCamarero());
    registrarFactura(factura.getFactura());
    registrarDetalles(factura);
  }

  /**
   * Registra el cliente que fue atendido y al cual se le realiza una factura
   *
   * @param cliente cliente a facturar
   * @throws PersistenciaExcepcion
   */
  public void registrarCliente(Cliente cliente) throws PersistenciaExcepcion {
    Integer idCliente = cliente.getIdCliente();
    if (idCliente == null || idCliente == 0) {
      clienteDAO.insertar(cliente);
    }
  }

  /**
   * Registra el camarero que atendio al cliente al que se le realiza la factura
   *
   * @param camarero camarero que atendio la mesa
   */
  private void registrarCamarero(Camarero camarero) throws PersistenciaExcepcion {
    Integer idCamarero = camarero.getIdCamarero();
    if (idCamarero == null || idCamarero == 0) {
      camareroDAO.insertar(camarero);
    }

  }

  /**
   * Registra el encabezado de una factura
   *
   * @param factura encabezado de factura
   * @throws PersistenciaExcepcion Error al insertar el encabezado de la facturas
   */
  private void registrarFactura(Factura factura) throws PersistenciaExcepcion {
    Integer idFactura = factura.getIdFactura();
    if (idFactura != null) {
      facturaDAO.editar(factura);
      return;
    }
    factura.setFecha(new Date());
    facturaDAO.insertar(factura);
  }

  /**
   * Registra los detalles de factura que hacen parte de un encabezado de factura
   *
   * @param factura Dto del encabezado de factura con la lista de detalles de factura
   * @throws PersistenciaExcepcion Error en la inserción de los detalles de factura
   */
  private void registrarDetalles(FacturaDTO factura) throws PersistenciaExcepcion {
    detalleFacturaDAO.eliminar(factura.getFactura().getIdFactura());
    for (DetalleFactura detalle : factura.getDetalles()) {
      detalle.setIdFactura(factura.getFactura());
      detalleFacturaDAO.insertar(detalle);
    }
  }

}
