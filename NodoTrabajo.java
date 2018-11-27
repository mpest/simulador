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
 * <b>Descripci�n</b>: Clase que modela el nodo en que las m�quinas trabajan
 * </p>
 * 
 * @author Mariop
 *
 */
public class NodoTrabajo
{
  /**
   * El atributo distribucion de tipo Normal se emplea para almacenar la
   * distribuci�n sobre la cual se realizar�n los c�lculos
   */
  private Normal distribucion = new Normal();

  /**
   * El atributo listaEstacionesTrabajo de tipo List<EstacionTrabajo> se emplea
   * para almacenar la lista de estaciones de trabajo
   */
  private EstacionTrabajo estacionTrabajo;

  /**
   * El atributo listaMaquinasEnCola de tipo List<Maquina> se emplea para
   * almacenar la lista de m�quinas en la cola de la estaci�n de trabajo
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
   * M�todo encargado de
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
   * M�todo encargado de actualizar la cola de la estaci�n
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
    } // fin while para intentar vaciar la lista de m�quinas en cola
  }// fin m�todo actualizarCola

  /**
   * M�todo encargado de generar el siguiente tiempo de rotura de una m�quina
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
