/**
 * 
 */
package estaciontrabajo;

/**
 *
 * <b>Nombre de la clase</b>: Maquina
 * <p>
 * <b>Descripción</b>: Clase encargada de modelar el funcionamiento de los
 * defectos y arreglos de la máquina.
 * </p>
 * 
 * @author Mariop
 *
 */
public class Maquina
{
  /**
   * El atributo estado de tipo boolean se emplea para almacenar el estado de la
   * máquina. El valor 0 se usa para máquinas estropeadas y el 1 para
   * funcionales.
   */
  private boolean estado = true;

  /**
   * Método encargado de estropear la máquina
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  public void estropear()
  {
    this.estado = false;
  }

  /**
   * Método encargado de arreglar la máquina
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   */
  public void arreglar()
  {
    this.estado = true;
  }

  /**
   * Método encargado de devolver el estado de la máquina
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public boolean getEstado()
  {
    return this.estado;
  }

}
