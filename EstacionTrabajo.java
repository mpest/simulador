/**
 * 
 */
package estaciontrabajo;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * <b>Nombre de la clase</b>: EstacionTrabajo
 * <p>
 * <b>Descripción</b>: Clase encargada de modelar la estación de trabajo
 * genérica
 * </p>
 * 
 * @author Mariop
 *
 */
public class EstacionTrabajo
{
  /**
   * El atributo tamanioEstacion de tipo int se emplea para almacenar el número
   * máximo de máquinas que pueden entrar en la estación
   */
  private int tamanioEstacion;

  /**
   * El atributo listaMaquinas de tipo List<Maquina> se emplea para almacenar la
   * lista de máquinas contenidas en la estación de trabajo en particular
   */
  private Deque<Maquina> listaMaquinas = new LinkedList<>();

  /**
   * Constructor de la clase EstacionTrabajo
   *
   * @param numeroMaxMaquinas
   */
  public EstacionTrabajo(int numeroMaxMaquinas)
  {
    this.tamanioEstacion = numeroMaxMaquinas;
  }

  /**
   * Método encargado de devolver el número de máquinas que se encuentran
   * actualmente en la estación
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public int getNumeroMaquinas()
  {
    return this.listaMaquinas.size();
  }

  /**
   * Método encargado de introducir una máquina en la cola de la estación
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   * 
   *
   */
  public void introducirMaquina(Maquina maquina)
  {
    this.listaMaquinas.push(maquina);
  }

  /**
   * Método encargado de extraer una máquina de la pila de máquinas.
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public Maquina extraerMaquina()
  {
    Maquina maquina = this.listaMaquinas.pop();
    maquina.estropear();
    return maquina;
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
  public int getTamanioEstacion()
  {
    return this.tamanioEstacion;
  }

}
