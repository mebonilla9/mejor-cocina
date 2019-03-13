
package dev.manuel.cocina.negocio.constante;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public enum ETablas {

  CAMARERO("camarero", "camarero"),
  CLIENTE("cliente", "cliente"),
  COCINERO("cocinero", "cocinero"),
  DETALLE_FACTURA("detalle_factura", "detalle factura"),
  FACTURA("factura", "factura"),
  MESA("mesa", "mesa");

  private final String tabla;
  private final String descripcion;

  private ETablas(String tabla, String descripcion) {
    this.tabla = tabla;
    this.descripcion = descripcion;
  }

  public String getTabla() {
    return tabla;
  }

  public String getDescripcion() {
    return descripcion;
  }

}
