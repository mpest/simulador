/**
 * 
 */
package estaciontrabajo;

/**
 *
 * <b>Nombre de la clase</b>: Maquina
 * <p>
 * <b>Descripci�n</b>: Clase encargada de modelar el funcionamiento de los
 * defectos y arreglos de la m�quina.
 * </p>
 * 
 * @author Mariop
 *
 */
public class Maquina
{
  /**
   * El atributo estado de tipo boolean se emplea para almacenar el estado de la
   * m�quina. El valor 0 se usa para m�quinas estropeadas y el 1 para
   * funcionales.
   */
  private boolean estado = true;

  /**
   * M�todo encargado de estropear la m�quina
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
   * M�todo encargado de arreglar la m�quina
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
   * M�todo encargado de devolver el estado de la m�quina
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
