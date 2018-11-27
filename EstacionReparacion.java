/**
 * Clase generada y codificada por Mariop
 */
package estaciontrabajo;

import distribucion.implementaciones.Exponencial;

/**
 *
 * <b>Nombre de la clase</b>: EstacionReparacion
 * <p>
 * <b>Descripci�n</b>: Clase encargada de modelar una estaci�n de reparaci�n en
 * particular
 * </p>
 * 
 * @author Mariop
 *
 */
public class EstacionReparacion
{
  /**
   * El atributo maquinaReparacion de tipo Maquina se emplea para almacenar la
   * m�quina que se est� reparando.
   */
  private Maquina maquinaReparacion;

  /**
   * El atributo distribucion de tipo Exponencial se emplea para almacenar la
   * distribuci�n exponencial
   */
  private final Exponencial distribucion = new Exponencial(1321549843);

  /**
   * El atributo tiempoOperacion de tipo Double se emplea para acumular el
   * tiempo que el operario ha estado trabajando
   */
  private Double tiempoOperacion = new Double(0);

  private Double tiempoSiguienteReparacion = new Double(-1);

  /**
   * M�todo encargado de mover la m�quina fuera de la estaci�n
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public Maquina extraerMaquina()
  {
    Maquina maquinaExtraida = this.maquinaReparacion;
    this.maquinaReparacion = null;
    this.tiempoSiguienteReparacion = new Double(-1);
    maquinaExtraida.arreglar();
    return maquinaExtraida;
  }

  /**
   * M�todo encargado de introducir una m�quina en la estaci�n
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>: Devuelve el tiempo generado que tardar� esa m�quina en
   * salir. Viene normalizado, no con arreglo a la simulaci�n.
   *
   * @param maquina
   * @return
   */
  public Double introducirMaquina(Maquina maquina)
  {
    this.maquinaReparacion = maquina;
    this.tiempoSiguienteReparacion = this.distribucion
            .getSiguienteTiempo(this.tiempoOperacion);
    this.tiempoOperacion += this.tiempoSiguienteReparacion;
    return this.tiempoSiguienteReparacion;
  }

  /**
   * @return the tiempoOperacion
   */
  public Double getTiempoOperacion()
  {
    return this.tiempoOperacion;
  }

  /**
   * M�todo encargado de devolver true si hay una m�quina en reparaci�n y false
   * si no
   * <p>
   * <b>Entradas</b>:
   * <p>
   * <b>Salidas</b>:
   *
   * @return
   */
  public boolean tieneMaquina()
  {
    return this.maquinaReparacion == null ? false : true;
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
  public Double getSiguienteTiempo()
  {
    return this.tiempoSiguienteReparacion;
  }

}
