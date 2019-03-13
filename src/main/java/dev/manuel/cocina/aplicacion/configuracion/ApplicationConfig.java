package dev.manuel.cocina.aplicacion.configuracion;

import dev.manuel.cocina.negocio.api.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> recursos = new HashSet<>();
    addRestResourcesClass(recursos);
    return recursos;
  }

  private void addRestResourcesClass(Set<Class<?>> recursos) {
    recursos.add(CORSFilter.class);
    recursos.add(AplicacionExcepcionHandler.class);
    recursos.add(NullPointerExceptionHandler.class);
    recursos.add(CamareroApi.class);
    recursos.add(ClienteApi.class);
    recursos.add(CocineroApi.class);
    recursos.add(FacturaApi.class);
    recursos.add(MesaApi.class);
  }
}
