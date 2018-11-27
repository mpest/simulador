/**
 * 
 */
package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import estaciontrabajo.EstacionReparacion;
import estaciontrabajo.EstacionTrabajo;
import estaciontrabajo.Maquina;
import estaciontrabajo.NodoReparacion;
import estaciontrabajo.NodoTrabajo;

/**
 *
 * <b>Nombre de la clase</b>: MainSimulador
 * <p>
 * <b>Descripción</b>:
 * </p>
 * 
 * @author Mariop
 *
 */
public class MainSimulador
{
  private static final String PATRON_TRAZAS = "[T= {0}]   Evento: {1}";

  /**
   * El atributo MIN_MAQUINAS de tipo int se emplea para
   */
  private static final int MIN_MAQUINAS = 10;

  /**
   * El atributo MAQUINAS_SISTEMA de tipo int se emplea para almacenar el número
   * máximo de máquinas en el sistema
   */
  private static final int MAQUINAS_SISTEMA = 14;

  /**
   * El atributo OPERARIOS de tipo int se emplea para almacenar el número de
   * operarios del sistema
   */
  private static final int OPERARIOS = 3;

  /**
   * El atributo TIEMPO_SIMULACION de tipo double se emplea para almacenar el
   * número de horas que se quiere simular
   */
  private static final double TIEMPO_SIMULACION = 100;

  /**
   * Método encargado de
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @param args
   */
  public static void main(String[] args)
  {
    List<Maquina> listaMaquinas = new ArrayList<>();
    List<EstacionReparacion> listaEstacionesReparacion = new ArrayList<>();

    // Se generan las máquinas del sistema
    for (int i = 0; i < MAQUINAS_SISTEMA; i++)
    {
      listaMaquinas.add(new Maquina());
    }

    for (int i = 0; i < OPERARIOS; i++)
    {
      listaEstacionesReparacion.add(new EstacionReparacion());
    }

    // Se generan las estaciones de trabajo
    EstacionTrabajo estacionFuncionamiento = new EstacionTrabajo(10);
    NodoTrabajo nodoFuncionamiento = new NodoTrabajo(estacionFuncionamiento);
    NodoReparacion nodoReparacion = new NodoReparacion(
            listaEstacionesReparacion, OPERARIOS);
    // Se introducen las máquinas en las estaciones
    for (Maquina maquinaIter : listaMaquinas)
    {
      nodoFuncionamiento.introducirMaquina(maquinaIter);
    }

    // Simulación
    TreeMap<Double, EnumEventoSiguiente> listaSiguientesEventos = new TreeMap<>(
            (Comparator<Double>) (o1, o2) -> o1.compareTo(o2));
    double tiempoFuncionamiento = 0;
    // Funcionamiento del sistema en general
    // Se abre el fichero de salida
    escribirFichero("Reports file:");

    // Variable de tiempo de simulación
    double tiempoEventoAnterior = 0;
    double tiempoSimulacion = nodoFuncionamiento.getSiguienteRotura();

    // Se generan las variables que se quieren extraer
    EnumEventoSiguiente eventoSiguiente = EnumEventoSiguiente.FALLO;

    // Bucle de simulación
    while (tiempoSimulacion < TIEMPO_SIMULACION)
    {
      // Se actualizan los tiempos
      if (nodoFuncionamiento.getNumeroMaquinas() == MIN_MAQUINAS)
      {
        tiempoFuncionamiento += tiempoSimulacion - tiempoEventoAnterior;
      }

      // Se comprueba el evento siguiente
      switch (eventoSiguiente) {
      case FALLO:
      {
        nodoReparacion.introducirMaquina(nodoFuncionamiento.extraerMaquina());
        break;
      }
      case REPARACION:
      {
        nodoFuncionamiento.introducirMaquina(nodoReparacion.extraerMaquina());
        break;
      }
      }// fin switch
      escribirFichero(MessageFormat.format(PATRON_TRAZAS, tiempoSimulacion,
              eventoSiguiente));

      // Se buscan los siguientes eventos de fallo:
      listaSiguientesEventos.put(nodoFuncionamiento.getSiguienteRotura(),
              EnumEventoSiguiente.FALLO);
      // Se buscan los siguientes eventos de reparación, si los hay:
      for (Double tiempo : nodoReparacion.getMapaEstacionesTiempos().values())
      {
        if (tiempo != -1)
        {
          listaSiguientesEventos.put(tiempo, EnumEventoSiguiente.REPARACION);
        }
      }
      // Se actualizan tiempos
      Entry<Double, EnumEventoSiguiente> siguienteEvento = listaSiguientesEventos
              .pollFirstEntry();
      eventoSiguiente = siguienteEvento.getValue();
      tiempoEventoAnterior = tiempoSimulacion;
      tiempoSimulacion += siguienteEvento.getKey();
    }
    escribirFichero("Fin Simulación.");
    escribirFichero("Tiempo de funcionamiento total: " + tiempoFuncionamiento);
    escribirFichero("Tiempo operarios simultaneamente: "
            + nodoReparacion.tiempoAcumuladoSimultaneo());

  }

  /**
   * Método encargado de
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @param texto
   */
  private static void escribirFichero(String texto)
  {
    try
    {
      Files.write(Paths.get("./report.txt"), (texto + "\n").getBytes(),
              StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
