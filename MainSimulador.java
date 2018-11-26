/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import distribucion.implementaciones.Exponencial;
import distribucion.implementaciones.Normal;
import distribucion.interfaces.IDistribucionSalida;
import estaciontrabajo.EstacionTrabajo;
import estaciontrabajo.Maquina;
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
  private static final double TIEMPO_SIMULACION = 2;

  private static List<Maquina> listaMaquinas = new ArrayList<>();

  private static List<EstacionTrabajo> listaEstacionesReparacion = new ArrayList<>();

  private static NodoTrabajo nodoFuncionamiento;

  private static NodoTrabajo nodoReparacion;

  private static double tiempoFuncionamiento = 0;

  private static double tiempoOperariosSimultaneamente = 0;

  private static IDistribucionSalida distribucionNormal = new Normal();

  private static IDistribucionSalida distribucionExponencial1 = new Exponencial(
          3);

  private static IDistribucionSalida distribucionExponencial2 = new Exponencial(
          4);

  private static IDistribucionSalida distribucionExponencial3 = new Exponencial(
          5);

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
    System.out.println("Empezao");
    // Funcionamiento del sistema en general
    init();
    // Variable de tiempo de simulación
    double tiempoSimulacion = 0;

    // Se genera la lista de tiempos en los que las máquinas se estropearán
    Deque<Double> tiemposFallos = new LinkedList<>();
    Deque<Double> tiemposReparaciones = new LinkedList<>();
    double tiempoEventoSiguiente = 0;

    // Se generan las variables que se quieren extraer
    boolean fabricaParada = false;
    EnumEventoSiguiente eventoSiguiente = EnumEventoSiguiente.FALLO;

    // Bucle de simulación
    while (tiempoSimulacion < TIEMPO_SIMULACION)
    {
      switch (eventoSiguiente) {
      case FALLO:
      {
        nodoReparacion.introducirMaquina(nodoFuncionamiento.extraerMaquina());
        if (nodoFuncionamiento.numeroMaquinas() < 10)
        {
          fabricaParada = true;
        }
        break;
      }
      case REPARACION:
      {
        nodoFuncionamiento.introducirMaquina(nodoReparacion.extraerMaquina());
        if (nodoFuncionamiento.numeroMaquinas() == 10 && fabricaParada)
        {
          fabricaParada = false;
        }
        break;
      }
      }// fin switch

      if (listaEstacionesReparacion.stream()
              .filter(EstacionTrabajo::tieneMaquinas).findAny().isPresent())
      {
        // Si hay alguna máquina en algún lugar del nodo de reparación se
        // generan tiempos de reparación
        for (Double tiempoIter : generarTiemposReparacion(tiempoSimulacion))
        {
          tiemposReparaciones.push(tiempoIter);
        }
      } else
      {
        tiemposReparaciones.clear();
      }

      if (tiemposFallos.isEmpty())
      {
        tiemposFallos.push(generarTiempoFallo(tiempoSimulacion));
      }

      tiempoEventoSiguiente = actualizarTiempos(tiemposFallos,
              tiemposReparaciones, eventoSiguiente);

      actualizarFuncionamientos(tiempoSimulacion, tiempoEventoSiguiente,
              fabricaParada);

      tiempoSimulacion = tiempoEventoSiguiente;
    }
    System.out.println("Terminao");
  }

  /**
   * Método encargado de
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @param tiempoSimulacion
   * @param tiempoEventoSiguiente
   * @param fabricaParada
   */
  private static void actualizarFuncionamientos(double tiempoSimulacion,
          double tiempoEventoSiguiente, boolean fabricaParada)
  {
    if (!fabricaParada)
    {
      tiempoFuncionamiento += tiempoEventoSiguiente - tiempoSimulacion;
    }
    if (nodoReparacion.numeroMaquinas() == 3)
    {
      tiempoOperariosSimultaneamente += tiempoEventoSiguiente
              - tiempoSimulacion;
    }
  }

  /**
   * Método encargado de
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @param tiemposFallos
   * @param tiemposReparaciones
   * @param eventoSiguiente
   * @return
   */
  private static double actualizarTiempos(Deque<Double> tiemposFallos,
          Deque<Double> tiemposReparaciones,
          EnumEventoSiguiente eventoSiguiente)
  {
    Double primerTiempoFallo = tiemposFallos.getFirst();
    Double primerTiempoRep = tiemposReparaciones.getFirst();
    eventoSiguiente = primerTiempoFallo < primerTiempoRep
            ? EnumEventoSiguiente.FALLO
            : EnumEventoSiguiente.REPARACION;
    return primerTiempoFallo < primerTiempoRep ? primerTiempoFallo
            : primerTiempoRep;
  }

  /**
   * Método encargado de
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  private static List<Double> generarTiemposReparacion(Double tiempoSimulacion)
  {
    List<Double> output = new ArrayList<>();
    if (nodoReparacion.getListaEstacionesTrabajo().get(0).tieneMaquinas())
    {
      output.add(distribucionExponencial1.getSiguienteTiempo(null)
              + tiempoSimulacion);
    }
    if (nodoReparacion.getListaEstacionesTrabajo().get(1).tieneMaquinas())
    {
      output.add(distribucionExponencial2.getSiguienteTiempo(null)
              + tiempoSimulacion);
    }
    if (nodoReparacion.getListaEstacionesTrabajo().get(2).tieneMaquinas())
    {
      output.add(distribucionExponencial3.getSiguienteTiempo(null)
              + tiempoSimulacion);
    }
    output.sort((Double d1, Double d2) -> d1.compareTo(d2));
    return output;
  }

  /**
   * Método encargado de
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  private static Double generarTiempoFallo(Double tiempoSimulacion)
  {
    return distribucionNormal.getSiguienteTiempo(null) + tiempoSimulacion;
  }

  /**
   * Método encargado de inicializar las máquinas, los nodos y estaciones para
   * la simulación
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  private static void init()
  {
    // Se generan las máquinas del sistema
    for (int i = 0; i < MAQUINAS_SISTEMA; i++)
    {
      listaMaquinas.add(new Maquina());
    }
    // Se generan las estaciones de trabajo
    EstacionTrabajo estacionFuncionamiento = new EstacionTrabajo(10);

    for (int i = 0; i < OPERARIOS; i++)
    {
      listaEstacionesReparacion.add(new EstacionTrabajo(1));
    }

    // Se generan los nodos de trabajo
    nodoFuncionamiento = new NodoTrabajo(Arrays.asList(estacionFuncionamiento));
    nodoReparacion = new NodoTrabajo(listaEstacionesReparacion);

    // Se introducen las máquinas en las estaciones
    for (Maquina maquinaIter : listaMaquinas)
    {
      nodoFuncionamiento.introducirMaquina(maquinaIter);
    }
  }// fin método init

}
