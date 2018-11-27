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
 * <b>Descripci�n</b>: Clase encargada de modelar la estaci�n de trabajo
 * gen�rica
 * </p>
 * 
 * @author Mariop
 *
 */
public class EstacionTrabajo
{
  /**
   * El atributo tamanioEstacion de tipo int se emplea para almacenar el n�mero
   * m�ximo de m�quinas que pueden entrar en la estaci�n
   */
  private int tamanioEstacion;

  /**
   * El atributo listaMaquinas de tipo List<Maquina> se emplea para almacenar la
   * lista de m�quinas contenidas en la estaci�n de trabajo en particular
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
   * M�todo encargado de devolver el n�mero de m�quinas que se encuentran
   * actualmente en la estaci�n
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
   * M�todo encargado de introducir una m�quina en la cola de la estaci�n
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
   * M�todo encargado de extraer una m�quina de la pila de m�quinas.
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
   * M�todo encargado de
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
