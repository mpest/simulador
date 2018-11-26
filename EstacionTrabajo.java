/**
 * 
 */
package estaciontrabajo;

import java.util.ArrayList;
import java.util.List;

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
  private List<Maquina> listaMaquinas = new ArrayList<>();

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
   * @return the listaMaquinas
   */
  public List<Maquina> getListaMaquinas()
  {
    return this.listaMaquinas;
  }

  /**
   * Método encargado de introducir una máquina en la cola de la estación
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  public void introducirMaquina(Maquina maquina)
  {
    this.listaMaquinas.add(maquina);
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
    Maquina output = Util.poll(this.listaMaquinas);
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
  public int getTamanioEstacion()
  {
    return this.tamanioEstacion;
  }

  /**
   * Método encargado de devolver si la estación tiene máquinas
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public boolean tieneMaquinas()
  {
    return this.listaMaquinas.isEmpty() ? false : true;
  }

}
