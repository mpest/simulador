/**
 * Clase generada y codificada por Mariop
 */
package estaciontrabajo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * <b>Nombre de la clase</b>: NodoReparacion
 * <p>
 * <b>Descripci�n</b>:
 * </p>
 * 
 * @author Mariop
 *
 */
public class NodoReparacion
{
  private final int numeroEstaciones;

  private Map<EstacionReparacion, Double> mapaEstacionesTiempos = new HashMap<>();

  private Deque<Maquina> listaMaquinasEnCola = new LinkedList<>();

  private Double tiempoAcumulado = new Double(0);

  private boolean acumularTiempo = true;

  /**
   * Constructor de la clase NodoTrabajo
   *
   * @param estacionTrabajo
   */
  public NodoReparacion(List<EstacionReparacion> estacionesReparacion,
          int numeroEstaciones)
  {
    for (EstacionReparacion estacion : estacionesReparacion)
    {
      this.mapaEstacionesTiempos.put(estacion, estacion.getSiguienteTiempo());
    }
    this.numeroEstaciones = numeroEstaciones;
  }

  /**
   * M�todo encargado de introducir una m�quina en la cola del nodo de trabajo
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @param maquina
   */
  public void introducirMaquina(Maquina maquina)
  {
    this.listaMaquinasEnCola.push(maquina);
    this.actualizarCola();
    this.generarTiemposRotura();
    this.comprobarAcumulado();
  }

  /**
   * M�todo encargado de extraer una m�quina de la cola del nodo de trabajo. Se
   * extrae una m�quina al azar, si no hay ninguna m�quina en ninguna estaci�n
   * se devuelve null
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  public Maquina extraerMaquina()
  {
    Maquina maquinaExtraida = null;
    for (EstacionReparacion estacionIter : this.mapaEstacionesTiempos.keySet())
    {
      if (estacionIter.tieneMaquina())
      {
        maquinaExtraida = estacionIter.extraerMaquina();
        break;
      }
    }
    this.actualizarCola();
    this.generarTiemposRotura();
    return maquinaExtraida;
  }

  /**
   * @return the mapaEstacionesTiempos
   */
  public Map<EstacionReparacion, Double> getMapaEstacionesTiempos()
  {
    return mapaEstacionesTiempos;
  }

  public Double tiempoAcumuladoSimultaneo()
  {
    return this.tiempoAcumulado;
  }

  /**
   * M�todo encargado de actualizar la cola de la estaci�n
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  private void actualizarCola()
  {
    for (EstacionReparacion estacionIter : this.mapaEstacionesTiempos.keySet())
    {
      if (!estacionIter.tieneMaquina() && !this.listaMaquinasEnCola.isEmpty())
      {
        estacionIter.introducirMaquina(this.listaMaquinasEnCola.pop());
      }
    }
  }

  /**
   * M�todo encargado de generar el siguiente tiempo de rotura de una m�quina
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  private void generarTiemposRotura()
  {
    int flag = 0;
    for (EstacionReparacion estacionIter : this.mapaEstacionesTiempos.keySet())
    {
      this.mapaEstacionesTiempos.put(estacionIter,
              estacionIter.getSiguienteTiempo());
      if (this.mapaEstacionesTiempos.get(estacionIter) != -1)
      {
        flag++;
      }
    }
    if (flag == this.numeroEstaciones && this.acumularTiempo)
    {
      List<Double> values = new ArrayList<>(
              this.mapaEstacionesTiempos.values());
      this.tiempoAcumulado += values
              .get(values.indexOf(Collections.min(values)));
      this.acumularTiempo = false;
    }
  }

  private void comprobarAcumulado()
  {
    int flag = 0;
    for (EstacionReparacion estacionIter : this.mapaEstacionesTiempos.keySet())
    {
      flag++;
    }
    if (flag == this.numeroEstaciones)
    {
      this.acumularTiempo = true;
    }
  }
}
