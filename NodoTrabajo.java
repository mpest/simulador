/**
 * 
 */
package estaciontrabajo;

import java.util.Deque;
import java.util.LinkedList;

import distribucion.implementaciones.Normal;

/**
 *
 * <b>Nombre de la clase</b>: NodoTrabajo
 * <p>
 * <b>Descripción</b>: Clase que modela el nodo en que las máquinas trabajan
 * </p>
 * 
 * @author Mariop
 *
 */
public class NodoTrabajo
{
  /**
   * El atributo distribucion de tipo Normal se emplea para almacenar la
   * distribución sobre la cual se realizarán los cálculos
   */
  private Normal distribucion = new Normal();

  /**
   * El atributo listaEstacionesTrabajo de tipo List<EstacionTrabajo> se emplea
   * para almacenar la lista de estaciones de trabajo
   */
  private EstacionTrabajo estacionTrabajo;

  /**
   * El atributo listaMaquinasEnCola de tipo List<Maquina> se emplea para
   * almacenar la lista de máquinas en la cola de la estación de trabajo
   */
  private Deque<Maquina> listaMaquinasEnCola = new LinkedList<>();

  /**
   * El atributo siguienteRotura de tipo Double se emplea para almacenar el
   * tiempo hasta la siguiente rotura
   */
  private Double siguienteRotura = this.generarTiempoRotura();

  /**
   * Constructor de la clase NodoTrabajo
   *
   * @param estacionTrabajo
   */
  public NodoTrabajo(EstacionTrabajo estacionTrabajo)
  {
    this.estacionTrabajo = estacionTrabajo;
  }

  /**
   * Método encargado de introducir una máquina en la cola del nodo de trabajo
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
  }

  /**
   * Método encargado de extraer una máquina de la cola del nodo de trabajo. Se
   * extrae una máquina al azar, si no hay ninguna máquina en ninguna estación
   * se devuelve null
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  public Maquina extraerMaquina()
  {
    Maquina maquinaExtraida = this.estacionTrabajo.extraerMaquina();
    this.actualizarCola();
    this.siguienteRotura = this.generarTiempoRotura();
    return maquinaExtraida;
  }

  /**
   * @return the siguienteRotura
   */
  public Double getSiguienteRotura()
  {
    return siguienteRotura;
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
  public int getNumeroMaquinas()
  {
    return this.estacionTrabajo.getNumeroMaquinas();
  }

  /**
   * Método encargado de actualizar la cola de la estación
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  private void actualizarCola()
  {
    while (!this.listaMaquinasEnCola.isEmpty())
    {
      if (this.estacionTrabajo.getNumeroMaquinas() < this.estacionTrabajo
              .getTamanioEstacion())
      {
        this.estacionTrabajo.introducirMaquina(this.listaMaquinasEnCola.pop());
      } else
      {
        return;
      }
    } // fin while para intentar vaciar la lista de máquinas en cola
  }// fin método actualizarCola

  /**
   * Método encargado de generar el siguiente tiempo de rotura de una máquina
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  private Double generarTiempoRotura()
  {
    return new Double(this.distribucion.getSiguienteTiempo(null));
  }
}
