/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.cocina.negocio.constante;

/**
 * Clase encargada de registrar las rutas del sistema
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public final class ERutas {
  public static final String CONTROL_BASE = "";

  public final class Camarero {
    public static final String CONSULTAR = "camarero/consultar";
    public static final String CONSULTAR_INFORME = "camarero/consultar/informe";
  }

  public final class Cliente {
    public static final String CONSULTAR = "cliente/consultar";
    public static final String CONSULTAR_GASTOS = "cliente/consultar/gastos";
  }

  public final class Cocinero {
    public static final String CONSULTAR = "cocinero/consultar";
  }

  public final class Factura {
    public static final String GUARDAR = "factura/guardar";
  }

  public final class Mesa {
    public static final String CONSULTAR = "mesa/consultar";
  }
}
