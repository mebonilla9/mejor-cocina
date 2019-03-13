/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.cocina.negocio.constante;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public enum EMensajes {

    INSERTO(1, "Se insertó correctamente"),
    MODIFICO(1, "Se modificó correctamente"),
    ELIMINO(1, "Se eliminó correctamente"),
    NO_RESULTADOS(0, "No se encontraron  registros"),
    ERROR_INSERTAR(-1, "Error al insertar el registro"),
    ERROR_MODIFICAR(-1, "Error al modificar el registro"),
    ERROR_CONSULTAR(-1, "Error al consultar el registro"),
    ERROR_ELIMINAR(-1, "Error al consultar el registro"),
    ERROR_REGISTRO_EXISTE(-1, "El registro ya existe"),
    ERROR_CONEXION_BD(-2, "No hay conexión con la base de datos");

    private int codigo;
    private String descripcion;

    private EMensajes(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
